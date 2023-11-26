package se.libraryhub.library.domain.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.libraryhub.library.domain.Library;
import se.libraryhub.project.domain.dto.response.ProjectContentResponseDto;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LibraryResponseDto {

    private Long libraryId;

    private ProjectContentResponseDto projectContentResponseDto;

    private String description;

    private String libraryname;

    private String version;

    private String usecase;

    private LocalDateTime createDate;

    private LocalDateTime modifiedDate;

    @Builder
    public LibraryResponseDto(Long libraryId, ProjectContentResponseDto projectContentResponseDto, String libraryname,
                              String version, String usecase,
                              LocalDateTime createDate, LocalDateTime modifiedDate,
                              String description) {
        this.libraryId = libraryId;
        this.projectContentResponseDto = projectContentResponseDto;
        this.libraryname = libraryname;
        this.version = version;
        this.usecase = usecase;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.description = description;
    }

    public static LibraryResponseDto of(Library library){
        return LibraryResponseDto.builder()
                .createDate(library.getCreateDate())
                .libraryId(library.getLibraryId())
                .libraryname(library.getLibraryname())
                .modifiedDate(library.getModifiedDate())
                .usecase(library.getUsecase())
                .version(library.getVersion())
                .description(library.getDescription())
                .build();
    }
}
