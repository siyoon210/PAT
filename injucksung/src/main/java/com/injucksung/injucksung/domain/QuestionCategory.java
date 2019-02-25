package com.injucksung.injucksung.domain;

import lombok.*;

import javax.persistence.*;

@Entity @Table(name = "question_category")
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(of="id")
public class QuestionCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column
    private Long parentId;

    @Column
    private String type; //인성 "per" 적성 "apt
}
