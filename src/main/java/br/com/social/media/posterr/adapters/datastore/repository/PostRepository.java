package br.com.social.media.posterr.adapters.datastore.repository;

import br.com.social.media.posterr.adapters.datastore.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {

    //todo: check
    @Query(value = "SELECT count(1) FROM posts p WHERE p.user.id = :id AND postDate = CURRENT_DATE()", nativeQuery = true)
    Integer getUserDailyPublication(@Param("id") String id);

    //todo: check
    @Query(value = "SELECT * FROM posts p WHERE p.postDate BETWEEN (CASE WHEN :begin IS NULL THEN p.postDate ELSE :begin END) AND " +
            "(CASE WHEN :end IS NULL THEN p.postDate ELSE :end END)", nativeQuery = true)
    List<Post> getPostByDateRange(@Param("begin") Date begin, @Param("end") Date end);

    @Query(value = "SELECT * FROM posts p WHERE p.user.id = :userId", nativeQuery = true)
    List<Post> getPostsByUserId(@Param("userId") String id);
}
