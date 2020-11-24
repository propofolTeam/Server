package com.hackerton.propofol.domain.repository;

import com.hackerton.propofol.domain.PostFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostFileRepository extends JpaRepository<PostFile, Long> {
    PostFile findByPostId(Long postId);
}
