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
    public String goHome(){
        return "This is main page";
    }

    @PostMapping("/update")
    public UserResponseDto updateUser(@RequestBody UserRequestDto userRequestDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        User user = getUserByPrincipal(principalDetails);
        return UserResponseDto.ofUserEntity(
                userService.updateUser(user.getId(), userRequestDto.getUsername(), userRequestDto.getProfileImageUrl())
        );
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal PrincipalDetails principalDetails){
        try{
            User user = getUserByPrincipal(principalDetails);
            userService.deleteUser(user.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/profile")
    public UserResponseDto getUserProfile(@AuthenticationPrincipal PrincipalDetails principalDetails){
        return UserResponseDto.ofUserEntity(getUserByPrincipal(principalDetails));
    }

    @GetMapping("/profileImg/{userId}")
    public String getUserProfileImg(@PathVariable("userId") Long userId){
        return userService.getProfileImg(userId);
    }

    public User getUserByPrincipal(PrincipalDetails principalDetails){
        return userService.findUserByEmail(principalDetails.getUser().getEmail());
    }
}
