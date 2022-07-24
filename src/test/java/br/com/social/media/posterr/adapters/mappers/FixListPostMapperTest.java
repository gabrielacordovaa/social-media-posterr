package br.com.social.media.posterr.adapters.mappers;

import br.com.social.media.posterr.adapters.datastore.entity.Post;
import br.com.social.media.posterr.application.dto.PostDTO;
import br.com.social.media.posterr.utils.GenerateBuilders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FixListPostMapperTest {

    @InjectMocks
    private FixListPostMapper fixListPostMapper;
    @Mock
    private PostDTOMapper postDTOMapper;

    List<Post> postList = new ArrayList<>();

    @BeforeEach
    void initial(){
        fixListPostMapper = new FixListPostMapper(postDTOMapper);
        postList.addAll(GenerateBuilders.generatePosts());
    }

    @Test
    void fixPostListTest(){
        when(postDTOMapper.map(any())).thenReturn(GenerateBuilders.generatePostDTO());
        Assertions.assertNotNull(fixListPostMapper.fixPostList(postList));
        verify(postDTOMapper, times(1)).map(any());
    }

    @Test
    void fixPostListEmptyTest(){
        Assertions.assertEquals(Collections.emptyList(), fixListPostMapper.fixPostList(new ArrayList<>()));
    }
}
