package se.libraryhub.hashtag;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import se.libraryhub.library.domain.Library;
import se.libraryhub.project.domain.Project;

import javax.persistence.*;

@Entity
@Getter
public class Hashtag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

    private String content;
}
