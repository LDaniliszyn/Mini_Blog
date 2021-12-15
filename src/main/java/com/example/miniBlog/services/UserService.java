package com.example.miniBlog.services;

import com.example.miniBlog.model.entity.RoleEntity;
import com.example.miniBlog.model.entity.UserEntity;
import com.example.miniBlog.model.form.UserRegisterForm;
import com.example.miniBlog.model.reopsitory.RoleRepository;
import com.example.miniBlog.model.reopsitory.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String ROLE_USER = "ROLE_USER";

    private void setOrCreateDefaultRole(final UserEntity userEntity){
        RoleEntity roleEntity = roleRepository.findByRoleName(ROLE_USER).orElseGet(() -> roleRepository.save(new RoleEntity(ROLE_USER)));
        userEntity.addToRoleSet(roleEntity);
    }


    public void registerUser(final UserRegisterForm userRegisterForm) {
        final var userEntity = UserEntity.builder()
                .email(userRegisterForm.getEmail())
                .firstName(userRegisterForm.getFirstName())
                .lastName(userRegisterForm.getLastName())
                .password(passwordEncoder.encode(userRegisterForm.getPassword()))
                .createDate(LocalDate.now())
                .build();
        setOrCreateDefaultRole(userEntity);
        log.info("save user\n{}",userEntity );
        userRepository.save(userEntity);
    }
}