package com.example.miniBlog.services;

import com.example.miniBlog.model.entity.CommentEntity;
import com.example.miniBlog.model.entity.PostEntity;
import com.example.miniBlog.model.entity.UserEntity;
import com.example.miniBlog.model.form.PostForm;
import com.example.miniBlog.model.reopsitory.CommentRepository;
import com.example.miniBlog.model.reopsitory.PostRepository;
import com.example.miniBlog.model.reopsitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserContextService userContextService;

    private final CommentRepository commentRepository;

    @PostConstruct
    public void addDefaultPosts(){
                PostEntity post1 = PostEntity.builder()
                .title("Angela Merkel vs European leaders. An unusually long term for a democratically elected leader")
                .createDate(LocalDate.now())
                .content("There is one thing, ONE THING he did that was the objectively right choice: when the US asked for German support for their Iraq war he told them to fuck off and shove their manufactured evidence where the sun don't shine... diplomatically of course, but the message was quite clear. If the CxU had been in power then Germany would have 100% joined the coalition of the willing")
                .build();
        postRepository.saveAndFlush(post1);

        PostEntity post2 = PostEntity.builder()
                .title("test post nr 2")
                .createDate(LocalDate.now())
                .content("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
                .build();
        postRepository.saveAndFlush(post2);

        PostEntity post3 = PostEntity.builder()
                .title("test post nr 3")
                .createDate(LocalDate.now())
                .content(" dummy text of the printing and typesetting industry. Lorem Ipsum has been the")
                .build();
        postRepository.saveAndFlush(post3);

        PostEntity post4 = PostEntity.builder()
                .title("test post nr 4")
                .createDate(LocalDate.now())
                .content("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
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
