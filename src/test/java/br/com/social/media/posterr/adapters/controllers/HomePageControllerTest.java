package br.com.social.media.posterr.adapters.controllers;

import br.com.social.media.posterr.adapters.controller.HomePageController;
import br.com.social.media.posterr.application.dto.PostDTO;
import br.com.social.media.posterr.application.enums.PostType;
import br.com.social.media.posterr.application.services.PosterrService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
 class HomePageControllerTest {

    @InjectMocks
    private HomePageController homePageController;

    @Mock
    private PosterrService posterrService;

    private List<PostDTO> dtoList;

    @BeforeEach
    void initial(){
        dtoList = new ArrayList<>();
        dtoList.add(PostDTO.builder().postId(1).userId(1).type(PostType.PERSONAL).postContent("My content").postDate(LocalDateTime.now()).build());
        dtoList.add(PostDTO.builder().postId(2).userId(1).type(PostType.REPOST).postContent("Older post").postDate(LocalDateTime.now()).build());
        dtoList.add(PostDTO.builder().postId(3).userId(2).type(PostType.PERSONAL).postContent("My content").postDate(LocalDateTime.now()).build());
    }

    @Test
    void getAllPostsOkTest(){
        when(posterrService.getAllPosts(any())).thenReturn(dtoList);
        ResponseEntity<List<PostDTO>> listResponseEntity = homePageController.getAllPosts();

        Assertions.assertEquals(200, listResponseEntity.getStatusCodeValue());
        Assertions.assertNotEquals(Collections.emptyList(), listResponseEntity.getBody());

        verify(posterrService, times(1)).getAllPosts(any());

    }

    @Test
    void getAllPostsEmptyTest(){
        when(posterrService.getAllPosts(any())).thenReturn(Collections.emptyList());
        ResponseEntity<List<PostDTO>> listResponseEntity = homePageController.getAllPosts();

        Assertions.assertEquals(204, listResponseEntity.getStatusCodeValue());
        Assertions.assertEquals(Collections.singletonList(PostDTO.builder().build()), listResponseEntity.getBody());

        verify(posterrService, times(1)).getAllPosts(any());

    }

    @Test
    void getAllPostsBetweenDatesTest(){
        when(posterrService.getAllPostsBetween(any(), anyString())).thenReturn(dtoList);

        ResponseEntity<List<PostDTO>> listResponseEntity = homePageController.getAllPostsBetweenDates("20/07/2022","24/07/2022");

        Assertions.assertEquals(200, listResponseEntity.getStatusCodeValue());
        Assertions.assertNotEquals(Collections.emptyList(), listResponseEntity.getBody());

        verify(posterrService, times(1)).getAllPostsBetween(any(), any());
    }
    @Test
    void getAllPostsBetweenDatesEmptyTest(){
        when(posterrService.getAllPostsBetween(any(), anyString())).thenReturn(Collections.emptyList());

        ResponseEntity<List<PostDTO>> listResponseEntity = homePageController.getAllPostsBetweenDates("20/07/2022","24/07/2022");

        Assertions.assertEquals(204, listResponseEntity.getStatusCodeValue());
        Assertions.assertEquals(Collections.singletonList(PostDTO.builder().build()), listResponseEntity.getBody());

        verify(posterrService, times(1)).getAllPostsBetween(any(), any());
    }

    @Test
    void getUserPostsTest(){
        when(posterrService.getPostsByUserId(any())).thenReturn(dtoList);

        ResponseEntity<List<PostDTO>> listResponseEntity = homePageController.getUserPosts(1);

        Assertions.assertEquals(200, listResponseEntity.getStatusCodeValue());
        Assertions.assertNotEquals(Collections.emptyList(), listResponseEntity.getBody());

        verify(posterrService, times(1)).getPostsByUserId(any());
    }

    @Test
    void getUserPostsEmptyTest() {
        when(posterrService.getPostsByUserId(any())).thenReturn(Collections.emptyList());

        ResponseEntity<List<PostDTO>> listResponseEntity = homePageController.getUserPosts(1);

        Assertions.assertEquals(204, listResponseEntity.getStatusCodeValue());
        Assertions.assertEquals(Collections.singletonList(PostDTO.builder().build()), listResponseEntity.getBody());

        verify(posterrService, times(1)).getPostsByUserId(any());

    }
}
