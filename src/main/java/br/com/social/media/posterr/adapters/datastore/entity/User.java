package br.com.social.media.posterr.adapters.datastore.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME",length = 14)
    private String name;

    @Column(name = "COUNTER_POSTS")
    private Integer counterPosts;

    @Column(name = "DATE_JOINED")
    private LocalDateTime dateJoined;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;
}
