package br.com.social.media.posterr.application.mapper;

import br.com.social.media.posterr.adapters.controller.response.UserResponse;
import br.com.social.media.posterr.adapters.datastore.entity.User;
import br.com.social.media.posterr.utils.GenerateBuilders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserResponseMapperTest {

    private UserResponseMapper mapper;

    private User user;

    @BeforeEach
    void initial(){
        mapper = new UserResponseMapper();
        user = GenerateBuilders.generateUser();
    }

    @Test
    void mapTest(){
        UserResponse map = mapper.fromEntity(user);

        Assertions.assertNotNull(map);
        Assertions.assertEquals(user.getName(), map.getUserName());
        Assertions.assertEquals(user.getCounterPosts(), map.getPostsCounter());
        //Assertions.assertEquals(mapper.formatDate(user.getDateJoined()), map.getDateJoined());

    }
}
