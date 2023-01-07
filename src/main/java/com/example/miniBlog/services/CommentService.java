package com.example.miniBlog.services;

import com.example.miniBlog.model.entity.CommentEntity;
import com.example.miniBlog.model.entity.PostEntity;
import com.example.miniBlog.model.entity.UserEntity;
import com.example.miniBlog.model.form.CommentForm;
import com.example.miniBlog.model.reopsitory.CommentRepository;
import com.example.miniBlog.model.reopsitory.PostRepository;
import com.example.miniBlog.model.reopsitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserContextService userContextService;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public boolean publishNewComment(CommentForm commentForm) {
        final Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(userContextService.getLoggedName());
        final UserEntity userEntity = optionalUserEntity.orElseGet(() -> UserEntity.builder().build());

        final PostEntity postEntity = postRepository.getById(commentForm.getPostId());

        CommentEntity comment = CommentEntity.builder()
                .createDate(LocalDate.now())
                .author(userEntity)
                .content(commentForm.getContent())
                .build();
        commentRepository.saveAndFlush(comment);

        postEntity.addComment(comment);

        postRepository.save(postEntity);
        return true;
    }
}
