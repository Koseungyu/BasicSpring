package com.sparta.springBasic.service;

import com.sparta.springBasic.dto.SignupRequestDto;
import com.sparta.springBasic.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("유저네임 글자수 체크")
    void usernameLengthChk() {

        // given
        String username = "te";
        String password = "password";
        String password2 = "password";


        SignupRequestDto signupRequestDto = new SignupRequestDto(
                username, password, password2
        );

        // when
        String result = userService.registerUser(signupRequestDto);

        // then
        assertEquals("유저네임을 3자 이상 입력하세요", result);
    }

    @Test
    @DisplayName("유저네임 특수문자 체크")
    void usernameChk() {

        // given
        String username = "유저네임";
        String password = "password";
        String password2 = "password";

        SignupRequestDto signupRequestDto = new SignupRequestDto(
                username, password, password2
        );

        // when
        String result = userService.registerUser(signupRequestDto);

        // then
        assertEquals("알파벳 대소문자와 숫자로만 입력하세요", result);
    }

    @Test
    @DisplayName("미밀번호 글자수 체크")
    void passwordLengthChk() {

        // given
        String username = "test";
        String password = "pas";
        String password2 = "pas";


        SignupRequestDto signupRequestDto = new SignupRequestDto(
                username, password, password2
        );

        // when
        String result = userService.registerUser(signupRequestDto);

        // then
        assertEquals("비밀번호를 4자 이상 입력하세요", result);
    }

    @Test
    @DisplayName("유저네임, 비밀번호 중복인 경우")
    void passwordUsernameChk() {

        // given
        String username = "test";
        String password = "test";
        String password2 = "test";


        SignupRequestDto signupRequestDto = new SignupRequestDto(
                username, password, password2
        );

        // when
        String result = userService.registerUser(signupRequestDto);

        // then
        assertEquals("유저네임과 비밀번호가 중복될 수 없습니다.", result);
    }
}