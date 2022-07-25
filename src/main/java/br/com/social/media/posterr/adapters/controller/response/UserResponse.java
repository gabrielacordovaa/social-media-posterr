package br.com.social.media.posterr.adapters.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private String userName;
    private String dateJoined;
    private Integer postsCounter;

}
