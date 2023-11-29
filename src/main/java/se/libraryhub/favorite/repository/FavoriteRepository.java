package se.libraryhub.favorite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.libraryhub.favorite.domain.Favorite;
import se.libraryhub.project.domain.Project;
import se.libraryhub.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findAllByProject(Project project);

    List<Favorite> findAllByUser(User user);

    Optional<Favorite> findByUserAndProject(User user, Project project);

    Boolean existsByUserAndProject(User user, Project project);
}
