package br.com.social.media.posterr.adapters.controllers;

import br.com.social.media.posterr.adapters.controller.PosterrController;
import br.com.social.media.posterr.adapters.controller.request.PostContentRequest;
import br.com.social.media.posterr.adapters.controller.request.PostInteractiveRequest;
import br.com.social.media.posterr.adapters.controller.response.StandardResponse;
import br.com.social.media.posterr.application.dto.PostContentDTO;
import br.com.social.media.posterr.application.enums.Status;
import br.com.social.media.posterr.application.mapper.PostContentDTOMapper;
import br.com.social.media.posterr.application.services.PosterrService;
import br.com.social.media.posterr.utils.GenerateBuilders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PosterrControllerTest {

    @InjectMocks
    private PosterrController posterrController;
    @Mock
    private PosterrService posterrService;
    @Mock
    private PostContentDTOMapper postContentDTOMapper;

    private PostContentRequest postContentRequest;
    private PostContentDTO contentDTO;
    private PostInteractiveRequest postInteractiveRequest;

    @BeforeEach
    void initial() {
        postContentRequest = GenerateBuilders.generatePostContentRequest();
        contentDTO = GenerateBuilders.generatePostContentDTO();
        postInteractiveRequest = GenerateBuilders.generatePostInteractiveRequest();
    }

    @Test
    void postPersonalContentAbleToPostTest() {
        when(posterrService.isUserAbleToPost(any())).thenReturn(true);
        when(postContentDTOMapper.map(any())).thenReturn(contentDTO);
        doNothing().when(posterrService).postPersonalContent(any());

        ResponseEntity<StandardResponse> response = posterrController.postPersonalContent(postContentRequest);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertNotEquals(StandardResponse.builder().build(), response.getBody());

        verify(posterrService, times(1)).isUserAbleToPost(any());
        verify(postContentDTOMapper, times(1)).map(any());
        verify(posterrService, times(1)).postPersonalContent(any());
    }

    @Test
    void postPersonalContentNotAbleToPostTest() {
        when(posterrService.isUserAbleToPost(any())).thenReturn(false);
        ResponseEntity<StandardResponse> response = posterrController.postPersonalContent(postContentRequest);

        Assertions.assertEquals(400, response.getStatusCodeValue());
        Assertions.assertNotEquals(StandardResponse.builder().build(), response.getBody());
        verify(posterrService, times(1)).isUserAbleToPost(any());
    }

    @Test
    void postInteractUserIsAbleTest() {
        when(posterrService.isUserAbleToPost(any())).thenReturn(true);
        when(posterrService.postInteraction(any())).thenReturn(GenerateBuilders.generatePostDTO());

        ResponseEntity<StandardResponse> response = posterrController.postInteract(postInteractiveRequest);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertNotEquals(StandardResponse.builder().build(), response.getBody());

        verify(posterrService, times(1)).postInteraction(any());
        verify(posterrService, times(1)).isUserAbleToPost(any());

    }

    @Test
    void postInteractUserIsAbleButFailedTest() {
        when(posterrService.isUserAbleToPost(any())).thenReturn(true);
        when(posterrService.postInteraction(any())).thenReturn(null);

        ResponseEntity<StandardResponse> response = posterrController.postInteract(postInteractiveRequest);

        Assertions.assertEquals(204, response.getStatusCodeValue());
        Assertions.assertEquals(Status.FAILED, response.getBody().getStatus());

        verify(posterrService, times(1)).postInteraction(any());
        verify(posterrService, times(1)).isUserAbleToPost(any());

    }

    @Test
    void postInteractUserIsNotAbleTest() {
        when(posterrService.isUserAbleToPost(any())).thenReturn(false);

        ResponseEntity<StandardResponse> response = posterrController.postInteract(postInteractiveRequest);

        Assertions.assertEquals(400, response.getStatusCodeValue());
        Assertions.assertNotEquals(StandardResponse.builder().build(), response.getBody());
        verify(posterrService, times(1)).isUserAbleToPost(any());
    }
}
