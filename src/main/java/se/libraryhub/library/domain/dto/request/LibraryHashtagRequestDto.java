package se.libraryhub.library.domain.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LibraryHashtagRequestDto {

    private Long libraryId;

    private String content;

    @Builder
    public LibraryHashtagRequestDto(Long libraryId, String content) {
        this.libraryId = libraryId;
        this.content = content;
    }
}
