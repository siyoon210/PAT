package com.injucksung.injucksung.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@ToString
@Entity
@Table(name = "content_file")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContentFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originName;

    @Column(nullable = false)
    private String savedName;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String length;

    @Column(nullable = false)
    private String regDate;

    @Column(nullable = false)
    private String path;
}
