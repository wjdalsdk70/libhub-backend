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

    private String description;

    private String libraryname;

    private String version;

    private LocalDateTime createDate;

    private LocalDateTime modifiedDate;

    @Builder
    public LibraryResponseDto(Long libraryId, String libraryname,
                              String version,
                              LocalDateTime createDate, LocalDateTime modifiedDate,
                              String description) {
        this.libraryId = libraryId;
        this.libraryname = libraryname;
        this.version = version;
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
                .version(library.getVersion())
                .description(library.getDescription())
                .build();
    }
}
