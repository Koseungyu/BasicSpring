package com.sparta.springBasic.controller;

import com.sparta.springBasic.model.Memo;
import com.sparta.springBasic.repository.MemoRepository;
import com.sparta.springBasic.dto.MemoRequestDto;
import com.sparta.springBasic.security.UserDetailsImpl;
import com.sparta.springBasic.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemoController {

    private final MemoRepository memoRepository;
    private final MemoService memoService;

    // 게시판 전체 조회
    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByCreatedAtDesc();
    }

    // 게시판 특정 조회
    @GetMapping("/api/memos/{id}")
    public Memo getMemos(@PathVariable Long id) {
        Memo memo =  memoRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("아이디가 존재하지 않습니다."));
        return memo;
    }

    // 게시판 글 생성
    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto,  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        Memo memo = memoService.createContents(requestDto, username);
        return memo;
    }


    // 게시판 특정 글 수정
    @PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        memoService.update(id, requestDto);
        return id;
    }

    // 게시판 특정 글 삭제
    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        memoRepository.deleteById(id);
        return id;
    }
}

//    // 게시판 특정 글 삭제
//    @DeleteMapping("/api/memos/{id}")
//    public Long deleteMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
//        Memo memo = memoRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//        Long a = 0L;
//        if(memo.getPassword().equals(requestDto.getPassword())){
//            memoRepository.deleteById(id);
//            return id;
//        }
//        return a;
//    }
//}

    // 게시판 특정 글 삭제
//    @DeleteMapping("/api/memos/{id}")
//    public String deleteMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
//        Optional<Memo>  memo = memoRepository.findById(id);
//        if(memo.get().getPassword().equals(requestDto.getPassword())){
//            memoRepository.deleteById(id);
//            return "삭제 성공";
//        }
//        return "비밀번호 불일치";
//    }

// 게시판 특정 글 수정
//    @PutMapping("/api/memos/{id}")
//    public String updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
//        Optional<Memo>  memo = memoRepository.findById(id);
//        if(memo.getPassword().equals(requestDto.getPassword())){
//            memoService.update(id, requestDto);
//            return "수정 완료";
//        }
//        return "비밀번호 불일치";
//    }
//

//    // 게시판 특정 글 수정
//    @PutMapping("/api/memos/{id}")
//    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
//        Memo memo = memoRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//        if(memo.getPassword().equals(requestDto.getPassword())){
//            memoService.update(id, requestDto);
//        }
//        return id;
//    }