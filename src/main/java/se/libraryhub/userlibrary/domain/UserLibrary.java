package se.libraryhub.userlibrary.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import se.libraryhub.global.util.BaseTimeEntity;
import se.libraryhub.library.domain.dto.request.LibraryContentRequestDto;
import se.libraryhub.project.domain.Project;
import se.libraryhub.user.domain.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLibrary extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userLibraryId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(nullable = false)
    private String libraryname;

    private String version;

    private String description;

    @Builder
    public UserLibrary(User user, String libraryname, String version, String description) {
        this.user = user;
        this.libraryname = libraryname;
        this.version = version;
        this.description = description;
    }

    public void updateUserLibrary(LibraryContentRequestDto libraryContentRequestDto){
        this.description = libraryContentRequestDto.getDescription();
        this.libraryname = libraryContentRequestDto.getLibraryname();
        this.version = libraryContentRequestDto.getVersion();
    }
}