package com.example.toypjt.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardPostingRequestDto {

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 20)
    private String title;

    @NotBlank(message = "내용은 필수입니다")
    private String content;

}
