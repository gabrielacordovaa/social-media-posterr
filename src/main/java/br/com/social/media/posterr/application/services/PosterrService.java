package br.com.social.media.posterr.application.services;

import br.com.social.media.posterr.adapters.controller.request.PostInteractiveRequest;
import br.com.social.media.posterr.adapters.controller.response.UserResponse;
import br.com.social.media.posterr.adapters.datastore.entity.Post;
import br.com.social.media.posterr.adapters.datastore.entity.User;
import br.com.social.media.posterr.adapters.datastore.repository.PostRepository;
import br.com.social.media.posterr.adapters.datastore.repository.UserRepository;
import br.com.social.media.posterr.adapters.mappers.FixListPostMapper;
import br.com.social.media.posterr.adapters.mappers.PostDTOMapper;
import br.com.social.media.posterr.application.dto.PostContentDTO;
import br.com.social.media.posterr.application.dto.PostDTO;
import br.com.social.media.posterr.application.enums.PostType;
import br.com.social.media.posterr.application.mapper.PostEntityMapper;
import br.com.social.media.posterr.application.mapper.UserResponseMapper;
import br.com.social.media.posterr.utils.FieldsFixer;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Log4j2
@AllArgsConstructor
@Service
public class PosterrService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;
    private final PostEntityMapper postEntityMapper;
    private final PostDTOMapper postDTOMapper;
    private final FixListPostMapper fixListPostMapper;
    public Boolean isUserAbleToPost(Integer id){
        return postRepository.getUserDailyPublication(id) < 5;
    }

    public void postPersonalContent(PostContentDTO postContentDTO){
        Optional<User> user = userRepository.findById(postContentDTO.getUserId());
        postRepository.save(postEntityMapper.map(
                                postContentDTO,
                                user.get())
        );
    }

    public List<PostDTO> getAllPosts(Integer size){
        Pageable pagination = PageRequest.of(0, size, Sort.by("postDate").descending());
        return fixListPostMapper.fixPostList(postRepository.findAll(pagination).toList());
    }


    public List<PostDTO> getAllPostsBetween(String startDate, String endDate){
        return fixListPostMapper.fixPostList(postRepository.getPostByDateRange(
                FieldsFixer.fixDate(startDate),
                FieldsFixer.fixDate(endDate))
        );
    }
    public List<PostDTO> getAllPostsByUserIdPaginated(Integer userId, Integer size){
        Pageable pagination = PageRequest.of(0, size, Sort.by("POST_DATE").descending());

        return fixListPostMapper.fixPostList(postRepository.getPostsByUserIdPaginated(userId, pagination).toList());
    }
    public List<PostDTO> getPostsByUserId(Integer userId){

        return fixListPostMapper.fixPostList(postRepository.getPostsByUserId(userId));
    }

    public UserResponse getUserById(Integer userId){
        Optional<User> user = userRepository.findById(userId);
        return user.map(userResponseMapper::fromEntity).orElse(null);
    }

    public PostDTO postInteraction(PostInteractiveRequest postInteractive){

        Optional<Post> post = postRepository.findById(postInteractive.getPostId());
        Optional<User> user = userRepository.findById(postInteractive.getUserId());

        if(post.isPresent() && user.isPresent()){
            if(FieldsFixer.isTheActionValid(postInteractive.getInteraction(), post.get().getType().name())){
                return postDTOMapper.map(postRepository.save(postEntityMapper.map(PostContentDTO.builder()
                                                                .type(PostType.getPostType(postInteractive.getInteraction()))
                                                                .content(post.get().getPostContent())
                                                                .build(), user.get())));
            }
        }
         return null;
    }
}
