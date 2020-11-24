package com.hackerton.propofol.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostFile {

    @Id
    private String fileId;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @OneToOne
    @JoinColumn(name = "id")
    private Post post;

    public PostFile updateFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
}
