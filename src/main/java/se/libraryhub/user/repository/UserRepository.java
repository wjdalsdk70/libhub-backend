package se.libraryhub.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.libraryhub.user.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(Long id);

    List<User> searchAllByUsername(String username);
}
