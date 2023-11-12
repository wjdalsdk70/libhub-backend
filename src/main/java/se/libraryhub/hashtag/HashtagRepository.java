package se.libraryhub.hashtag;

import org.springframework.data.jpa.repository.JpaRepository;
import se.libraryhub.library.domain.Library;
import se.libraryhub.project.domain.Project;

import java.util.List;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    List<Hashtag> findAllByProject(Project project);

    List<Hashtag> findAllByLibrary(Library library);
}
