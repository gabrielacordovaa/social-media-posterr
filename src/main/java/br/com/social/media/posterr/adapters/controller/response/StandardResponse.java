package br.com.social.media.posterr.adapters.controller.response;

import br.com.social.media.posterr.application.enums.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StandardResponse {

    private String message;
    private String userId;
    private Status status;
}
