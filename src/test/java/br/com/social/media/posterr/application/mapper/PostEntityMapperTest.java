package br.com.social.media.posterr.application.mapper;

import br.com.social.media.posterr.adapters.datastore.entity.Post;
import br.com.social.media.posterr.adapters.datastore.entity.User;
import br.com.social.media.posterr.application.dto.PostContentDTO;
import br.com.social.media.posterr.utils.GenerateBuilders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PostEntityMapperTest {

    private PostEntityMapper mapper;
    private PostContentDTO postContentDTO;
    private User user;

    @BeforeEach
    void initial(){
        mapper = new PostEntityMapper();
        postContentDTO = GenerateBuilders.generatePostContentDTO();
        user = GenerateBuilders.generateUser();
    }

    @Test
    void mapTest(){
        Post map = mapper.map(postContentDTO, user);

        Assertions.assertNotNull(map);
        Assertions.assertNotNull(map.getUser());
        Assertions.assertEquals(postContentDTO.getContent(), map.getPostContent());
    }

    @Test
    void addCounterTest(){
        User newUser = mapper.addCounter(user);
        Assertions.assertEquals(6, newUser.getCounterPosts());
    }
}
