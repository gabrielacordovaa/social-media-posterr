package br.com.social.media.posterr.adapters.datastore.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Data
@Builder
public class User {

    @Id
    private String id;

    @Column(length = 14) // only accepts alphanumeric
    private String name;

    private Integer counterPosts;
    private Date dateJoined;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Post> posts;
}
