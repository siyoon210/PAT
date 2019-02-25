package com.injucksung.injucksung.domain;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book_content")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of="id")
public class BookContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "super_book_content_id")
    private BookContent superBookContent;

    @OneToMany(mappedBy = "superBookContent", cascade = CascadeType.ALL)
    private List<BookContent> subBookContents;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer depth; //책 목차의 깊이 구분자 (대분류는 0 그 이하는 1,2,3 . . . )

    @Column(nullable = false)
    private Integer sequence; //책 목차의 같은 depth 의 순서 구분

    @Column(nullable = false)
    private Boolean isMockTest; //모의고사 인지 일반 문제인지

    @Column
    private Integer recommendTime; //권장시간 (단위 초)

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer questionCount; //이 목차에 해당하는 문제 갯수
}
