package com.injucksung.injucksung.service;

import com.injucksung.injucksung.domain.QuestionCategory;
import org.springframework.data.domain.Page;

public interface QuestionCategoryService {
    QuestionCategory addQuestionCategory(QuestionCategory questionCategory);

    void deleteQuestionCategory(Long id);

    int modifyQuestionCategory(QuestionCategory questionCategory);

    Page<QuestionCategory> getQuestionCategoryList(int start);
}
