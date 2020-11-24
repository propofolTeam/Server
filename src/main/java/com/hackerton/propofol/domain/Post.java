package com.hackerton.propofol.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String fileId;

    private String fileName;

    private String createdAt;

    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Post updateFile(String fileId, String fileName) {
        this.fileId = fileId;
        this.fileName = fileName;

        return this;
    }

}
