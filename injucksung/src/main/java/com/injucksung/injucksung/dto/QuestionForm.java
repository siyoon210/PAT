package com.injucksung.injucksung.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class QuestionForm {
    private Long questionCategoryId;        // 문제 분류를 위한 카테고리
    private Long bookId;                    // 책
    private Long bookContentId;             // 책 목차
    private Integer bookNumber;             // 책 번호
    private MultipartFile contentFile;      // 지문
    private MultipartFile explanationFile;  // 해설
    private int correct;                    // 정답인 보기
    private int tryCount;                   // 문제를 시도한 횟수
    private int correctCount;               // 문제를 맞춘 횟수
    private int choiceCount;                // 4지선다인지 5지선다인지
}
