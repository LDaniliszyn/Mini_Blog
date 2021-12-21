package com.example.miniBlog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@SuperBuilder
@AllArgsConstructor
@Table(name = "POST")
public class PostEntity extends BaseEntity {
    private String title;
    private String content;
    @ManyToOne
    private UserEntity author;
}
