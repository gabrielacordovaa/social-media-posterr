package br.com.social.media.posterr.application.dto;

import br.com.social.media.posterr.application.enums.PostType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostContentDTO {

    private Integer userId;
    private String content;
    private PostType type;
}
