package se.libraryhub.project.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import se.libraryhub.favorite.domain.dto.FavoriteResponseDto;
import se.libraryhub.library.domain.dto.response.LibraryContentResponseDto;
import se.libraryhub.project.domain.Project;
import se.libraryhub.user.domain.dto.response.UserResponseDto;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "프로젝트 상세 내용 조회")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectContentResponseDto {

    private Long projectId;

    private String projectname;

    private List<String> projectLinks;

    private String description;

    private Boolean isPublic;

    private List<String> projectHashtags;

    private List<LibraryContentResponseDto> projectLibraries;

    private UserResponseDto userResponseDto;

    private FavoriteResponseDto favoriteResponseDto;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @Builder
    public ProjectContentResponseDto(Long projectId, String projectname, List<String> projectLinks, 
                                     String description, Boolean isPublic, List<String> projectHashtags, 
                                     List<LibraryContentResponseDto> projectLibraries,
                                     UserResponseDto userResponseDto, FavoriteResponseDto favoriteResponseDto,
                                     LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.projectId = projectId;
        this.projectname = projectname;
        this.projectLinks = projectLinks;
        this.description = description;
        this.isPublic = isPublic;
        this.userResponseDto = userResponseDto;
        this.projectHashtags = projectHashtags;
        this.projectLibraries = projectLibraries;
        this.favoriteResponseDto = favoriteResponseDto;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static ProjectContentResponseDto of(Project project, List<String> hashtags,
                                               List<LibraryContentResponseDto> libraryContentResponseDtos,
                                               FavoriteResponseDto favoriteResponseDto){
        return ProjectContentResponseDto.builder()
                .projectId(project.getProjectId())
                .projectname(project.getProjectname())
                .projectLinks(project.getProjectLinks())
                .createdDate(project.getCreateDate())
                .modifiedDate(project.getModifiedDate())
                .projectHashtags(hashtags)
                .isPublic(project.getIsPublic())
                .description(project.getDescription())
                .userResponseDto(UserResponseDto.of(project.getUser()))
                .favoriteResponseDto(favoriteResponseDto)
                .projectLibraries(libraryContentResponseDtos)
                .build();
    }
}
