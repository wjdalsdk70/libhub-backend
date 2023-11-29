package se.libraryhub.library.domain.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.libraryhub.library.domain.Library;
import se.libraryhub.project.domain.Project;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LibraryContentRequestDto {

    private String libraryname;

    private String description;

    private String version;

    private List<String> libraryHashtags;

    @Builder
    public LibraryContentRequestDto(String libraryname, String version,
                                    String description, List<String> libraryHashtags) {
        this.libraryname = libraryname;
        this.description = description;
        this.version = version;
        this.libraryHashtags = libraryHashtags;
    }

    public static Library toEntity(LibraryContentRequestDto libraryContentRequestDto, Project project){
        return Library.builder()
                .libraryname(libraryContentRequestDto.getLibraryname())
                .version(libraryContentRequestDto.getVersion())
                .description(libraryContentRequestDto.getDescription())
                .project(project)
                .build();
    }
}
