package se.libraryhub.user.domain.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserContentResponseDto {

    private UserResponseDto userResponseDto;

    private boolean isFollowed;

    @Builder
    public UserContentResponseDto(UserResponseDto userResponseDto, boolean isFollowed) {
        this.userResponseDto = userResponseDto;
        this.isFollowed = isFollowed;
    }

    public static UserContentResponseDto of(UserResponseDto userResponseDto, boolean isFollowed){
        return UserContentResponseDto.builder()
                .userResponseDto(userResponseDto)
                .isFollowed(isFollowed)
                .build();
    }
}
