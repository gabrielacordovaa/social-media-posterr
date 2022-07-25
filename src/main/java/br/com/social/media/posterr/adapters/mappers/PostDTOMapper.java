package br.com.social.media.posterr.adapters.mappers;

import br.com.social.media.posterr.adapters.datastore.entity.Post;
import br.com.social.media.posterr.application.dto.PostDTO;
import org.springframework.stereotype.Component;

@Component
public class PostDTOMapper {

    public PostDTO map(Post post){
        return PostDTO.builder()
                .postId(post.getPostId())
                .postContent(post.getPostContent())
                .postDate(post.getPostDate())
                .type(post.getType())
                .userId(post.getUser().getId())
                .build();
    }
}
