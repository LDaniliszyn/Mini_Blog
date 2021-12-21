package com.example.miniBlog.model.form;

import com.example.miniBlog.model.entity.UserEntity;
import lombok.Data;

@Data
public class PostForm {
    private String title;
    private String content;
}
