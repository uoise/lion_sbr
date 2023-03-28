package com.ll.sbr.question.repository;

import com.ll.sbr.question.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE question Auto_Increment = 1", nativeQuery = true)
    void clearAutoIncrement();
}
