package se.libraryhub.user.domain.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLibraryResponseDto {

    private String libraryName;

    private int count;

    @Builder
    public UserLibraryResponseDto(String libraryName, int count) {
        this.libraryName = libraryName;
        this.count = count;
    }
}
