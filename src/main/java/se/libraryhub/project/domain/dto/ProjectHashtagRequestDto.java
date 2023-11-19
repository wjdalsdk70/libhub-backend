package se.libraryhub.project.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectHashtagRequestDto {

    private Long projectId;

    private String content;

    @Builder
    public ProjectHashtagRequestDto(Long projectId, String content) {
        this.projectId = projectId;
        this.content = content;
    }
}
