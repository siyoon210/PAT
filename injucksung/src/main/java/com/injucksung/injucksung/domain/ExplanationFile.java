package com.injucksung.injucksung.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity @Table(name = "explanation_file")
@Setter @Getter
@NoArgsConstructor @RequiredArgsConstructor
public class ExplanationFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String originName;

    @NonNull
    @Column(nullable = false)
    private String savedName;

    @NonNull
    @Column(nullable = false)
    private String type;

    @NonNull
    @Column(nullable = false)
    private String length;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date regDate;

    @NonNull
    @Column(nullable = false)
    private String path;
}