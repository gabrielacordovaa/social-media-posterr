package br.com.social.media.posterr.adapters.controller.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
@Builder
public class PostInteractiveRequest {

    @NotNull(message = "User Id can not be null. Please, verify the request.")
    private Integer userId;
    @NotNull(message = "Post Id can not be null. Please, verify the request.")
    private Integer postId;
    @NotNull(message = "The interaction type can not be null. Accepts: QUOTE or REPOST.")
    private String interaction;
}
