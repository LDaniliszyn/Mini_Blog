package com.example.miniBlog.controllers;

import com.example.miniBlog.model.form.CommentForm;
import com.example.miniBlog.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {
    final CommentService commentService;

    @PostMapping("/add-comment")
    public String publishPost(@ModelAttribute CommentForm commentForm) {
        commentService.publishNewComment(commentForm);
        return "created";
    }
}
