package com.sparta.springBasic.repository;

import com.sparta.springBasic.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    //    List<Reply> findAllByPostid(Long postId);
    List<Reply> findAllByPostidOrderByCreatedAtDesc(Long postId);
}