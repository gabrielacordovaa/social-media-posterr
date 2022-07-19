package br.com.social.media.posterr.adapters.datastore.repository;

import br.com.social.media.posterr.adapters.datastore.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, String> {

    //todo: check if query works later
    @Query(value = "SELECT count(1) FROM posts p WHERE p.user.id = :id AND postDate = CURRENT_DATE()", nativeQuery = true)
    public Integer getUserDailyPublication(@Param("id") String id);
}
