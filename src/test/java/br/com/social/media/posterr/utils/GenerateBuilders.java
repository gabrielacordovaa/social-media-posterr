package br.com.social.media.posterr.utils;

import br.com.social.media.posterr.adapters.controller.request.PostContentRequest;
import br.com.social.media.posterr.adapters.controller.response.UserResponse;
import br.com.social.media.posterr.adapters.datastore.entity.Post;
import br.com.social.media.posterr.adapters.datastore.entity.User;
import br.com.social.media.posterr.application.dto.PostContentDTO;
import br.com.social.media.posterr.application.dto.PostDTO;
import br.com.social.media.posterr.application.enums.PostType;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GenerateBuilders {

    public static PostContentRequest generatePostContentRequest(){
        return  PostContentRequest.builder()
                .userId(2)
                .content("OLA ESTOU TENTANDO ENVIAR ESSA MENSAGEM AQUI")
                .build();
    }

    public static User generateUser(){

        return User.builder()
                .id(1)
                .counterPosts(5)
                .dateJoined(LocalDateTime.parse("2022-07-24T11:48:12.34"))
                .name("Joshua")
                .posts(generatePosts())
                .build();
    }

    public static List<Post> generatePosts(){

        return Collections.singletonList(Post.builder()
                        .type(PostType.PERSONAL)
                        .postDate(LocalDateTime.now())
                        .postContent("Celebrating one year at Posterr!!")
                        .postId(3)
                        .user(User.builder()
                                .id(4)
                                .build())
                .build());

    }

    public static PostContentDTO generatePostContentDTO(){
        return PostContentDTO.builder()
                .content("MY CONTENT")
                .id(2)
                .build();
    }

    public static UserResponse generateUserResponse(){
        return UserResponse.builder()
                .postsCounter(2)
                .userName("Rafael")
                .dateJoined("July 23, 2022")
                .build();
    }

    public static PostDTO generatePostDTO(){
        return PostDTO.builder()
                .userId(1)
                .type(PostType.PERSONAL)
                .postDate(LocalDateTime.now())
                .postContent("My post.")
                .postId(3)
                .build();
    }
}
