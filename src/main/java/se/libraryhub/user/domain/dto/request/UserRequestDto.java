package se.libraryhub.user.domain.dto.request;

import lombok.*;
import se.libraryhub.user.domain.User;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRequestDto {

    private String username;

    private String email;

    private String profileImageUrl;

    private List<String> userLinks;

    @Builder
    public UserRequestDto(String username, String email, String profileImageUrl, List<String> userLinks) {
        this.username = username;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.userLinks = userLinks;
    }

    public User toEntity(UserRequestDto userRequestDto){
        return User.builder()
                .email(userRequestDto.getEmail())
                .profileImageUrl(userRequestDto.getProfileImageUrl())
                .username(userRequestDto.getUsername())
                .userLinks(userRequestDto.getUserLinks())
                .build();
    }

    public UserRequestDto toDto(User user){
        return UserRequestDto.builder()
                .email(user.getEmail())
                .profileImageUrl(user.getProfileImageUrl())
                .username(user.getUsername())
                .userLinks(user.getUserLinks())
                .build();
    }
}
