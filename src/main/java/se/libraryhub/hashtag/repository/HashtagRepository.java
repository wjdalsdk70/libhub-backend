package se.libraryhub.hashtag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import se.libraryhub.hashtag.domain.Hashtag;
import se.libraryhub.library.domain.Library;
import se.libraryhub.project.domain.Project;

import java.util.List;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    List<Hashtag> findAllByProject(Project project);

    List<Hashtag> findAllByLibrary(Library library);

    void deleteAllByProject(Project project);

    void deleteAllByLibrary(Library library);

    List<Hashtag> searchAllByProjectNotNullAndContent(String content);

    List<Hashtag> searchAllByLibraryNotNullAndContent(String content);
}
