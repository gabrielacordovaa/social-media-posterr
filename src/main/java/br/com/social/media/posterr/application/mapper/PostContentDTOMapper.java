package br.com.social.media.posterr.application.mapper;

import br.com.social.media.posterr.adapters.controller.request.PostContentRequest;
import br.com.social.media.posterr.application.dto.PostContentDTO;
import org.springframework.stereotype.Component;

@Component
public class PostContentDTOMapper {

    public PostContentDTO map(PostContentRequest postContentRequest){
        return PostContentDTO.builder()
                .id(postContentRequest.getUserId())
                .content(postContentRequest.getContent())
                .build();
    }
}
