package br.com.social.media.posterr.application.services;

import br.com.social.media.posterr.adapters.datastore.repository.PostRepository;
import br.com.social.media.posterr.adapters.datastore.repository.UserRepository;
import br.com.social.media.posterr.application.dto.PostContentDTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class PosterrService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public Boolean isUserAbleToPost(String id){
        return postRepository.getUserDailyPublication(id) < 5;
    }

    public void postPersonalContent(PostContentDTO postContentDTO){
        // todo: query user and map to save the post entity
    }
}
