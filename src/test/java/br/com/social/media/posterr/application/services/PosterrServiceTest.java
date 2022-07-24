package br.com.social.media.posterr.application.services;

import br.com.social.media.posterr.adapters.datastore.repository.PostRepository;
import br.com.social.media.posterr.adapters.datastore.repository.UserRepository;
import br.com.social.media.posterr.adapters.mappers.FixListPostMapper;
import br.com.social.media.posterr.adapters.mappers.PostDTOMapper;
import br.com.social.media.posterr.application.dto.PostDTO;
import br.com.social.media.posterr.application.mapper.PostEntityMapper;
import br.com.social.media.posterr.application.mapper.UserResponseMapper;
import br.com.social.media.posterr.utils.GenerateBuilders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PosterrServiceTest {

    @InjectMocks
    private PosterrService posterrService;
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserResponseMapper userResponseMapper;
    @Mock
    private PostEntityMapper postEntityMapper;
    @Mock
    private PostDTOMapper postDTOMapper;
    @Mock
    private FixListPostMapper fixListPostMapper;

    @BeforeEach
    void initial(){
        posterrService = new PosterrService(
                postRepository,
                userRepository,
                userResponseMapper,
                postEntityMapper,
                postDTOMapper,
                fixListPostMapper
        );
    }

    @Test
    void isUserAbleToPost_True_Test(){
        when(postRepository.getUserDailyPublication(any())).thenReturn(4);
        Assertions.assertTrue(posterrService.isUserAbleToPost(1));
        verify(postRepository, times(1)).getUserDailyPublication(any());
    }

    @Test
    void isUserAbleToPost_False_Test(){
        when(postRepository.getUserDailyPublication(any())).thenReturn(5);
        Assertions.assertFalse(posterrService.isUserAbleToPost(1));
        verify(postRepository, times(1)).getUserDailyPublication(any());
    }

    @Test
    void postPersonalContentTest(){
        when(userRepository.findById(any())).thenReturn(Optional.of(GenerateBuilders.generateUser()));
        when(postEntityMapper.map(any(), any())).thenReturn(GenerateBuilders.generatePosts().get(0));
        when(postRepository.save(any())).thenReturn(GenerateBuilders.generatePosts().get(0));

        posterrService.postPersonalContent(GenerateBuilders.generatePostContentDTO());

        verify(userRepository, times(1)).findById(any());
        verify(postEntityMapper, times(1)).map(any(), any());
        verify(postRepository, times(1)).save(any());
    }

    //todo: ajustar esse teste aqui
   /* @Test
    void getAllPostsTest(){
        when(postRepository.findAll((Pageable) any()).toList()).thenReturn(GenerateBuilders.generatePosts());
        posterrService.fixPostList(GenerateBuilders.generatePosts());
        List<PostDTO> posts = posterrService.getAllPosts(5);

        verify(postRepository, times(1)).findAll((Pageable) any());
    }*/

    @Test
    void getAllPostsBetweenTest(){
        when(postRepository.getPostByDateRange(any(), any())).thenReturn(GenerateBuilders.generatePosts());

        Assertions.assertNotNull(posterrService.getAllPostsBetween("23/07/2022", "23/07/2022"));
        verify(postRepository, times(1)).getPostByDateRange(any(), any());
    }

    @Test
    void getPostsByUserId(){
        when(postRepository.getPostsByUserId(any())).thenReturn(GenerateBuilders.generatePosts());
        Assertions.assertNotNull(posterrService.getPostsByUserId(1));
        verify(postRepository, times(1)).getPostsByUserId(any());
    }

    @Test
    void getUserByIdTest(){
        when(userRepository.findById(any())).thenReturn(Optional.of(GenerateBuilders.generateUser()));
        when(userResponseMapper.fromEntity(any())).thenReturn(GenerateBuilders.generateUserResponse());

        Assertions.assertNotNull(posterrService.getUserById(1));

        verify(userRepository, times(1)).findById(any());
        verify(userResponseMapper, times(1)).fromEntity(any());
    }
}
