package br.com.social.media.posterr.adapters.controller.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostInteractiveRequest {

    private Integer userId;
    private Integer postId;
    private String interaction;
}
