package br.com.social.media.posterr.adapters.controller;

import br.com.social.media.posterr.adapters.controller.request.PostContentRequest;
import br.com.social.media.posterr.adapters.controller.response.StandardResponse;
import br.com.social.media.posterr.adapters.controller.response.UserResponse;
import br.com.social.media.posterr.adapters.datastore.entity.Post;
import br.com.social.media.posterr.adapters.datastore.entity.User;
import br.com.social.media.posterr.application.enums.Status;
import br.com.social.media.posterr.application.services.PosterrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collections;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/profile-page/v1")
@RequiredArgsConstructor
public class ProfilePageController {

    private static final Integer size = 5;
    private final PosterrService posterrService;
    @GetMapping(value = "/posts")
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> posts = posterrService.getAllPosts(5);
        if(!posts.isEmpty()){
            return ResponseEntity.ok(posts);
        }
        else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(Collections.singletonList(Post.builder().build())
                    );
        }
    }

    @GetMapping(value = "/info/{userId}")
    public ResponseEntity<UserResponse> getUserInfo(@PathParam(value = "userId") Integer id){
        try {
            return ResponseEntity.ok(posterrService.getUserById(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(UserResponse.builder().build()
                    );
        }
    }
}
