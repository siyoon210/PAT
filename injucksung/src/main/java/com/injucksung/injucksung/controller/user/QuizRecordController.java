package com.injucksung.injucksung.controller.user;

import com.injucksung.injucksung.service.QuizRecordService;
import com.injucksung.injucksung.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/quiz-records")
public class QuizRecordController {
    private final QuizRecordService quizRecordService;
    private final ResultService resultService;

    //시험 목록 가져오기
    @GetMapping
    public String getResults(@PathVariable Long userId,
                             @RequestParam(value = "start", defaultValue = "0") int start,
                             Model model) {
        model.addAttribute("quizRecordPage", quizRecordService.getQuizRecordList(userId, start));
        return "/users/quiz/quizRecordList";
    }

    //결과 페이지 한건 가져오기
    @GetMapping("/{quizRecordId}")
    public String getResult(@PathVariable Long quizRecordId,
                            Model model) {
        model.addAttribute("results", resultService.getResults(quizRecordId));
        return "/users/quiz/result";
    }
}
