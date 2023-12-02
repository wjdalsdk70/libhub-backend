package se.libraryhub.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import se.libraryhub.global.error.user.UserNotFoundException;
import se.libraryhub.security.oauth.PrincipalDetails;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.domain.dto.request.UserUpdateRequestDto;
import se.libraryhub.user.domain.dto.response.UserContentResponseDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    @BeforeEach
    public void init() {
        User user = userService.registerUser("loginUser", "login@test.com", "profileImageUrl", new ArrayList<>(Arrays.asList("link1", "link2")));
        PrincipalDetails principalDetails = new PrincipalDetails(user, new HashMap<>());
        Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, principalDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void setUserContext(User user){
        PrincipalDetails principalDetails = new PrincipalDetails(user, new HashMap<>());
        Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, principalDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void registerUser() {
        User user = userService.registerUser("username", "email@test.com", "profileImageUrl", new ArrayList<>(Arrays.asList("link1", "link2")));

        assertNotNull(user);
        assertEquals("username", user.getUsername());
        assertEquals("email@test.com", user.getEmail());
        assertEquals("profileImageUrl", user.getProfileImageUrl());
        assertEquals(Arrays.asList("link1", "link2"), user.getUserLinks());
    }

    @Test
    void updateUser() {
        User user = userService.registerUser("username", "email@test.com", "profileImageUrl", new ArrayList<>(Arrays.asList("link1", "link2")));
        UserUpdateRequestDto userUpdateRequestDto = new UserUpdateRequestDto("newUsername", "newProfileImageUrl", new ArrayList<>(Arrays.asList("newLink1", "newLink2")));
        System.out.println(user);
        User updatedUser = userService.updateUser(user, userUpdateRequestDto);

        assertNotNull(updatedUser);
        assertEquals("newUsername", updatedUser.getUsername());
        assertEquals("newProfileImageUrl", updatedUser.getProfileImageUrl());
        assertEquals(Arrays.asList("newLink1", "newLink2"), updatedUser.getUserLinks());
    }


    @Test
    void deleteUser() {
        User user = userService.registerUser("username", "email@test.com", "profileImageUrl", new ArrayList<>(Arrays.asList("link1", "link2")));
        setUserContext(user);
        userService.deleteUser();
        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserProfile(user.getId());
        });
    }

    @Test
    void getUserProfile() {
        User user = userService.registerUser("username", "email@test.com", "profileImageUrl", new ArrayList<>(Arrays.asList("link1", "link2")));
        User fetchedUser = userService.getUserProfile(user.getId());
        assertNotNull(fetchedUser);
        assertEquals(user.getUsername(), fetchedUser.getUsername());
    }

    @Test
    void searchUserByUsername() {
        userService.registerUser("username1", "email1@test.com", "profileImageUrl1",new ArrayList<>(Arrays.asList("link1", "link2")));
        userService.registerUser("username2", "email2@test.com", "profileImageUrl2", new ArrayList<>(Arrays.asList("link1", "link2")));

        List<User> users = userService.searchUserByUsername("username");
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    void getAnotherUserInfo() {
        User user = userService.registerUser("username", "email@test.com", "profileImageUrl", new ArrayList<>(Arrays.asList("link1", "link2")));
        UserContentResponseDto userContentResponseDto = userService.getAnotherUserInfo(user.getId());
        assertNotNull(userContentResponseDto);
        assertEquals(user.getUsername(), userContentResponseDto.getUserResponseDto().getUsername());
    }
}