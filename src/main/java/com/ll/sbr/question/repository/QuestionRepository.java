package com.ll.sbr.question.repository;

import com.ll.sbr.question.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
