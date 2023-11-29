package se.libraryhub.librarycount.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.libraryhub.librarycount.domain.LibraryCount;

import java.util.List;
import java.util.Optional;

public interface LibraryCountRepository extends JpaRepository<LibraryCount, Long> {

    Optional<LibraryCount> findByLibraryname(String libraryName);

    List<LibraryCount> findTop10ByOrderByCountDesc();

    List<LibraryCount> findAllByOrderByCountDesc();
}
