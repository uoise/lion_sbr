package com.ll.sbr.answer.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerForm {

    @NotBlank(message = "내용을 작성하세요.")
    private String content;
}
