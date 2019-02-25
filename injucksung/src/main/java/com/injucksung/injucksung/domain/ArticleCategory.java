package com.injucksung.injucksung.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(of="id")
public class ArticleCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "default 0")
    private int articleCount;

    @Column(columnDefinition = "default 0")
    private int sequence;
}
