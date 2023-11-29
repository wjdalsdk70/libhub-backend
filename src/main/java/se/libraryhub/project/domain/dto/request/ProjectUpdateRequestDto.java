package se.libraryhub.project.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.libraryhub.project.domain.Project;
import se.libraryhub.user.domain.User;

import java.util.List;

@Schema(description = "프로젝트 업데이트하기 위한 요청")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectUpdateRequestDto {

    private Long projectId;

    private String projectname;

    private String description;

    private List<String> projectHashtags;

    private List<String> projectLinks;

    private Boolean isPublic;

    @Builder
    public ProjectUpdateRequestDto(Long projectId, String projectname, List<String> projectLinks, String description,
                                    Boolean isPublic, List<String> projectHashtags) {
        this.projectId = projectId;
        this.projectname = projectname;
        this.projectLinks = projectLinks;
        this.description = description;
        this.isPublic = isPublic;
        this.projectHashtags = projectHashtags;
    }
}
