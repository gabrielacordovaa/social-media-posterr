package br.com.social.media.posterr.adapters.datastore.repository;

import br.com.social.media.posterr.adapters.datastore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
