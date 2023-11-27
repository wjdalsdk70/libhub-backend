package se.libraryhub.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static se.libraryhub.security.oauth.SecurityUtil.getCurrentUser;

import se.libraryhub.project.domain.dto.response.ProjectResponseDto;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.domain.dto.response.UserContentResponseDto;
import se.libraryhub.user.domain.dto.response.UserLibraryResponseDto;
import se.libraryhub.user.domain.dto.response.UserResponseDto;
import se.libraryhub.user.domain.dto.request.UserUpdateRequestDto;
import se.libraryhub.user.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public User goHome(){
        return getCurrentUser();
    }

    @Operation(summary = "다른 유저의 상세 정보 조회 + 팔로우하는 유저인지 확인 가능",
            description = "기존 UserResponseDto 에서 isFollowed 필드가 추가됐습니다. 팔로우 중인 유저인지 확인 가능")
    @GetMapping("/info/{userId}")
    public UserContentResponseDto getAnotherUserInfo(@PathVariable("userId") Long userId){
        return userService.getAnotherUserInfo(userId);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update")
    public UserResponseDto updateUser(@RequestBody UserUpdateRequestDto userRequestDto){
        User user = getCurrentUser();
        return UserResponseDto.of(
                userService.updateUser(user, userRequestDto)
        );
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(){
        try{
            userService.deleteUser();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/profile")
    public UserResponseDto getUserProfile(){
        return UserResponseDto.of(getCurrentUser());
    }

    @GetMapping("/profileImg/{userId}")
    public String getUserProfileImg(@PathVariable("userId") Long userId){
        return userService.getProfileImg(userId);
    }

    @GetMapping("/favorites")
    public List<ProjectResponseDto> getFavoriteProjects(){
        return userService.getFavoriteProjects();
    }

    @GetMapping("/usedLibrary/{userId}")
    public List<UserLibraryResponseDto> getUsedLibraries(@PathVariable Long userId){
        return userService.getUsedLibraries(userId);
    }
}
