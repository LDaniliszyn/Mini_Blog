package com.example.miniBlog.controllers;

import com.example.miniBlog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class MainController {
    final PostService postService;

    @GetMapping(value = {"/home", "/", "/index"})
    public String getHome(Model model) {
        model.addAttribute("posts", postService.getPosts());
        return "home";
    }
}
