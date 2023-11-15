package se.libraryhub.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.libraryhub.project.domain.Project;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findProjectByProjectId(Long id);
}
