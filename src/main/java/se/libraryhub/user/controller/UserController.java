package se.libraryhub.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import se.libraryhub.config.oauth.PrincipalDetails;
import static se.libraryhub.config.oauth.SecurityUtil.getCurrentUser;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.domain.dto.UserRequestDto;
import se.libraryhub.user.domain.dto.UserResponseDto;
import se.libraryhub.user.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/home")
    public User goHome(){
        return getCurrentUser();
    }

    @PostMapping("/update")
    public UserResponseDto updateUser(@RequestBody UserRequestDto userRequestDto){
        User user = getCurrentUser();
        return UserResponseDto.of(
                userService.updateUser(user.getId(), userRequestDto.getUsername(), userRequestDto.getProfileImageUrl())
        );
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(){
        try{
            User user = getCurrentUser();
            userService.deleteUser(user.getId());
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
