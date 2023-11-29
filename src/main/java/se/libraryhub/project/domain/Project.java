package se.libraryhub.project.domain;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import se.libraryhub.global.util.BaseTimeEntity;
import se.libraryhub.project.domain.dto.request.ProjectContentRequestDto;
import se.libraryhub.user.domain.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
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

    private String description;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> projectLinks;

    private Boolean isPublic;

    @Builder
    public Project(User user, String projectname, String description, List<String> projectLinks, Boolean isPublic) {
        this.user = user;
        this.projectname = projectname;
        this.description = description;
        this.projectLinks = projectLinks;
        this.isPublic = isPublic;
    }

    public static void updateProject(Project project, ProjectContentRequestDto projectContentRequestDto){
        project.setProjectLinks(projectContentRequestDto.getProjectLinks());
        project.setDescription(projectContentRequestDto.getDescription());
        project.setIsPublic(projectContentRequestDto.getIsPublic());
        project.setProjectname(projectContentRequestDto.getProjectname());
    }
}
