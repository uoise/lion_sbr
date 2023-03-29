package com.ll.sbr;

import com.ll.sbr.answer.repository.AnswerRepository;
import com.ll.sbr.answer.service.AnswerService;
import com.ll.sbr.question.model.Question;
import com.ll.sbr.question.repository.QuestionRepository;
import com.ll.sbr.question.service.QuestionService;
import com.ll.sbr.user.model.SiteUser;
import com.ll.sbr.user.repository.UserRepository;
import com.ll.sbr.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class SbrApplicationTests {

    @Autowired
    public SbrApplicationTests(UserRepository userRepository,
                               QuestionRepository questionRepository,
                               AnswerRepository answerRepository,
                               UserService userService,
                               QuestionService questionService,
                               AnswerService answerService) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.userService = userService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    private final UserService userService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    @Rollback(value = false)
    @BeforeEach
    void dbInitializer() {
        System.out.println("=========== DB INIT ===========");
        answerRepository.deleteAll();
        answerRepository.clearAutoIncrement();

        questionRepository.deleteAll();
        questionRepository.clearAutoIncrement();

        userRepository.deleteAll();
        userRepository.clearAutoIncrement();

        System.out.println("=========== DELETE DONE ===========");
        userService.create("user1", "1234", "user1@gmail.com");
        userService.create("jake", "1234", "jake@gmail.com");
        userService.create("dean", "1234", "dean@gmail.com");

        SiteUser u1 = userService.getSiteUser("jake");
        SiteUser u2 = userService.getSiteUser("dean");

        for (int i = 1; i <= 300; i++) questionService.create(String.format("Sbb[%s]", i), "autogen", u2);
        Question q1 = questionService.create("Meaning of Spring Triangle", "what is this?", u1);

        for (int i = 0; i < 14; i++) answerService.create(q1, String.format("%s is my num", i), u2);


        System.out.println("=========== DB INIT DONE ===========");
    }

    @Test
    void doNothing() {
        System.out.println("=========== TEST DONE ===========");
    }
}
