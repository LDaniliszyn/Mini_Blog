package com.example.miniBlog.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ROLE")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30, unique = true)
    private String roleName;


    public RoleEntity(String roleName) {
        this.roleName = roleName;
    }

    public RoleEntity() {
    }
}
