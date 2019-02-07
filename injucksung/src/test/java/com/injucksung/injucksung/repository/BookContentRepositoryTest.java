package com.injucksung.injucksung.repository;

import com.injucksung.injucksung.domain.BookContent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.injucksung.injucksung.repository.Print.print;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.AdditionalMatchers.not;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class BookContentRepositoryTest {
    @Autowired
    private BookContentRepository bookContentRepository;

    @Test
    public void bookId로_책목차_조회하기() throws Exception {
        List<BookContent> bookContents = bookContentRepository.findBookContentByBookId(1L);
        Assert.assertEquals(8, bookContents.size());
    }

    @Test
    public void 책목차ID로_책목차_한건_조회하기() throws Exception {
        BookContent bookContentById = bookContentRepository.findBookContentById(4L);
        Assert.assertNotNull(bookContentById);
        System.out.println(bookContentById.getSuperBookContent().getName());
        for (BookContent subBookContent : bookContentById.getSubBookContents()) {
            System.out.println(subBookContent.getName());
        }
    }

    @Test
    public void 가장큰Sequence조회해오기() {
        Long superBookContentId = 4L;
        int maxSequenceByBookIdAndDepth = bookContentRepository.findMaxSequenceBySuperBookContentId(superBookContentId);
        //bookid가 1이고 뎁스가 1인 경우의 sequnece 최대값은 2 (샘플데이터 기준)
//        Assert.assertEquals(2, maxSequenceByBookIdAndDepth);
        Assert.assertThat(maxSequenceByBookIdAndDepth, is(2));
    }

}