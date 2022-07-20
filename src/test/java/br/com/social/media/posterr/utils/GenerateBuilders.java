package br.com.social.media.posterr.utils;

import br.com.social.media.posterr.adapters.controller.request.PostContentRequest;
import br.com.social.media.posterr.adapters.datastore.entity.Post;
import br.com.social.media.posterr.adapters.datastore.entity.User;
import br.com.social.media.posterr.application.dto.PostContentDTO;
import br.com.social.media.posterr.application.enums.PostType;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GenerateBuilders {

    public static PostContentRequest generatePostContentRequest(){
        return  PostContentRequest.builder()
                .userId("2891821921")
                .content("OLA ESTOU TENTANDO ENVIAR ESSA MENSAGEM AQUI")
                .build();
    }

    public static User generateUser(){

        return User.builder()
                .id("1")
                .counterPosts(5)
                .dateJoined(new Date())
                .name("Joshua")
                .posts(generatePosts())
                .build();
    }

    public static List<Post> generatePosts(){
        java.util.Date data = new java.util.Date();

        return Collections.singletonList(Post.builder()
                        .type(PostType.PERSONAL)
                        .postDate(data)
                        .postContent("Celebrating one year at Posterr!!")
                        .postId("1213232")
                        .user(User.builder()
                                .id("1")
                                .build())
                .build());

    }

    public static PostContentDTO generatePostContentDTO(){
        return PostContentDTO.builder()
                .content("MY CONTENT")
                .id("2")
                .build();
    }
}
