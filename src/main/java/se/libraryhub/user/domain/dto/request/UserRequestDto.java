package se.libraryhub.user.domain.dto.request;

import lombok.*;
import se.libraryhub.user.domain.User;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRequestDto {

    private String username;

    private String email;

    private String profileImageUrl;

    @Builder
    public UserRequestDto(String username, String email, String profileImageUrl) {
        this.username = username;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
    }

    public User toEntity(UserRequestDto userRequestDto){
        return User.builder()
                .email(userRequestDto.getEmail())
                .profileImageUrl(userRequestDto.getProfileImageUrl())
                .username(userRequestDto.getUsername())
                .build();
    }

    public UserRequestDto toDto(User user){
        return UserRequestDto.builder()
                .email(user.getEmail())
                .profileImageUrl(user.getProfileImageUrl())
                .username(user.getUsername())
                .build();
    }
}
