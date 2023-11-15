package se.libraryhub.project.domain.dto;

import lombok.Builder;
import se.libraryhub.project.domain.Project;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.domain.dto.UserResponseDto;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ProjectResponseDto {

    private final Long projectId;

    private final UserResponseDto userResponseDto;

    private final String projectname;

    private final String projectLink;

    private final Boolean isPublic;

    @Builder
    public ProjectResponseDto(Long projectId, UserResponseDto userResponseDto, String projectname, String projectLink, Boolean isPublic) {
        this.projectId = projectId;
        this.userResponseDto = userResponseDto;
        this.projectname = projectname;
        this.projectLink = projectLink;
        this.isPublic = isPublic;
    }

    public static ProjectResponseDto of(Project project, User user){
        return ProjectResponseDto.builder()
                .projectId(project.getProjectId())
                .userResponseDto(UserResponseDto.of(user))
                .projectname(project.getProjectname())
                .projectLink(project.getProjectLink())
                .isPublic(project.getIsPublic())
                .build();
    }
}
