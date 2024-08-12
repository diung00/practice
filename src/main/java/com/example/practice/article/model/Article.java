package com.example.practice.article.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.ClientInfoStatus;
import java.util.List;

@Getter
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String writer;

    @Setter
    @OneToMany (mappedBy = "article")
    private List<Comment> comments;


    public Article() {}

    public Article(String content, String writer, String title) {
        this.content = content;
        this.writer = writer;
        this.title = title;
    }
}

