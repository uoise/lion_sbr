package com.ll.sbr.answer.repository;

import com.ll.sbr.answer.model.Answer;
import com.ll.sbr.question.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByQuestion(Question question);
}
