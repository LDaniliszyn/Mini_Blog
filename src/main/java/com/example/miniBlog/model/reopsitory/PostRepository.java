package com.example.miniBlog.model.reopsitory;

import com.example.miniBlog.model.entity.CommentEntity;
import com.example.miniBlog.model.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
