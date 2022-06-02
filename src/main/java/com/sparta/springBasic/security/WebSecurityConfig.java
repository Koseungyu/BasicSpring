package com.sparta.springBasic.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration     // 빈 설정하려면 써야하는 어노테이션.
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean   // 비밀번호 암호화
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()

                // image 폴더를 login 없이 허용
                .antMatchers("/images/**").permitAll()
                // css 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()

                // 메인페이지 접근허용
                .antMatchers("/index/**").permitAll()
                // 글 조회 접근허용
                .antMatchers("/detail.html/**").permitAll()
                // api 요청 접근허용
                .antMatchers("/api/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/**").permitAll()
                // 전부 허용
                .antMatchers("*").permitAll()

                // 그 외 모든 요청은 인증과정 필요
                .anyRequest().authenticated()
                .and()
                // 로그인 기능
                .formLogin()
                // 로그인 view 제공(GET)
                .loginPage("/user/login")
                // 로그인 처리(POST)
                .loginProcessingUrl("/user/login")                            //클라이언트 쪽에서 무조건 username과 password로 보내달라고 요청해야함. 규율에 따른것.
                // 로그인 처리 후 성공 시 url                                     //확인필요
                .defaultSuccessUrl("/index.html")
                // 로그인 처리 후 실패 시 url
                .failureUrl("/user/login/error")
                .permitAll()
                .and()
                // 로그아웃 기능
                .logout()
                // 로그아웃 URL
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/")
                .permitAll();
    }
}