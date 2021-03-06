package com.injucksung.injucksung.service;

import com.injucksung.injucksung.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.injucksung.injucksung.repository.Print.print;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Test
    public void 책_모든_목록_조회하기() throws Exception {
        int start = 0;

        Page<Book> books = bookService.getBookList(start);
        print(books);
    }

    @Test
    public void 책_목록_검색하여_조회하기() throws Exception {
        int start = 0;
//        String searchType = "name";
//        String searchWord = "마스터";
//        String searchType = "author";
//        String searchWord = "연구소";
        String searchType = "isbn";
        String searchWord = "123461";
//        String searchType = "publisher";
//        String searchWord = "하하";

        Page<Book> books = bookService.getBookList(start, searchType, searchWord);
        print(books);
    }

    @Test
    public void id로_책_한건_조회하기() throws Exception {
        Book book = bookService.getBook(1L);
        System.out.println(book.toString());
    }

    @Test
    public void 책_한건_저장하기() throws Exception {
//        Book book = new Book("인적성의 정석 2019", "2018.12.14", "남궁성", "4949303049", "정석출판사");
//        bookService.addBook(book);
//        this.책_모든_목록_조회하기();
    }

    @Test
    public void 책_수정하기() throws Exception {
//        Book book = new Book("인적성의 신", "2018.12.14", "유어스토리", "4949303049", "신출판사");
//        book.setId(1L);
//        bookService.modifyBook(book);
        this.책_모든_목록_조회하기();
    }

    @Test
    public void 책_삭제하기() throws Exception {
        bookService.deleteBook(2L);
        System.out.println("------------");
        this.책_모든_목록_조회하기();
    }

}


