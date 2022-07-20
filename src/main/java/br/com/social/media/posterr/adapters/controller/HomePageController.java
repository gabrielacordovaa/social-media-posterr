package br.com.social.media.posterr.adapters.controller;

import br.com.social.media.posterr.adapters.controller.request.PostContentRequest;
import br.com.social.media.posterr.adapters.controller.response.StandardResponse;
import br.com.social.media.posterr.adapters.datastore.entity.Post;
import br.com.social.media.posterr.application.enums.Status;
import br.com.social.media.posterr.application.services.PosterrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.InvalidObjectException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/home-page/v1")
@RequiredArgsConstructor
public class HomePageController {

    private static final Integer size = 10;
    private PosterrService posterrService;
    @GetMapping(value = "/posts")
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> posts = posterrService.getAllPosts(size);
        if(!posts.isEmpty()){
            return ResponseEntity.ok(posts);
        }
        else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                                 .body(Collections.singletonList(Post.builder().build())
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
    public ResponseEntity<List<Post>> getUserPosts(@PathParam(value = "userId") String userId){
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
