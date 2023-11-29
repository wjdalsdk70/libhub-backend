package se.libraryhub.user.domain.dto.response;

import lombok.*;
import se.libraryhub.user.domain.Role;
import se.libraryhub.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponseDto {

    private Long id;

    private String username;

    private String email;

    private String profileImageUrl;

    private List<String> userLinks;

    private Role role;

    private LocalDateTime createDate;

    private LocalDateTime modifiedDate;

    @Builder
    public UserResponseDto(Long id, String username, String email, String profileImageUrl,
                           Role role, LocalDateTime createDate, LocalDateTime modifiedDate,
                           List<String> userLinks) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.role = role;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.userLinks = userLinks;
    }

    public static UserResponseDto of(User user){
        return UserResponseDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .profileImageUrl(user.getProfileImageUrl())
                .id(user.getId())
                .role(user.getRole())
                .createDate(user.getCreateDate())
                .modifiedDate(user.getModifiedDate())
                .userLinks(user.getUserLinks())
                .build();
    }

}
