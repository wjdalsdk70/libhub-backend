package se.libraryhub.library.domain.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.libraryhub.hashtag.domain.Hashtag;
import se.libraryhub.library.domain.Library;
import se.libraryhub.project.domain.dto.ProjectContentResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LibraryContentResponseDto {

    private Long libraryId;

    private String libraryname;

    private String version;

    private String usecase;

    private List<String> libraryHashtags;

    private LocalDateTime createDate;

    private LocalDateTime modifiedDate;

    @Builder
    public LibraryContentResponseDto(Long libraryId, String libraryname, String version, String usecase,
                                     List<String> libraryHashtags, LocalDateTime createDate, LocalDateTime modifiedDate) {
        this.libraryId = libraryId;
        this.libraryname = libraryname;
        this.version = version;
        this.usecase = usecase;
        this.libraryHashtags = libraryHashtags;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
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
                .build();
    }
}
