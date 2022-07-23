package br.com.social.media.posterr.application.dto;

import br.com.social.media.posterr.adapters.datastore.entity.User;
import br.com.social.media.posterr.application.enums.PostType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostDTO {

    private Integer postId;
    private LocalDateTime postDate;
    private String postContent;
    private PostType type;
    private Integer userId;
}
