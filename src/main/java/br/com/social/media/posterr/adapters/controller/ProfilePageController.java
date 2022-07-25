package br.com.social.media.posterr.adapters.controller;

import br.com.social.media.posterr.adapters.controller.response.UserResponse;
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
@RequestMapping("/profile-page/v1")
@RequiredArgsConstructor
public class ProfilePageController {

    private static final Integer size = 5;
    private final PosterrService posterrService;

    @GetMapping(value = "/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        log.info("Profile-Page request posts with pagination: {}", size);
        List<PostDTO> posts = posterrService.getAllPosts(size);
        if (!posts.isEmpty()) {
            log.info("Request was made. List is not empty. Size: {}", posts.size());
            return ResponseEntity.ok(posts);
        } else {
            log.info("Request was made. List is empty.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(Collections.singletonList(PostDTO.builder().build())
                    );
        }
    }

    @GetMapping(value = "/info/{userId}")
    public ResponseEntity<UserResponse> getUserInfo(@PathVariable("userId") Integer id) {
        log.info("Profile-Page request user profile information.");
        UserResponse userResponse = posterrService.getUserById(id);
        if (userResponse != null) {
            log.info("Request was made. User info is not empty.");
            return ResponseEntity.ok(userResponse);
        } else {
            log.info("Request was made. User info is empty.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(UserResponse.builder().build()
                    );
        }
    }
}
