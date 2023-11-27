package se.libraryhub.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static se.libraryhub.security.oauth.SecurityUtil.getCurrentUser;

import se.libraryhub.project.domain.PagingMode;
import se.libraryhub.project.domain.dto.response.ProjectResponseDto;
import se.libraryhub.project.domain.dto.response.ProjectResult;
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

    @Operation(summary = "로그인",
            description = "로그인 성공 시 200 OK 반환")
    @GetMapping("/login")
    public ResponseEntity<?> login(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "유저 정보 업데이트")
    @PostMapping("/update")
    public UserResponseDto updateUser(@RequestBody UserUpdateRequestDto userRequestDto){
        User user = getCurrentUser();
        return UserResponseDto.of(
                userService.updateUser(user, userRequestDto)
        );
    }

    @Operation(summary = "회원 탈퇴",
            description = "현재 로그인한 유저 정보 삭제")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(){
        try{
            userService.deleteUser();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "현재 로그인한 유저의 정보 조회")
    @GetMapping("/info")
    public UserResponseDto getUserInfo(){
        return UserResponseDto.of(userService.getUserProfile(getCurrentUser().getId()));
    }

    @Operation(summary = "다른 유저의 상세 정보 조회 + 팔로우하는 유저인지 확인 가능",
            description = "기존 UserResponseDto 에서 isFollowed 필드가 추가됐습니다. 팔로우 중인 유저인지 확인 가능")
    @GetMapping("/info/{userId}")
    public UserContentResponseDto getAnotherUserInfo(@PathVariable("userId") Long userId){
        return userService.getAnotherUserInfo(userId);
    }

    @Operation(summary = "현재 로그인한 유저가 즐겨찾기 한 프로젝트들 조회")
    @GetMapping("/favorites/{pageNumber}")
    public ProjectResult getFavoriteProjects(@PathVariable int pageNumber,
                                             @RequestParam PagingMode pagingMode){
        return userService.getFavoriteProjects(pageNumber, pagingMode);
    }

    @Operation(summary = "특정 유저가 자주 사용하는 라이브러리 목록 조회")
    @GetMapping("/usedLibrary/{userId}")
    public List<UserLibraryResponseDto> getUsedLibraries(@PathVariable Long userId){
        return userService.getUsedLibraries(userId);
    }
}
