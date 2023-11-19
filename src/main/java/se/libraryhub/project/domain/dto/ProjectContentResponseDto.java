package se.libraryhub.project.domain.dto;

import lombok.*;
import se.libraryhub.project.domain.Project;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectContentResponseDto {

    private Long projectId;

    private String projectname;

    private String projectLink;

    private Boolean isPublic;

    private List<String> projectHashtags;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @Builder
    public ProjectContentResponseDto(Long projectId, String projectname, String projectLink,
                                     Boolean isPublic, List<String> projectHashtags
                                     ,LocalDateTime createdDate , LocalDateTime modifiedDate) {
        this.projectId = projectId;
        this.projectname = projectname;
        this.projectLink = projectLink;
        this.projectHashtags = projectHashtags;
        this.isPublic = isPublic;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static ProjectContentResponseDto of(Project project, List<String> hashtags){
        return ProjectContentResponseDto.builder()
                .projectId(project.getProjectId())
                .projectname(project.getProjectname())
                .projectLink(project.getProjectLink())
                .createdDate(project.getCreateDate())
                .modifiedDate(project.getModifiedDate())
                .projectHashtags(hashtags)
                .isPublic(project.getIsPublic())
                .build();
    }
}
