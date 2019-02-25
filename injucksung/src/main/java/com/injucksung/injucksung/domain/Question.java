package com.injucksung.injucksung.domain;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "question")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of="id")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_category_id", nullable = false)
    private QuestionCategory questionCategory; //문제 분류를 위한 카테고리

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_content_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BookContent bookContent; //책 목차

    @Column(nullable = false)
    private int bookNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "content_file_id")
    private ContentFile contentFile; //지문

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "explanation_file_id")
    private ExplanationFile explanationFile; //해설

    @Column(nullable = false)
    private int correct; //정답인 보기

    @Column(nullable = false, columnDefinition = "int default 0")
    private int tryCount; //문제를 시도한 횟수

    @Column(nullable = false, columnDefinition = "int default 0")
    private int correctCount; //문제를 맞춘 횟수

    @Column(nullable = false)
    private int choiceCount; //4지선다인지 5지선다인지
}