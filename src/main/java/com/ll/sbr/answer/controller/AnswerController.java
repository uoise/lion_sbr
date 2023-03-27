package com.ll.sbr.answer.controller;

import com.ll.sbr.answer.service.AnswerService;
import com.ll.sbr.question.model.Question;
import com.ll.sbr.question.service.QuestionService;
import com.ll.sbr.user.model.SiteUser;
import com.ll.sbr.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    @PostMapping("/create/{id}")
    public String create(@PathVariable Long id, String content) {
        Question question = questionService.getQuestion(id);
        // for spring sec
        SiteUser author = userService.getSiteUser("luke");
        answerService.create(question, content, author);
        return String.format("redirect:/question/" +
                "detail/%s", id);
    }
}
