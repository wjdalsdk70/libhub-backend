package se.libraryhub.user.service;

import org.springframework.stereotype.Service;
import se.libraryhub.user.domain.User;

import java.util.List;

public interface UserService {

    User registerUser(String username, String email, String profileImageUrl);

    User updateUser(Long userId, String username, String profileImageUrl);

    User findUserByEmail(String email);

    User findUserById(Long userId);

    boolean deleteUser(Long userId);

    User getUserProfile(Long userId);

    String getProfileImg(Long userId);

    boolean isExistingEmail(String email);
    
    boolean logoutUser(Long userId);

    List<User> searchUserByUsername(String username);
}
