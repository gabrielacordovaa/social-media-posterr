package br.com.social.media.posterr.adapters.controller;

import br.com.social.media.posterr.adapters.datastore.entity.Post;
import br.com.social.media.posterr.application.dto.PostDTO;
import br.com.social.media.posterr.application.services.PosterrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.ParseException;
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
    public ResponseEntity<List<Post>> getAllPostsBetweenDates(@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate){
        try {
            return ResponseEntity.ok(posterrService.getAllPostsBetween(startDate, endDate));
        }
        catch (ParseException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonList(Post.builder().build())
                    );
        }
    }

    @GetMapping(value = "posts/{userId}")
    public ResponseEntity<List<Post>> getUserPosts(@PathParam(value = "userId") Integer userId){
        List<Post> posts = posterrService.getPostsByUserId(userId);
        if(!posts.isEmpty()){
            return ResponseEntity.ok(posts);
        }
        else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(Collections.singletonList(Post.builder().build())
                    );
        }
    }
}
