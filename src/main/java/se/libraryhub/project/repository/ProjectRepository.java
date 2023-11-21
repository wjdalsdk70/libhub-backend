package se.libraryhub.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import se.libraryhub.project.domain.Project;
import se.libraryhub.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findProjectByProjectId(Long id);

    List<Project> findAllByUser(User user);

    Page<Project> findAll(Pageable pageable);

    Page<Project> findByUser(User user, Pageable pageable);
}
