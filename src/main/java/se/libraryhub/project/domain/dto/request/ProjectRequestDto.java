package se.libraryhub.project.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import se.libraryhub.project.domain.Project;
import se.libraryhub.user.domain.User;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "프로젝트 상세 내용 조회하기 위한 Request")
public class ProjectRequestDto {

    private String projectname;

    private List<String> projectLinks;

    private Boolean isPublic;

    @Builder
    public ProjectRequestDto(String projectname, List<String> projectLinks, Boolean isPublic) {
        this.projectname = projectname;
        this.projectLinks = projectLinks;
        this.isPublic = isPublic;
    }

    public Project toEntity(ProjectRequestDto projectRequestDto, User user){
        return Project.builder()
                .isPublic(projectRequestDto.getIsPublic())
                .projectLinks(projectRequestDto.getProjectLinks())
                .projectname(projectRequestDto.getProjectname())
                .user(user)
                .build();
    }
}
