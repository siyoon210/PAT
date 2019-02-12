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

    QuizRecord modifyQuizRecordService(QuizRecord quizRecord);
}
