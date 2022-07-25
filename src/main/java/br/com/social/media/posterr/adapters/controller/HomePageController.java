package br.com.social.media.posterr.adapters.controller;

import br.com.social.media.posterr.application.dto.PostDTO;
import br.com.social.media.posterr.application.services.PosterrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/home-page/v1")
@RequiredArgsConstructor
public class HomePageController {

    private static final Integer size = 10;
    private final PosterrService posterrService;
    @GetMapping(value = "/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        List<PostDTO> posts = posterrService.getAllPosts(size);
        if(!posts.isEmpty()){
            return ResponseEntity.ok(posts);
        }
        else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                                 .body(Collections.singletonList(PostDTO.builder().build())
                                 );
        }
    }

    @GetMapping(value = "/posts/date-range")
    public ResponseEntity<List<PostDTO>> getAllPostsBetweenDates(@RequestParam(name = "start", required = false) String startDate, @RequestParam(name = "end", required = false) String endDate){
        List<PostDTO> posts = posterrService.getAllPostsBetween(startDate, endDate);
        if(!posts.isEmpty()){
            return ResponseEntity.ok(posts);
        }
        else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(Collections.singletonList(PostDTO.builder().build())
                    );
        }
    }
    @GetMapping(value = "posts/{userId}")
    public ResponseEntity<List<PostDTO>> getUserPosts(@PathVariable(value = "userId") Integer userId){
        List<PostDTO> posts = posterrService.getPostsByUserId(userId);
        if(!posts.isEmpty()){
            return ResponseEntity.ok(posts);
        }
        else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(Collections.singletonList(PostDTO.builder().build())
                    );
        }
    }
}
