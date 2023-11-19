package se.libraryhub.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static se.libraryhub.config.oauth.SecurityUtil.getAccessToken;
import static se.libraryhub.config.oauth.SecurityUtil.getCurrentUser;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.domain.dto.request.UserRequestDto;
import se.libraryhub.user.domain.dto.UserResponseDto;
import se.libraryhub.user.domain.dto.request.UserUpdateRequestDto;
import se.libraryhub.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public User goHome(){
        System.out.println(getAccessToken());
        return getCurrentUser();
    }

    @PostMapping("/update")
    public UserResponseDto updateUser(@RequestBody UserUpdateRequestDto userRequestDto){
        User user = getCurrentUser();
        return UserResponseDto.of(
                userService.updateUser(user.getId(), userRequestDto.getUsername(), userRequestDto.getProfileImageUrl())
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

}
