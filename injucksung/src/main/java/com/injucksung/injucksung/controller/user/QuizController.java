package com.injucksung.injucksung.controller.user;

import com.injucksung.injucksung.domain.QuizRecord;
import com.injucksung.injucksung.domain.Result;
import com.injucksung.injucksung.dto.SelectedBookContentForQuizForm;
import com.injucksung.injucksung.dto.SubmittedQuizInfoDto;
import com.injucksung.injucksung.security.CustomUserDetails;
import com.injucksung.injucksung.service.QuestionService;
import com.injucksung.injucksung.service.QuizRecordService;
import com.injucksung.injucksung.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {
    private final QuestionService questionService;
    private final QuizRecordService quizRecordService;
    private final ResultService resultService;

    //문제 리스트 가져오기
    @GetMapping
    public String getQuestionList(@ModelAttribute SelectedBookContentForQuizForm selectedBookContentForQuizForm,
                                  Model model) {
        String viewName = null;
        if (selectedBookContentForQuizForm.getAction().equals("문제풀기")) {
            //세션에 저장된 스프링 시큐리티 정보로 CustomUserDetails 가져오기
            CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            QuizRecord quizRecord = quizRecordService.addQuizRecord(selectedBookContentForQuizForm.getBookContentIds(), userDetails);
            List<Result> results = resultService.addResult(selectedBookContentForQuizForm.getBookContentIds(), quizRecord);
            Collections.sort(results);
            model.addAttribute("question", results.get(0).getQuestion());
            model.addAttribute("result", results.get(0));

            viewName = "/users/quiz/solving";
        } else {
            model.addAttribute("questions", questionService.getQuestionList(selectedBookContentForQuizForm));
            model.addAttribute("bookContentCount", selectedBookContentForQuizForm.getBookContentIds().length);
            viewName = "/users/quiz/grading";
        }
        return viewName;
    }

    //채점하기 제출
    @PostMapping
    public String submitQuiz(@RequestParam Map<String, String> submittedQuizInfo, Model model) {
        SubmittedQuizInfoDto submittedQuizInfoDto = new SubmittedQuizInfoDto(submittedQuizInfo);

        //세션에 저장된 스프링 시큐리티 정보로 CustomUserDetails 가져오기
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        QuizRecord quizRecord = quizRecordService.addQuizRecord(submittedQuizInfoDto, userDetails);
        List<Result> results = resultService.addResult(submittedQuizInfoDto, quizRecord);

        model.addAttribute("quizRecord", quizRecord);
        model.addAttribute("results", results);
        return "/users/quiz/result";
    }

    //문제풀기 한문제 제출하기
    @PostMapping("/{resultId}")
    public String checkQuestion(@RequestParam("checkedChoice") int checkedChoice, @PathVariable Long resultId, Model model) {
        QuizRecord quizRecord = resultService.checkQuestion(resultId, checkedChoice);
        List<Result> results = resultService.getResults(quizRecord.getId());
        Collections.sort(results);
        Optional<Result> nextResult = results.stream().filter(result -> result.getCheckedChoice() == 0).findFirst();

        String viewName = null;
        if (nextResult.isPresent()) {
            model.addAttribute("question", nextResult.get().getQuestion());
            model.addAttribute("result", nextResult.get());
            viewName = "/users/quiz/solving";
        } else {
            model.addAttribute("quizRecord", quizRecordService.modifyQuizRecordService(quizRecord));
            model.addAttribute("results", results);
            viewName = "/users/quiz/result";
        }

        return viewName;
    }

}
