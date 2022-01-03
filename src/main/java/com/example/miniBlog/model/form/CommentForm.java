package com.example.miniBlog.model.form;

import lombok.Data;

@Data
public class CommentForm {
    private String content;
    private Long postId;
}
