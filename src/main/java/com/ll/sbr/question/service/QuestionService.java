package com.ll.sbr.question.service;

import com.ll.sbr.question.model.Question;
import com.ll.sbr.question.repository.QuestionRepository;

import com.ll.sbr.user.model.SiteUser;
import com.ll.sbr.utils.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public Question getQuestion(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> {
            throw new DataNotFoundException("question not found");
        });
    }

    @Transactional(readOnly = true)
    public Page<Question> getQuestionList(int page) {
        int DEFAULT_PAGE_SIZE = 10;
        String DEFAULT_PROPERTIES = "createDate";
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc(DEFAULT_PROPERTIES));
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by(sorts));
        return questionRepository.findAll(pageable);
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
