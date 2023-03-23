package com.ll.sbr.question.service;

import com.ll.sbr.question.model.Question;
import com.ll.sbr.question.repository.QuestionRepository;

import com.ll.sbr.user.model.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public Question getQuestion(Long id) {
        return questionRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Question> getQuestionList() {
        return questionRepository.findAll();
    }

    @Transactional
    public Question create(String subject, String content, SiteUser author) {
        return questionRepository.save(Question.builder()
                .subject(subject)
                .content(content)
                .author(author)
                .build());
    }

    @Transactional
    public Question update(Question question, String subject, String content) {
        return questionRepository.save(Question.builder()
                .id(question.getId())
                .subject(subject)
                .content(content)
                .createDate(question.getCreateDate())
                .author(question.getAuthor())
                .build());
    }

    public void delete(Question question) {
        questionRepository.delete(question);
    }
}
