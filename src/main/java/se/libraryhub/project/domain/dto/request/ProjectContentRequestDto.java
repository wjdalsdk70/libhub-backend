package se.libraryhub.project.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.libraryhub.project.domain.Project;
import se.libraryhub.user.domain.User;

import java.util.List;

@Schema(description = "프로젝트 등록, 업데이트 하기 위한 요청")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectContentRequestDto {

    private String projectname;

    private String description;

    private List<String> projectHashtags;

    private List<String> projectLinks;

    private Boolean isPublic;


    public ProjectContentRequestDto(String projectname, List<String> projectLinks, String description,
                                    Boolean isPublic, List<String> projectHashtags) {
        this.projectname = projectname;
        this.projectLinks = projectLinks;
        this.description = description;
        this.isPublic = isPublic;
        this.projectHashtags = projectHashtags;
    }

    public static Project toEntity(ProjectContentRequestDto projectContentRequestDto, User user){
        return Project.builder()
                .isPublic(projectContentRequestDto.getIsPublic())
                .projectLinks(projectContentRequestDto.getProjectLinks())
                .projectname(projectContentRequestDto.getProjectname())
                .description(projectContentRequestDto.getDescription())
                .user(user)
                .build();
    }
}
