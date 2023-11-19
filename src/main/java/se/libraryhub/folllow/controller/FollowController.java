package se.libraryhub.folllow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.libraryhub.user.domain.dto.UserResponseDto;

import java.util.*;

@RestController
@RequestMapping("api/follow")
public class FollowController {

    @PostMapping("/follow")
    public boolean followingUser(Long followerUserId, Long followUserId){
        return true;
    }

    @PostMapping("/unfollow")
    public boolean unfollowUser(Long followerUserId, Long followUserId){
        return true;
    }

    @GetMapping("/list/{id}")
    public List<UserResponseDto> getFollowerList(@PathVariable("id") Long userId){
        return new ArrayList<>();
    }

    @GetMapping("/list/{id}/follower")
    public List<UserResponseDto> getFollowList(@PathVariable("id") Long userId){
        return new ArrayList<>();
    }
}
