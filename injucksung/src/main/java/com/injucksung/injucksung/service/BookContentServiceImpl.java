package com.injucksung.injucksung.service;

import com.injucksung.injucksung.domain.BookContent;
import com.injucksung.injucksung.dto.BookContentForm;
import com.injucksung.injucksung.repository.BookContentRepository;
import com.injucksung.injucksung.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookContentServiceImpl implements BookContentService {
    private final BookContentRepository bookContentRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public BookContent addBookContent(BookContentForm bookContentForm) {
        BookContent bookContent = convertFormToBookContent(bookContentForm);
        return bookContentRepository.save(bookContent);
    }

    private BookContent convertFormToBookContent(BookContentForm bookContentForm) {
        //depth 설정
        Integer depth = bookContentForm.getDepth();
        bookContentForm.setDepth((depth == null) ? 0 : depth + 1);

        //questionCount 0으로 설정
        bookContentForm.setQuestionCount(0);

        //bookContent 생성 후 복사
        BookContent bookContent = new BookContent();
        BeanUtils.copyProperties(bookContentForm, bookContent);

        //superBookContent
        Long bookContentId = bookContentForm.getBookContentId();
        if (bookContentId != null) {
            bookContent.setSuperBookContent(
                    bookContentRepository.findBookContentById(bookContentId));
        }

        //sequence 설정
        if (bookContentId != null) {
            int maxSequenceByBookIdAndDepth;
            try {
                maxSequenceByBookIdAndDepth = bookContentRepository.findMaxSequenceBySuperBookContentId(bookContentId) + 1;
            } catch (Exception e) {
                e.printStackTrace();
                maxSequenceByBookIdAndDepth = 0;
            }
            bookContent.setSequence(maxSequenceByBookIdAndDepth);
        } else {
            bookContent.setSequence(
                    bookContentRepository.findBookContentByDepthEqualsZero(
                            bookContentForm.getBookId()).size());
        }

        //Book 정보 주입
        bookContent.setBook(bookRepository.findBookById(bookContentForm.getBookId()));
        return bookContent;
    }

    @Override
    @Transactional
    public void deleteBookContent(Long id) {
        arrangeSequencePull(id);
        bookContentRepository.deleteById(id);
    }

    //삭제 시에 sequence (정렬 순서) 재정렬
    private void arrangeSequencePull(Long id) {
        BookContent bookContentById = bookContentRepository.findBookContentById(id);
        Integer sequence = bookContentById.getSequence();
        BookContent superBookContent = bookContentById.getSuperBookContent();

        if (superBookContent != null) {
            bookContentRepository.arrangeSequencePull(superBookContent.getId(), sequence);
        } else {
            bookContentRepository.arrangeSequencePullByDepthEqualsZero(
                    bookContentById.getBook().getId(), sequence);
        }
    }

    @Override
    @Transactional
    public BookContent modifyBookContent(BookContentForm bookContentForm, Long bookContentId) {
        return null;
    }

    @Override
    @Transactional
    public LinkedList<BookContent> modifyBookContent(String sequenceDirection, Long bookContentId) {
        BookContent bookContent = bookContentRepository.findBookContentById(bookContentId);

        int sequence = 0;
        switch (sequenceDirection) {
            case "up":
                sequence = bookContent.getSequence() - 1;
                break;
            case "down":
                sequence = bookContent.getSequence() + 1;
                break;
        }

        BookContent bookContentBySequence;
        BookContent superBookContent = bookContent.getSuperBookContent();
        if (superBookContent != null) {
            bookContentBySequence =
                    bookContentRepository.findBookContentBySuperBookContentIdAndSequence(
                            bookContent.getSuperBookContent().getId(),
                            sequence);
        } else {
            bookContentBySequence =
                    bookContentRepository.findBookContentByBookIdAndSequenceAndDepthEqualsZero(
                            bookContent.getBook().getId(),
                            sequence);
        }

        swapSequence(bookContent, bookContentBySequence);

        LinkedList<BookContent> bookContentByBookId = bookContentRepository.findBookContentByBookId(bookContent.getBook().getId());
        return bookContentByBookId;
    }

    private void swapSequence(BookContent bookContentById, BookContent bookContentBySuperBookContentIdAndSequence) {
        if (bookContentBySuperBookContentIdAndSequence != null) {
            Integer tmp;
            tmp = bookContentById.getSequence();
            bookContentById.setSequence(bookContentBySuperBookContentIdAndSequence.getSequence());
            bookContentBySuperBookContentIdAndSequence.setSequence(tmp);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookContent> getBookContentList(Long bookId) {
        LinkedList<BookContent> bookContentByBookId = bookContentRepository.findBookContentByBookId(bookId);
        return sortBookContents(bookContentByBookId);
    }

    private List<BookContent> sortBookContents(LinkedList<BookContent> bookContentList) {
        BookContent tmpBookContent;
        for (int i = 0; i < bookContentList.size(); i++) {
            BookContent bookContent = bookContentList.get(i);
            BookContent superBookContent = bookContent.getSuperBookContent();
            if (superBookContent != null) {
                tmpBookContent = bookContent;
                bookContentList.remove(bookContent);
                bookContentList.add(
                        bookContentList.indexOf(superBookContent) + 1 + bookContent.getSequence(),
                        tmpBookContent);
            }
        }
        return bookContentList;
    }

    @Override
    @Transactional(readOnly = true)
    public BookContent getBookContent(Long bookContentId) {
        return bookContentRepository.findBookContentById(bookContentId);
    }
}
