package br.com.social.media.posterr.adapters.datastore.repository;

import br.com.social.media.posterr.adapters.datastore.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "SELECT count(1) FROM posts p WHERE p.USER_ID = :id AND DATE(p.POST_DATE) = CURRENT_DATE()", nativeQuery = true)
    Integer getUserDailyPublication(@Param("id") Integer id);

    @Query(value = "SELECT * FROM posts p WHERE p.POST_DATE BETWEEN (CASE WHEN :begin IS NULL THEN p.POST_DATE ELSE :begin END) AND " +
            "(CASE WHEN :end IS NULL THEN p.POST_DATE ELSE :end END)", nativeQuery = true)
    List<Post> getPostByDateRange(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);

    @Query(value = "SELECT * FROM posts p WHERE p.USER_ID = :userId", nativeQuery = true)
    List<Post> getPostsByUserId(@Param("userId") Integer id);

    @Query(value = "SELECT * FROM posts p WHERE p.USER_ID = :userId", nativeQuery = true)
    Page<Post> getPostsByUserIdPaginated(@Param("userId") Integer id, Pageable pagination);
}
