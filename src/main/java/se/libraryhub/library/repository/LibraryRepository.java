package se.libraryhub.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.libraryhub.library.domain.Library;

import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library, Long> {

    Optional<Library> findLibraryByLibraryId(Long libraryId);

}
