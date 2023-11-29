package se.libraryhub.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.libraryhub.library.domain.Library;
import se.libraryhub.project.domain.Project;

import java.util.List;
import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library, Long> {

    Optional<Library> findLibraryByLibraryId(Long libraryId);

    List<Library> findLibrariesByProject(Project project);

    @Query("SELECT DISTINCT p FROM Project p " +
            "LEFT JOIN FETCH Hashtag h ON p = h.project " +
            "LEFT JOIN FETCH Library l ON p = l.project " +
            "WHERE (h.content LIKE %:content% OR p.projectname LIKE %:content% " +
            "OR l.libraryname LIKE %:content%) "+
            "AND p.isPublic = true")
    List<Project> findProjectsByLibrarynameContent(@Param("content") String content);
}
