package br.com.social.media.posterr.adapters.datastore.entity;

import br.com.social.media.posterr.application.enums.PostType;
import lombok.*;

import javax.naming.spi.NamingManager;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "posts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @Column(name = "POST_ID")
    private String postId;

    @Column(name = "POST_DATE")
    private LocalDateTime postDate;

    @Column(name = "POST_CONTENT", length = 777)
    private String postContent;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private PostType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
}
