package se.libraryhub.library.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import se.libraryhub.global.util.BaseTimeEntity;
import se.libraryhub.library.domain.dto.request.LibraryContentRequestDto;
import se.libraryhub.project.domain.Project;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Library extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long libraryId;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;

    @Column(nullable = false)
    private String libraryname;

    private String version;

    private String description;

    @Builder
    public Library(Project project, String libraryname, String version, String description) {
        this.project = project;
        this.libraryname = libraryname;
        this.version = version;
        this.description = description;
    }

    public void updateLibrary(LibraryContentRequestDto libraryContentRequestDto){
        this.description = libraryContentRequestDto.getDescription();
        this.libraryname = libraryContentRequestDto.getLibraryname();
        this.version = libraryContentRequestDto.getVersion();
    }
}