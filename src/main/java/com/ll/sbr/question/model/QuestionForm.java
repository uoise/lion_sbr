package com.ll.sbr.question.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {

    @NotBlank(message = "제목을 입력하세요.")
    @Size(max = 200)
    private String subject;

    @NotBlank(message = "내용을 입력하세요.")
    private String content;
}