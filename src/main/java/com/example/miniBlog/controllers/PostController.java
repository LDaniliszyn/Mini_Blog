package com.example.miniBlog.controllers;

import com.example.miniBlog.model.form.CommentForm;
import com.example.miniBlog.model.form.PostForm;
import com.example.miniBlog.services.CommentService;
import com.example.miniBlog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class PostController {
    final PostService postService;
    final CommentService commentService;


    @GetMapping("/add-post")
    public String getPostForm(Model model) {
        model.addAttribute("newPost", new PostForm());
        return "newPost";
    }

    @PostMapping("/add-post")
    public String publishPost(@ModelAttribute PostForm postForm) {
        postService.publishNewPost(postForm);
        return "created";
    }

    @GetMapping("/get-post/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        model.addAttribute("comments", postService.getComments(id));
        model.addAttribute("newComment", new CommentForm());
        return "post";
    }

}
