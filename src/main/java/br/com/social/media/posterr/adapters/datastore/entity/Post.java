package br.com.social.media.posterr.adapters.datastore.entity;

import br.com.social.media.posterr.application.enums.PostType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Builder
public class Post {

    @Id
    private String postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user.name")
    private User user;

    private Date postDate;

    @Column(length = 777)
    private String postContent;

    private PostType type;
}
