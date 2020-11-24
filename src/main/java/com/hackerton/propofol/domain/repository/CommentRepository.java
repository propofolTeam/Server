package com.hackerton.propofol.domain.repository;

import com.hackerton.propofol.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Integer countByPostId(Long postId);
}
