package br.com.social.media.posterr.adapters.mappers;

import br.com.social.media.posterr.adapters.datastore.entity.Post;
import br.com.social.media.posterr.application.dto.PostDTO;
import br.com.social.media.posterr.utils.GenerateBuilders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 class PostDTOMapperTest {

    private PostDTOMapper mapper;
    private Post post;

    @BeforeEach
    void initial(){
        mapper = new PostDTOMapper();
        post = GenerateBuilders.generatePosts().get(0);
    }

    @Test
    void mapTest(){
        PostDTO mapped = mapper.map(post);

        Assertions.assertNotNull(mapped);
        Assertions.assertEquals(post.getPostId(), mapped.getPostId());
        Assertions.assertEquals(post.getPostContent(), mapped.getPostContent());
        Assertions.assertEquals(post.getType(), mapped.getType());
        Assertions.assertEquals(post.getUser().getId(), mapped.getUserId());
        Assertions.assertEquals(post.getPostDate(), mapped.getPostDate());

    }
}