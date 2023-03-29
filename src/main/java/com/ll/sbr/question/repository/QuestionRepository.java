package com.ll.sbr.question.repository;

import com.ll.sbr.question.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Page<Question> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE question Auto_Increment = 1", nativeQuery = true)
    void clearAutoIncrement();
}
