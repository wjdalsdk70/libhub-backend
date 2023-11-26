package se.libraryhub.library.domain.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.libraryhub.library.domain.Library;
import se.libraryhub.project.domain.Project;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LibraryRequestDto {

    private Long projectId;

    private String libraryname;

    private String description;

    private String version;

    private String usecase;

    @Builder
    public LibraryRequestDto(Long projectId, String libraryname, String version, String usecase,
                             String description) {
        this.projectId = projectId;
        this.libraryname = libraryname;
        this.description = description;
        this.version = version;
        this.usecase = usecase;
    }

    public static Library toEntity(LibraryRequestDto libraryRequestDto, Project project){
        return Library.builder()
                .libraryname(libraryRequestDto.getLibraryname())
                .usecase(libraryRequestDto.getUsecase())
                .version(libraryRequestDto.getVersion())
                .description(libraryRequestDto.getDescription())
                .project(project)
                .build();
    }
}
