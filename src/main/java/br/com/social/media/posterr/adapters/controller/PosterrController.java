package br.com.social.media.posterr.adapters.controller;

import br.com.social.media.posterr.adapters.controller.request.PostContentRequest;
import br.com.social.media.posterr.adapters.controller.request.PostInteractiveRequest;
import br.com.social.media.posterr.adapters.controller.response.StandardResponse;
import br.com.social.media.posterr.application.dto.PostDTO;
import br.com.social.media.posterr.application.enums.Status;
import br.com.social.media.posterr.application.mapper.PostContentDTOMapper;
import br.com.social.media.posterr.application.services.PosterrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Log4j2
@RequestMapping("/posterr/v1")
@RequiredArgsConstructor
public class PosterrController {
    private final PosterrService posterrService;
    private final PostContentDTOMapper postContentDTOMapper;

    @PostMapping(value = "/post/personal")
    public ResponseEntity<StandardResponse> postPersonalContent(@Valid @RequestBody PostContentRequest postContentRequest) {
        if (posterrService.isUserAbleToPost(postContentRequest.getUserId())) {

            posterrService.postPersonalContent(postContentDTOMapper.map(postContentRequest));

            return ResponseEntity.ok(StandardResponse.builder().status(Status.SUCCESS).message("Successful Publication!!").userId(postContentRequest.getUserId()).build());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(StandardResponse.builder().status(Status.FAILED).message("The user has exceeded the daily publication limit.").userId(postContentRequest.getUserId()).build());


        }
    }

    @PostMapping(value = "/post/interact")
    public ResponseEntity<StandardResponse> postInteract(@Valid @RequestBody PostInteractiveRequest postInteractive) {
        if (posterrService.isUserAbleToPost(postInteractive.getUserId())) {

            PostDTO post = posterrService.postInteraction(postInteractive);

            if (post != null) {
                return ResponseEntity.ok(StandardResponse.builder().status(Status.SUCCESS).message("Successful Publication!!").userId(postInteractive.getUserId()).build());
            } else {
                return ResponseEntity.ok(StandardResponse.builder().status(Status.FAILED).message("This action is not valid! Please verify.").userId(postInteractive.getUserId()).build());
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(StandardResponse.builder().status(Status.FAILED).message("The user has exceeded the daily publication limit.").userId(postInteractive.getUserId()).build());
        }
    }
}
