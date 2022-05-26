package com.sparta.springBasic.service;

import com.sparta.springBasic.domain.Memo;
import com.sparta.springBasic.domain.MemoRepository;
import com.sparta.springBasic.domain.MemoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository;

    //게시판 글 수정
    @Transactional     //SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public String update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (memo.getPassword().equals(requestDto.getPassword())) {
            memo.update(requestDto);
            return "수정 완료";
        }else{
            return "비밀번호 불일치";
        }
    }

    //게시판 글 삭제
    @Transactional      //SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public String deleteMemo(Long id, MemoRequestDto requestDto) {
        Optional<Memo> memo = memoRepository.findById(id);
        if(memo.get().getPassword().equals(requestDto.getPassword())){
            memoRepository.deleteById(id);
            return "삭제 성공";
        }
        return "비밀번호 불일치";
    }
}



