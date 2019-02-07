package com.injucksung.injucksung.service;

import com.injucksung.injucksung.domain.QuizRecord;
import com.injucksung.injucksung.dto.SubmittedQuizInfoDto;
import com.injucksung.injucksung.security.CustomUserDetails;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface QuizRecordService {

    Page<QuizRecord> getQuizRecordList(Long userId, int start);

    QuizRecord addQuizRecord(SubmittedQuizInfoDto submittedQuizInfoDto, CustomUserDetails userDetails);

    QuizRecord addQuizRecord(Long[] bookContentIds, CustomUserDetails userDetails);

    // TODO: 2018-12-18 이게 과연 필요할까?
    int modifyQuizRecordService(QuizRecord quizRecord);

    // TODO: 2018-12-18 이게 과연 필요할까? 
    void deleteQuizRecordService(Long id);
}
