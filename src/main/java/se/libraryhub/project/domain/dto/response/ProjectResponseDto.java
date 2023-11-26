package se.libraryhub.project.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.libraryhub.project.domain.Project;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.domain.dto.UserResponseDto;

import java.util.List;

@Schema(description = "프로젝트 미리보기")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectResponseDto {

    private Long projectId;

    private UserResponseDto userResponseDto;

    private String projectname;

    private String description;

    private List<String> projectHashtags;

    private Boolean isPublic;

    @Builder
    public ProjectResponseDto(Long projectId, UserResponseDto userResponseDto, String projectname,
                              List<String> projectHashtags, Boolean isPublic, String description) {
        this.projectId = projectId;
        this.userResponseDto = userResponseDto;
        this.description = description;
        this.projectname = projectname;
        this.projectHashtags = projectHashtags;
        this.isPublic = isPublic;
    }

    public static ProjectResponseDto of(Project project, List<String> projectHashtags, User user){
        return ProjectResponseDto.builder()
                .projectId(project.getProjectId())
                .userResponseDto(UserResponseDto.of(user))
                .description(project.getDescription())
                .projectname(project.getProjectname())
                .projectHashtags(projectHashtags)
                .isPublic(project.getIsPublic())
                .build();
    }
}
