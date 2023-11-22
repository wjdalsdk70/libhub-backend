package se.libraryhub.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.libraryhub.library.domain.Library;
import se.libraryhub.project.domain.Project;

import java.util.List;
import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library, Long> {

    Optional<Library> findLibraryByLibraryId(Long libraryId);

    List<Library> findLibrariesByProject(Project project);
}
