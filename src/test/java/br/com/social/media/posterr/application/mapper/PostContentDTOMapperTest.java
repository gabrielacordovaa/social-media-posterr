package br.com.social.media.posterr.application.mapper;

import br.com.social.media.posterr.adapters.controller.request.PostContentRequest;
import br.com.social.media.posterr.application.dto.PostContentDTO;
import br.com.social.media.posterr.utils.GenerateBuilders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PostContentDTOMapperTest {

    private PostContentDTOMapper mapper;
    private PostContentRequest postContentRequest;

    @BeforeEach
    void initial(){
        mapper = new PostContentDTOMapper();
        postContentRequest = GenerateBuilders.generatePostContentRequest();
    }

    @Test
    void mapTest(){
        PostContentDTO map = mapper.map(postContentRequest);

        Assertions.assertNotNull(map);

        Assertions.assertEquals(postContentRequest.getUserId(), map.getId());
        Assertions.assertEquals(postContentRequest.getContent(), map.getContent());
    }

    @Test
    void mapContentNullTest(){
        postContentRequest.setContent(null);
        PostContentDTO map = mapper.map(postContentRequest);

        Assertions.assertNotNull(map);

        Assertions.assertEquals(postContentRequest.getUserId(), map.getId());
        Assertions.assertNull(map.getContent());
    }

    @Test
    void mapIdNullTest(){
        postContentRequest.setUserId(null);
        PostContentDTO map = mapper.map(postContentRequest);

        Assertions.assertNotNull(map);

        Assertions.assertEquals(postContentRequest.getContent(), map.getContent());
        Assertions.assertNull(map.getId());
    }
}
