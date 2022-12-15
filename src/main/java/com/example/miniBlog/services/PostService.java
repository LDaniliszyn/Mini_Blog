package com.example.miniBlog.services;

import com.example.miniBlog.model.entity.CommentEntity;
import com.example.miniBlog.model.entity.PostEntity;
import com.example.miniBlog.model.entity.UserEntity;
import com.example.miniBlog.model.form.PostForm;
import com.example.miniBlog.model.reopsitory.PostRepository;
import com.example.miniBlog.model.reopsitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserContextService userContextService;

    @PostConstruct
    public void addDefaultPosts(){
        PostEntity post1 = PostEntity.builder()
                .title("test post nr 1")
                .createDate(LocalDate.now())
                .content("test post content")
                .build();
        postRepository.saveAndFlush(post1);

        PostEntity post2 = PostEntity.builder()
                .title("test post nr 2")
                .createDate(LocalDate.now())
                .content("test post content2")
                .build();
        postRepository.saveAndFlush(post2);
    }

    public boolean publishNewPost(PostForm postForm) {
        final Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(userContextService.getLoggedName());
        final UserEntity userEntity = optionalUserEntity.orElseGet(() -> UserEntity.builder().build());
        PostEntity post = PostEntity.builder()
                .title(postForm.getTitle())
                .createDate(LocalDate.now())
                .author(userEntity)
                .content(postForm.getContent())
                .build();
        postRepository.saveAndFlush(post);
        return true;
    }


    public List<PostEntity> getPosts() {
        List<PostEntity> all = postRepository.findAll();
        return all;
    }

    public PostEntity getPost(Long id) {
        return postRepository.getById(id);
    }

    public List<CommentEntity> getComments(Long id){
        return postRepository.getById(id).getComment();
    }

}
