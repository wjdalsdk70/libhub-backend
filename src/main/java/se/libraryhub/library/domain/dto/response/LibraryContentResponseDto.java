package se.libraryhub.library.domain.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.libraryhub.library.domain.Library;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LibraryContentResponseDto {

    private Long libraryId;

    private String libraryname;

    private String version;

    private String usecase;

    private String description;

    private List<String> libraryHashtags;

    private LocalDateTime createDate;

    private LocalDateTime modifiedDate;

    @Builder
    public LibraryContentResponseDto(Long libraryId, String libraryname, String version, String usecase,
                                     List<String> libraryHashtags, LocalDateTime createDate, LocalDateTime modifiedDate,
                                     String description) {
        this.libraryId = libraryId;
        this.libraryname = libraryname;
        this.version = version;
        this.usecase = usecase;
        this.libraryHashtags = libraryHashtags;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.description = description;
    }

    public static LibraryContentResponseDto of(Library library, List<String> hashtags){
        return LibraryContentResponseDto.builder()
                .createDate(library.getCreateDate())
                .libraryId(library.getLibraryId())
                .libraryHashtags(hashtags)
                .libraryname(library.getLibraryname())
                .modifiedDate(library.getModifiedDate())
                .usecase(library.getUsecase())
                .version(library.getVersion())
                .description(library.getDescription())
                .build();
    }
}
