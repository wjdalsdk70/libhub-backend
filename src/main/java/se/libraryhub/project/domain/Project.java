package se.libraryhub.project.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import se.libraryhub.global.util.BaseTimeEntity;
import se.libraryhub.user.domain.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(nullable = false)
    private String projectname;

    private String projectLink;

    private Boolean isPublic;

    @Builder
    public Project(User user, String projectname, String projectLink, Boolean isPublic) {
        this.user = user;
        this.projectname = projectname;
        this.projectLink = projectLink;
        this.isPublic = isPublic;
    }
}
