package br.com.social.media.posterr.application.mapper;

import br.com.social.media.posterr.adapters.datastore.entity.Post;
import br.com.social.media.posterr.adapters.datastore.entity.User;
import br.com.social.media.posterr.application.dto.PostContentDTO;
import br.com.social.media.posterr.application.enums.PostType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class PostEntityMapper {

    public Post map(PostContentDTO contentDTO, User user){
        return Post.builder()
                .postId(0)
                .postContent(contentDTO.getContent())
                .user(addCounter(user))
                .postDate(LocalDateTime.now())
                .type(PostType.PERSONAL)
                .build();
    }

    public User addCounter(User user){
        user.setCounterPosts(user.getCounterPosts() + 1);
        return user;
    }
}
