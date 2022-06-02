package com.sparta.springBasic.model;

import com.sparta.springBasic.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Memo extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)           //컬럼 값. 값이 반드시 존재해야 함울 나타냄.
    private String title;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String contents;



    public Memo(String title, String username, String contents) {
        this.title = title;
        this.username = username;
        this.contents = contents;
    }

    //게시글 작성
    public Memo(MemoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.username = requestDto.getUsername();
    }

    public Memo(MemoRequestDto requestDto, String username) {
        this.title = requestDto.getTitle();
        this.username = username;
        this.contents = requestDto.getContents();
    }

    //게시글 수정
    public void update(MemoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }


    public Memo(MemoRequestDto requestDto, String username, String contents) {
        this.title = requestDto.getTitle();
        this.username = username;
        this.contents = contents;
    }
}
