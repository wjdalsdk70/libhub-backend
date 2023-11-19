package se.libraryhub.hashtag.domain;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;
import se.libraryhub.library.domain.Library;
import se.libraryhub.project.domain.Project;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hashtag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "library_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Library library;

    private String content;

    @Builder
    public Hashtag(Project project, Library library, String content) {
        this.project = project;
        this.library = library;
        this.content = content;
    }

    public static Hashtag projectHashtag(Project project, String content){
        return Hashtag.builder()
                .project(project)
                .content(content)
                .library(null)
                .build();
    }

    public static Hashtag libraryHashtag(Library library, String content){
        return Hashtag.builder()
                .project(null)
                .content(content)
                .library(library)
                .build();
    }
}
