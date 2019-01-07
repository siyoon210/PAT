package com.injucksung.injucksung.service;

import com.injucksung.injucksung.domain.Question;
import com.injucksung.injucksung.domain.QuizRecord;
import com.injucksung.injucksung.domain.Result;
import com.injucksung.injucksung.repository.QuestionRepository;
import com.injucksung.injucksung.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final QuestionRepository questionRepository;

    @Override
    public Page<Result> getResultList(Long QuizResultId, int start) {
        return null;
    }

    @Override
    public List<Result> addResult(Map<String, String> selectedChoices, QuizRecord quizRecord) {
        List<Result> results = new ArrayList<>();
        //TODO for문 X 성능 심히 구림
        for (Map.Entry<String, String> selectedChoice : selectedChoices.entrySet()) {
            Question questionById = questionRepository.findQuestionById(Long.parseLong(selectedChoice.getKey()));
            boolean isCorrect = questionById.getCorrect() == Integer.parseInt(selectedChoice.getValue());
            Result result = Result.builder()
                    .isCorrect(isCorrect).checkedChoice(Integer.parseInt(selectedChoice.getValue()))
                    .question(questionById).quizRecord(quizRecord)
                    .build();
            Result save = resultRepository.save(result);
            results.add(save);
        }

        return results;
    }

    @Override
    public int modifyResult(Result result) {
        return 0;
    }

    @Override
    public void deleteResult(Long id) {

    }
}
