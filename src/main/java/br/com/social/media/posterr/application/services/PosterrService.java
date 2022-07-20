package br.com.social.media.posterr.application.services;

import br.com.social.media.posterr.adapters.controller.response.UserResponse;
import br.com.social.media.posterr.adapters.datastore.entity.Post;
import br.com.social.media.posterr.adapters.datastore.entity.User;
import br.com.social.media.posterr.adapters.datastore.repository.PostRepository;
import br.com.social.media.posterr.adapters.datastore.repository.UserRepository;
import br.com.social.media.posterr.application.dto.PostContentDTO;
import br.com.social.media.posterr.application.mapper.PostEntityMapper;
import br.com.social.media.posterr.application.mapper.UserResponseMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@AllArgsConstructor
public class PosterrService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private UserResponseMapper userResponseMapper;
    private PostEntityMapper postEntityMapper;

    public Boolean isUserAbleToPost(String id){
        return postRepository.getUserDailyPublication(id) < 5;
    }

    public void postPersonalContent(PostContentDTO postContentDTO) throws Exception {
        Optional<User> user = userRepository.findById(postContentDTO.getId());
        if(user.isPresent()){
            postRepository.save(postEntityMapper.map(
                                    postContentDTO,
                                    user.get())
            );
        }
        else {
            throw new Exception("There is no user with this id");
        }

    }

    public List<Post> getAllPosts(Integer size){
        Pageable pagination = PageRequest.of(0, size, Sort.by("postDate").descending());
        return postRepository.findAll(pagination).toList();
    }

    public List<Post> getAllPostsBetween(String startDate, String endDate) throws ParseException {
        Date start = startDate.isEmpty() ? null : new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
        Date end = endDate.isEmpty() ? null : new SimpleDateFormat("dd/MM/yyyy").parse(endDate);

        return postRepository.getPostByDateRange(start, end);
    }

    public List<Post> getPostsByUserId(String userId){
        return postRepository.getPostsByUserId(userId);
    }

    public UserResponse getUserById(String userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return userResponseMapper.fromEntity(user.get());
        }
        return null;
    }
}
