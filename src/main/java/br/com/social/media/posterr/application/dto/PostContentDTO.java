package br.com.social.media.posterr.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostContentDTO {

    private Integer id;
    private String content;
}
