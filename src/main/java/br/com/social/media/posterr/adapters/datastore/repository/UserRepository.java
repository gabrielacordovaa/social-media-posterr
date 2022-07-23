package br.com.social.media.posterr.adapters.datastore.repository;

import br.com.social.media.posterr.adapters.datastore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {

}
