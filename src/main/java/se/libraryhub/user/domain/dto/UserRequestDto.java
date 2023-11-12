package se.libraryhub.user.domain.dto;

import lombok.Builder;
import lombok.Getter;
import se.libraryhub.user.domain.Role;
import se.libraryhub.user.domain.User;

@Getter
public class UserRequestDto {

    private final String username;

    private final String email;

    private final String profileImageUrl;

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
