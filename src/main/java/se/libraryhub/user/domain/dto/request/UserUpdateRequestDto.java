package se.libraryhub.user.domain.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserUpdateRequestDto {

    private String username;

    private String profileImageUrl;

    private List<String> userLinks;

    @Builder
    public UserUpdateRequestDto(String username, String profileImageUrl, List<String> userLinks) {
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.userLinks = userLinks;
    }
}
