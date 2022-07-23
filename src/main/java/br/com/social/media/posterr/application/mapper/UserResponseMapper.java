package br.com.social.media.posterr.application.mapper;

import br.com.social.media.posterr.adapters.controller.response.UserResponse;
import br.com.social.media.posterr.adapters.datastore.entity.User;
import br.com.social.media.posterr.utils.Utilities;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {

    public UserResponse fromEntity(User user){
        return UserResponse.builder()
                .userName(user.getName())
                .dateJoined(Utilities.formatDate(user.getDateJoined()))
                .postsCounter(user.getCounterPosts())
                .build();
    }
}
