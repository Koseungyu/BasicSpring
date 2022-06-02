package com.sparta.springBasic.service;

import com.sparta.springBasic.model.Users;
import com.sparta.springBasic.dto.SignupRequestDto;
import com.sparta.springBasic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(SignupRequestDto requestDto) {
        String error = "";
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String password2 = requestDto.getPassword2();
        // 회원 ID 중복 확인
        Optional<Users> found = userRepository.findByUsername(username);

        String pattern = "^[a-zA-Z0-9]*$";

        if (found.isPresent()) {
            return "중복된 id 입니다.";
        }
        if (username.length() < 3) {
            return "유저네임을 3자 이상 입력하세요";
        } else if (!Pattern.matches(pattern, username)) {
            return "알파벳 대소문자와 숫자로만 입력하세요";
        } else if (!password.equals(password2)) {
            return "비밀번호가 일치하지 않습니다";
        } else if (password.length() < 4) {
            return "비밀번호를 4자 이상 입력하세요";
        } else if (password.contains(username)) {
            return "유저네임과 비밀번호가 중복될 수 없습니다.";
        }
        // 패스워드 인코딩
        password = passwordEncoder.encode(password);
        requestDto.setPassword(password);

        Users user = new Users(username, password);
        userRepository.save(user);
        return error;
    }
}