package se.libraryhub.project.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Schema(description = "프로젝트 미리보기 목록")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectResult {

    private List<ProjectResponseDto> projectResult;

    private int totalPage;

    @Builder
    public ProjectResult(List<ProjectResponseDto> projectResult, int totalPage) {
        this.projectResult = projectResult;
        this.totalPage = totalPage;
    }
}
