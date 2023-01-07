package com.example.miniBlog.controllers;

import com.example.miniBlog.model.form.UserRegisterForm;
import com.example.miniBlog.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public ModelAndView registerUser(@ModelAttribute UserRegisterForm userRegisterForm) {
        log.info("register user: {}", userRegisterForm);
        userService.registerUser(userRegisterForm);
        return new ModelAndView("redirect:/login-page");
    }

    @GetMapping("/login-page")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public ModelAndView logoutPage(final HttpServletRequest request, final HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ModelAndView("redirect:/home");    }

    @GetMapping("/user")
    public String userPage(){
        return "user";
    }
}
