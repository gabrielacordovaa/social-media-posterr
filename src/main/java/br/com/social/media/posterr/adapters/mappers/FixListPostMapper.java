package br.com.social.media.posterr.adapters.mappers;

import br.com.social.media.posterr.adapters.datastore.entity.Post;
import br.com.social.media.posterr.application.dto.PostDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class FixListPostMapper {

    private final PostDTOMapper postDTOMapper;

    public List<PostDTO> fixPostList(List<Post> posts){
        List<PostDTO> list = new ArrayList<>();
        for (Post p: posts
        ) {
            list.add(postDTOMapper.map(p));
        }
        return list;
    }
}
