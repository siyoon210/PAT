package com.injucksung.injucksung.service;

import com.injucksung.injucksung.domain.BookContent;
import com.injucksung.injucksung.dto.BookContentForm;

import java.util.LinkedList;
import java.util.List;

public interface BookContentService {
    BookContent addBookContent(BookContentForm bookContentForm);

    void deleteBookContent(Long id);

    BookContent modifyBookContent(BookContentForm bookContentForm, Long bookContentId);

    List<BookContent> modifyBookContent(String sequenceDirection, Long bookContentId);

    List<BookContent> getBookContentList(Long bookId);

    BookContent getBookContent(Long bookContentId);
}
