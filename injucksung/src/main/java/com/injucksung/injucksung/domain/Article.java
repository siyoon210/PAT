package com.injucksung.injucksung.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity @Table(name = "article")
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(of="id")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_category_id")
    private ArticleCategory articleCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "article")
    private ArticleContent articleContent;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "default 0")
    private int hit;

    @Column(nullable = false)
    private Date regDate;

    @Column
    private Date modifyDate;

    @Column
    private String ipAddress;

    @Column(columnDefinition = "default 0")
    private int commentCount;
}
