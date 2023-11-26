package se.libraryhub.project.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.libraryhub.favorite.domain.dto.FavoriteResponseDto;
import se.libraryhub.project.domain.Project;
import se.libraryhub.project.repository.ProjectRepository;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.domain.dto.response.UserResponseDto;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Schema(description = "프로젝트 미리보기")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectResponseDto{

    private Long projectId;

    private UserResponseDto userResponseDto;

    private String projectname;

    private String description;

    private List<String> projectHashtags;

    private Boolean isPublic;

    private FavoriteResponseDto favoriteResponseDto;

    private LocalDateTime createdDate;

    @Builder
    public ProjectResponseDto(Long projectId, UserResponseDto userResponseDto, String projectname,
                              List<String> projectHashtags, Boolean isPublic, String description,
                              FavoriteResponseDto favoriteResponseDto, LocalDateTime createdDate) {
        this.projectId = projectId;
        this.userResponseDto = userResponseDto;
        this.description = description;
        this.projectname = projectname;
        this.projectHashtags = projectHashtags;
        this.isPublic = isPublic;
        this.createdDate = createdDate;
        this.favoriteResponseDto = favoriteResponseDto;
    }

    public static ProjectResponseDto of(Project project, List<String> projectHashtags, User user,
                                        FavoriteResponseDto favoriteResponseDto){
        return ProjectResponseDto.builder()
                .projectId(project.getProjectId())
                .userResponseDto(UserResponseDto.of(user))
                .description(project.getDescription())
                .projectname(project.getProjectname())
                .projectHashtags(projectHashtags)
                .favoriteResponseDto(favoriteResponseDto)
                .createdDate(project.getCreateDate())
                .isPublic(project.getIsPublic())
                .build();
    }


    public static void sortByFavorite(List<ProjectResponseDto> projectResponseDtos) {
        Collections.sort(projectResponseDtos,
                Comparator.comparingInt((ProjectResponseDto projectResponseDto) ->
                        projectResponseDto.getFavoriteResponseDto().getFavoriteCount()).reversed());
    }

    public static void sortByCreateDate(List<ProjectResponseDto> projectResponseDtos) {
        Collections.sort(projectResponseDtos,
                Comparator.comparing(ProjectResponseDto::getCreatedDate).reversed());
    }
}
