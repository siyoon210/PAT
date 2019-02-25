package com.injucksung.injucksung.domain;

import lombok.*;

import javax.persistence.*;


@Entity @Table(name = "book")
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(of="id")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String publicationDate;

    @Column(length = 100, nullable = false)
    private String author;

    @Column(length = 100)
    private String isbn; //국제표준도서번호

    @Column(nullable = false)
    private String publisher;
}
