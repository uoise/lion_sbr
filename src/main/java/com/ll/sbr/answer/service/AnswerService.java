package com.ll.sbr.answer.service;

import com.ll.sbr.answer.model.Answer;
import com.ll.sbr.answer.repository.AnswerRepository;
import com.ll.sbr.question.model.Question;
import com.ll.sbr.user.model.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    @Transactional(readOnly = true)
    public Answer getAnswer(Long id) {
        return answerRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Answer> getAnswerList(Question question) {
        return answerRepository.findAllByQuestion(question);
    }

    @Transactional
    public Answer create(Question question, String content, SiteUser author) {
        return answerRepository.save(Answer.builder()
                .question(question)
                .content(content)
                .author(author)
                .build());
    }

    @Transactional
    public Answer update(Answer answer, String content) {
        return answerRepository.save(Answer.builder()
                .id(answer.getId())
                .question(answer.getQuestion())
                .content(content)
                .author(answer.getAuthor())
                .createDate(answer.getCreateDate())
                .build());
    }

    @Transactional
    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }
}
