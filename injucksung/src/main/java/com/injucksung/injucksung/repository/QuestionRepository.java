package com.injucksung.injucksung.repository;

import com.injucksung.injucksung.domain.Question;
import com.injucksung.injucksung.dto.BookContentForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    //책 목차 id로 문제 조회하기
    @Query(value = "SELECT q FROM Question q JOIN FETCH q.bookContent bc JOIN FETCH q.questionCategory qc WHERE book_content_id=:bookContentId")
    List<Question> findQuestionByBookContentId(@Param("bookContentId") Long bookContentId);

    //책 목차 id가 여러개인 경우(list) 해당 문제 조회하기
    @Query(value = "SELECT q FROM Question q JOIN FETCH q.bookContent bc JOIN FETCH q.questionCategory qc WHERE book_content_id IN :bookContentIds ORDER BY q.bookContent.sequence DESC, q.bookNumber DESC")
    List<Question> findQuestionByBookContentId(@Param("bookContentIds") Long[] bookContentIds);

    //책 id로 문제 조회하기
    //@Query(value = "SELECT * FROM question q JOIN book_content bc ON q.book_content_id = bc.id WHERE bc.book_id =:bookId", nativeQuery = true)
    @Query(value = "SELECT q FROM Question q INNER JOIN q.bookContent bc WHERE book_id =:bookId")
    Page<Question> findQuestionByBookId(@Param("bookId") Long bookId, Pageable pageable);

    //문제 id로 문제 조회하기
//    @Query(value = "SELECT q FROM Question q JOIN FETCH q.bookContent bc WHERE q.id =:id") // (책 목차까지 바로 FETCH 해오기)
    Question findQuestionById(@Param("id") Long id);

    //문제 id가 여러개인 경우 해당 문제 조회하기
    @Query(value = "SELECT q FROM Question q WHERE id IN :questionIds")
    List<Question> findQuestionById(@Param("questionIds") Set<Long> questionIds);

    //책 목차 id로 연관된 문제 삭제하기
    void deleteByBookContentId(Long bookContentId);
}
