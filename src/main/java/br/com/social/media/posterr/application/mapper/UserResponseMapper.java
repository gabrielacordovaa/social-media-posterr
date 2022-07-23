package br.com.social.media.posterr.application.mapper;

import br.com.social.media.posterr.adapters.controller.response.UserResponse;
import br.com.social.media.posterr.adapters.datastore.entity.User;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class UserResponseMapper {

    public UserResponse fromEntity(User user){
        return UserResponse.builder()
                .userName(user.getName())
                //.dateJoined(formatDate(user.getDateJoined()))
                .postsCounter(user.getCounterPosts())
                .build();
    }

    public String formatDate(Date data){
        Locale local = new Locale("EN","US");
        DateFormat formatter = new SimpleDateFormat("MMMM dd ',' yyyy", local);
        return formatter.format(data);
    }
}
