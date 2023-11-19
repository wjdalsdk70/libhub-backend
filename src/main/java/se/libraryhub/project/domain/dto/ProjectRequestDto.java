package se.libraryhub.project.domain.dto;

import lombok.*;
import se.libraryhub.project.domain.Project;
import se.libraryhub.user.domain.User;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectRequestDto {

    private String projectname;

    private String projectLink;

    private Boolean isPublic;

    @Builder
    public ProjectRequestDto(String projectname, String projectLink, Boolean isPublic) {
        this.projectname = projectname;
        this.projectLink = projectLink;
        this.isPublic = isPublic;
    }

    public Project toEntity(ProjectRequestDto projectRequestDto, User user){
        return Project.builder()
                .isPublic(projectRequestDto.getIsPublic())
                .projectLink(projectRequestDto.getProjectLink())
                .projectname(projectRequestDto.getProjectname())
                .user(user)
                .build();
    }
}
