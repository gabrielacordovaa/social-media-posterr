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
        log.info("Posterr - request to post personal content.");
        if (posterrService.isUserAbleToPost(postContentRequest.getUserId())) {

            posterrService.postPersonalContent(postContentDTOMapper.map(postContentRequest));

            log.info("User's post was completed.");
            return ResponseEntity.ok(StandardResponse.builder().status(Status.SUCCESS).message("Successful Publication!!").userId(postContentRequest.getUserId()).build());
        } else {
            log.info("User can not post anymore for today.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(StandardResponse.builder().status(Status.FAILED).message("The user has exceeded the daily publication limit.").userId(postContentRequest.getUserId()).build());
        }
    }

    @PostMapping(value = "/post/interact")
    public ResponseEntity<StandardResponse> postInteract(@Valid @RequestBody PostInteractiveRequest postInteractive) {
        log.info("Posterr - request to quote or repost content.");
        if (posterrService.isUserAbleToPost(postInteractive.getUserId())) {

            PostDTO post = posterrService.postInteraction(postInteractive);

            if (post != null) {
                log.info("User's post was completed.");
                return ResponseEntity.ok(StandardResponse.builder().status(Status.SUCCESS).message("Successful Publication!!").userId(postInteractive.getUserId()).build());
            } else {
                log.info("Posterr - request to quote or repost content invalid. Verify the action and the post type.");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(StandardResponse.builder().status(Status.FAILED).message("This action is not valid! Please verify.").userId(postInteractive.getUserId()).build());
            }
        } else {
            log.info("User can not post anymore for today.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(StandardResponse.builder().status(Status.FAILED).message("The user has exceeded the daily publication limit.").userId(postInteractive.getUserId()).build());
        }
    }
}
