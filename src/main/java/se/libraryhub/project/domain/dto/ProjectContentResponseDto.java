package se.libraryhub.project.domain.dto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import se.libraryhub.library.domain.Library;
import se.libraryhub.library.domain.dto.response.LibraryContentResponseDto;
import se.libraryhub.project.domain.Project;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "프로젝트 상세 내용 조회")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectContentResponseDto {

    private Long projectId;

    private String projectname;

    private String projectLink;

    private Boolean isPublic;

    private List<String> projectHashtags;

    private List<LibraryContentResponseDto> projectLibraries;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @Builder
    public ProjectContentResponseDto(Long projectId, String projectname, String projectLink,
                                     Boolean isPublic, List<String> projectHashtags, List<LibraryContentResponseDto> libraryContentResponseDtos,
                                     LocalDateTime createdDate , LocalDateTime modifiedDate) {
        this.projectId = projectId;
        this.projectname = projectname;
        this.projectLink = projectLink;
        this.projectHashtags = projectHashtags;
        this.projectLibraries = libraryContentResponseDtos;
        this.isPublic = isPublic;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static ProjectContentResponseDto of(Project project, List<String> hashtags, List<LibraryContentResponseDto> libraryContentResponseDtos){
        return ProjectContentResponseDto.builder()
                .projectId(project.getProjectId())
                .projectname(project.getProjectname())
                .projectLink(project.getProjectLink())
                .createdDate(project.getCreateDate())
                .modifiedDate(project.getModifiedDate())
                .projectHashtags(hashtags)
                .isPublic(project.getIsPublic())
                .libraryContentResponseDtos(libraryContentResponseDtos)
                .build();
    }
}
