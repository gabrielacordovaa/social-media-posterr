package br.com.social.media.posterr.application.mapper;

import br.com.social.media.posterr.adapters.controller.request.PostContentRequest;
import br.com.social.media.posterr.application.dto.PostContentDTO;
import br.com.social.media.posterr.application.enums.PostType;
import org.springframework.stereotype.Component;

@Component
public class PostContentDTOMapper {

    public PostContentDTO map(PostContentRequest postContentRequest){
        return PostContentDTO.builder()
                .userId(postContentRequest.getUserId())
                .content(postContentRequest.getContent())
                .type(PostType.PERSONAL)
                .build();
    }
}
