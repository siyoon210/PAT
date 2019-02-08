package com.injucksung.injucksung.service;

import com.injucksung.injucksung.domain.Question;
import com.injucksung.injucksung.domain.QuizRecord;
import com.injucksung.injucksung.domain.Result;
import com.injucksung.injucksung.dto.SubmittedQuizInfoDto;
import com.injucksung.injucksung.repository.QuestionRepository;
import com.injucksung.injucksung.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final QuestionRepository questionRepository;

    @Override
    public List<Result> getResults(Long quizRecordId) {
        return resultRepository.findResultByQuizRecordId(quizRecordId);
    }

    @Override
    public List<Result> addResult(SubmittedQuizInfoDto submittedQuizInfoDto, QuizRecord quizRecord) {
        List<Result> results = new ArrayList<>();
        List<Question> questions = questionRepository.findQuestionById(submittedQuizInfoDto.getSelectedChoices().keySet());

        for (Question question : questions) {
            Integer chosenNum = submittedQuizInfoDto.getSelectedChoices().get(question.getId());
            results.add(
                    Result.builder()
                            .isCorrect(question.getCorrect() == chosenNum)
                            .checkedChoice(chosenNum)
                            .question(question)
                            .quizRecord(quizRecord)
                            .build());
        }

        return resultRepository.saveAll(results);
    }

    @Override
    public List<Result> addResult(Long[] bookContentIds, QuizRecord quizRecord) {
        List<Result> results = new ArrayList<>();
        List<Question> questions = questionRepository.findQuestionByBookContentId(bookContentIds);

        for (int i = 0; i < questions.size(); i++) {
            results.add(
                    Result.builder()
                            .isCorrect(false)
                            .checkedChoice(0)
                            .sequence(i)
                            .question(questions.get(i))
                            .quizRecord(quizRecord)
                            .build());
        }
        return resultRepository.saveAll(results);
    }

    @Override
    public QuizRecord checkQuestion(Long resultId, int checkedChoice) {
        Result resultById = resultRepository.findResultById(resultId);
        resultById.setCheckedChoice(checkedChoice);
        if (resultById.getQuestion().getCorrect() == checkedChoice) {
            resultById.setIsCorrect(true);
        }

        Result save = resultRepository.save(resultById);
        return save.getQuizRecord();
    }

    @Override
    public int modifyResult(Result result) {
        return 0;
    }

    @Override
    public void deleteResult(Long id) {

    }
}
