package se.libraryhub.folllow.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import se.libraryhub.folllow.service.FollowService;
import se.libraryhub.security.oauth.SecurityUtil;
import se.libraryhub.user.domain.dto.response.UserContentResponseDto;
import se.libraryhub.user.domain.dto.response.UserResponseDto;

import java.util.*;

import static se.libraryhub.security.oauth.SecurityUtil.getCurrentUser;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follow")
public class FollowController {

    private final FollowService followService;

    @Operation(summary = "유저를 팔로우 또는 언팔로우",
            description = "쿼리 파라미터로 팔로우 또는 팔로잉 할 유저 ID를 넣으면 됩니다")
    @PostMapping("/{followUserId}")
    public boolean followingUser(@PathVariable Long followUserId){
        return followService.followingUser(getCurrentUser().getId(), followUserId);
    }

    @Operation(summary = "자신을 팔로우하는 유저들(팔로워) 목록 조회",
            description = "조회할 User 고유 ID는 현재 로그인한 유저의 정보입니다.")
    @GetMapping("/followerList")
    public List<UserContentResponseDto> getFollowerList(){
        return followService.getFollowerList(getCurrentUser().getId());
    }

    @Operation(summary = "자신이 팔로우하는 유저들(팔로잉) 목록 조회",
            description = "조회할 User 고유 ID는 현재 로그인한 유저의 정보입니다.")
    @GetMapping("/followList")
    public List<UserContentResponseDto> getFollowList(){
        return followService.getFollowList(getCurrentUser().getId());
    }
}
