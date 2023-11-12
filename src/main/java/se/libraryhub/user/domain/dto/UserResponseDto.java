package se.libraryhub.user.domain.dto;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import se.libraryhub.user.domain.Role;
import se.libraryhub.user.domain.User;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Date;

@Getter
public class UserResponseDto {

    private final Long id;

    private final String username;

    private final String email;

    private final String profileImageUrl;

    private final Role role;

    @Builder
    public UserResponseDto(Long id, String username, String email, String profileImageUrl, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.role = role;
    }

    public static UserResponseDto ofUserEntity(User user){
        return UserResponseDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .profileImageUrl(user.getProfileImageUrl())
                .id(user.getId())
                .role(user.getRole())
                .build();
    }

}
