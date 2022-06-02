package com.sparta.springBasic.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String password2;

    public SignupRequestDto(String username, String password, String password2) {
        this.username = username;
        this.password = password;
        this.password2 = password2;
    }
}