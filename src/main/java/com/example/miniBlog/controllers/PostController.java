package com.example.miniBlog.controllers;

import com.example.miniBlog.model.form.PostForm;
import com.example.miniBlog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostController {
    final PostService postService;
    // TODO: 20.12.2021 wyswietlanie postow na str glownej
    // TODO: 20.12.2021 dodawanie kom

    @GetMapping("/add-post")
    public String getPostForm(Model model){
        model.addAttribute("newPost",new PostForm());
        return "newPost";
    }

    @PostMapping("/add-post")
    public String publishPost(@ModelAttribute PostForm postForm){
        postService.publishNewPost(postForm);
        return "created";
    }
}
