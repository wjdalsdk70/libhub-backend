package se.libraryhub.project.domain.dto;

import lombok.Builder;
import lombok.Getter;
import se.libraryhub.project.domain.Project;
import se.libraryhub.user.domain.dto.UserRequestDto;

@Getter
public class ProjectRequestDto {

    private final String projectname;

    private final String projectLink;

    private final Boolean isPublic;

    @Builder
    public ProjectRequestDto(String projectname, String projectLink, Boolean isPublic) {
        this.projectname = projectname;
        this.projectLink = projectLink;
        this.isPublic = isPublic;
    }

    public Project toEntity(ProjectRequestDto projectRequestDto){
        return Project.builder()
                .isPublic(projectRequestDto.getIsPublic())
                .projectLink(projectRequestDto.getProjectLink())
                .projectname(projectRequestDto.getProjectname())
                .build();
    }
}
