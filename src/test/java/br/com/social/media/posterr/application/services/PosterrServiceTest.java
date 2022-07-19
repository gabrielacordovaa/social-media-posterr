package br.com.social.media.posterr.application.services;

import br.com.social.media.posterr.adapters.datastore.repository.PostRepository;
import br.com.social.media.posterr.adapters.datastore.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @BeforeEach
    void initial(){
        posterrService = new PosterrService(postRepository, userRepository);
    }

    @Test
    void isUserAbleToPost_True_Test(){
        when(postRepository.getUserDailyPublication(any())).thenReturn(4);
        Assertions.assertTrue(posterrService.isUserAbleToPost("821921"));
        verify(postRepository, times(1)).getUserDailyPublication(any());
    }

    @Test
    void isUserAbleToPost_False_Test(){
        when(postRepository.getUserDailyPublication(any())).thenReturn(5);
        Assertions.assertFalse(posterrService.isUserAbleToPost("821921"));
        verify(postRepository, times(1)).getUserDailyPublication(any());
    }
}
