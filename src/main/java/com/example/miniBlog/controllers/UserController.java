package com.example.miniBlog.controllers;

import com.example.miniBlog.model.form.UserRegisterForm;
import com.example.miniBlog.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller

public class UserController {
    UserService userService;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegisterForm", new UserRegisterForm());
        return "registerForm";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserRegisterForm userRegisterForm) {
        log.info("register user: {}", userRegisterForm);
        userService.registerUser(userRegisterForm);
        return "home";
    }

    @GetMapping("/login-page")
    public String getLoginPage() {
        return "login";
    }
}
