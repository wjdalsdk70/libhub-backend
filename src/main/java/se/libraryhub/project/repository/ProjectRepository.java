package se.libraryhub.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.libraryhub.project.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
