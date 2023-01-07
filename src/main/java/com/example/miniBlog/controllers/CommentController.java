package com.example.miniBlog.controllers;

import com.example.miniBlog.model.form.CommentForm;
import com.example.miniBlog.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class CommentController {
    final CommentService commentService;

    @PostMapping("/add-comment")
    public ModelAndView publishPost(@ModelAttribute CommentForm commentForm) {
        commentService.publishNewComment(commentForm);
        Long postId = commentForm.getPostId();
        return new ModelAndView("redirect:/get-post/"+postId);
    }
}
