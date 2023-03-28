package com.ll.sbr.answer.controller;

import com.ll.sbr.answer.model.AnswerForm;
import com.ll.sbr.answer.service.AnswerService;
import com.ll.sbr.question.model.Question;
import com.ll.sbr.question.service.QuestionService;
import com.ll.sbr.user.model.SiteUser;
import com.ll.sbr.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String create(Model model, @PathVariable Long id, @Valid AnswerForm answerForm, BindingResult bindingResult) {
        Question question = questionService.getQuestion(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }
        // for spring sec
        SiteUser author = userService.getSiteUser("luke");
        answerService.create(question, answerForm.getContent(), author);
        return String.format("redirect:/question/detail/%s", id);
    }
}
