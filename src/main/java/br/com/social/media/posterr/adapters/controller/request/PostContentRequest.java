package br.com.social.media.posterr.adapters.controller.request;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class PostContentRequest {

    @NotNull(message = "User Id can not be null. Please, verify the request.")
    private Integer userId;

    @Length(max = 777, message = "777 is the maximum number of characters allowed.")
    private String content;
}
