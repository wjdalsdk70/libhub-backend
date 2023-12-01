package se.libraryhub.user.domain.dto.response;

import lombok.*;
import se.libraryhub.project.domain.dto.response.ProjectResponseDto;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLibraryResponseDto {

    private String libraryName;

    private int count;

    private List<ProjectResponseDto> usedProjects;

    @Builder
    public UserLibraryResponseDto(String libraryName, int count, List<ProjectResponseDto> usedProjects) {
        this.usedProjects = usedProjects;
        this.libraryName = libraryName;
        this.count = count;
    }
}
