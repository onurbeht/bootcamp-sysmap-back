package br.com.sysmap.bootcamp.domain.repository;

import br.com.sysmap.bootcamp.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);

}
