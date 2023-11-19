package se.libraryhub.project.domain.dto;

import lombok.*;
import se.libraryhub.hashtag.domain.Hashtag;
import se.libraryhub.project.domain.Project;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.domain.dto.UserResponseDto;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectContentResponseDto {

    private Long projectId;

    private String projectname;

    private String projectLink;

    private Boolean isPublic;

    private List<String> projectHashtags;

    @Builder
    public ProjectContentResponseDto(Long projectId, String projectname, String projectLink, Boolean isPublic, List<String> projectHashtags) {
        this.projectId = projectId;
        this.projectname = projectname;
        this.projectLink = projectLink;
        this.projectHashtags = projectHashtags;
        this.isPublic = isPublic;
    }

    public static ProjectContentResponseDto of(Project project, List<String> hashtags){
        return ProjectContentResponseDto.builder()
                .projectId(project.getProjectId())
                .projectname(project.getProjectname())
                .projectLink(project.getProjectLink())
                .projectHashtags(hashtags)
                .isPublic(project.getIsPublic())
                .build();
    }
}
