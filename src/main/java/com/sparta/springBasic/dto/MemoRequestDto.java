package com.sparta.springBasic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoRequestDto {
    private String title;
    private String username;
    private String contents;
}


