package com.sparta.springBasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing       //생성, 수정시간이 바뀌었을때 자동으올 업데이트 됨.
@SpringBootApplication
public class BasicSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicSpringApplication.class, args);
	}

}
