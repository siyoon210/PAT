package com.injucksung.injucksung.repository;

import com.injucksung.injucksung.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.injucksung.injucksung.repository.Print.print;


@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void 책_모든_목록_조회하기() throws Exception {
        Pageable pageable = PageRequest.of(0, 3);

        Page<Book> books = bookRepository.findAll(pageable);
        print(books);
    }

    @Test
    public void name으로_책_검색하여_조회하기() throws Exception {
        Pageable pageable = PageRequest.of(0, 3);

        Page<Book> books = bookRepository.findBookByNameContaining("마스터", pageable);
        print(books);
    }

    @Test
    public void id로_책_한건_조회하기() throws Exception {
        Book book = bookRepository.findBookById(1L);
        System.out.println(book.toString());
    }

    @Test
    public void 책_한건_저장하기() throws Exception {
//        Book book = new Book("인적성의 정석 2019", "2018.12.14", "남궁성", "4949303049", "정석출판사");
//        Book saveAndFlush = bookRepository.save(book);
        this.책_모든_목록_조회하기();
    }

    @Test
    public void 책_수정하기() throws Exception {
//        Book book = new Book("인적성의 정석 2019", "2018.12.14", "남궁성", "4949303049", "정석출판사");
//        book.setId(1L);
//        Book saveAndFlush = bookRepository.save(book);
        this.책_모든_목록_조회하기();
    }

    @Test
    public void 책_삭제하기() throws Exception {
        bookRepository.deleteById(1L);
        System.out.println("------------");
        this.책_모든_목록_조회하기();
    }

}


