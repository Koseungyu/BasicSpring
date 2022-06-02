package com.sparta.springBasic.service;

import com.sparta.springBasic.model.Memo;
import com.sparta.springBasic.repository.MemoRepository;
import com.sparta.springBasic.dto.MemoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public Memo createContents(MemoRequestDto requestDto, String username) {
        String memoCheck = requestDto.getContents();
        String titleCheck = requestDto.getTitle();
        if (memoCheck.contains("script") || memoCheck.contains("<") || memoCheck.contains(">")) {
            Memo memo = new Memo(requestDto, username, "평화");
            memoRepository.save(memo);
            return memo;
        }
        if (titleCheck.contains("script") || titleCheck.contains("<") || titleCheck.contains(">")) {
            Memo memo = new Memo("평화", username, "사랑");
            memoRepository.save(memo);
            return memo;
        }
        Memo memo = new Memo(requestDto, username);
        memoRepository.save(memo);
        return memo;
    }


    //게시판 글 수정
    @Transactional     //SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public Long update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
            memo.update(requestDto);
            return memo.getId();
        }

//    //게시판 글 삭제
//    @Transactional      //SQL 쿼리가 일어나야 함을 스프링에게 알려줌
//    public String deleteMemo(Long id, MemoRequestDto requestDto) {
//        Memo memo = memoRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
//        memoRepository.deleteById(id);
//        return "삭제 성공";
//    }
}



