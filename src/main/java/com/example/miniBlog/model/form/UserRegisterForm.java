package com.example.miniBlog.model.form;

import lombok.Data;

@Data
public class UserRegisterForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
