package br.com.social.media.posterr.adapters.controller;

import br.com.social.media.posterr.adapters.controller.request.PostContentRequest;
import br.com.social.media.posterr.adapters.controller.response.StandardResponse;
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

    @PostMapping(value = "/personal/post")
    public ResponseEntity<StandardResponse> postPersonalContent(@Valid @RequestBody PostContentRequest postContentRequest){
        if(posterrService.isUserAbleToPost(postContentRequest.getUserId())){

            try{

                posterrService.postPersonalContent(
                        postContentDTOMapper.map(
                                postContentRequest));

                return ResponseEntity.ok(StandardResponse.builder()
                        .status(Status.SUCCESS)
                        .message("Successful Publication!!")
                        .userId(postContentRequest.getUserId())
                        .build());
            }
            catch(Exception e){
                ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(StandardResponse.builder()
                                .status(Status.FAILED)
                                .message("The user has exceeded the daily publication limit.")
                                .userId(postContentRequest.getUserId())
                                .build());
            }
        }
        return null;
    }
}
