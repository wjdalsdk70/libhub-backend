package se.libraryhub.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.libraryhub.config.oauth.SecurityUtil;
import se.libraryhub.global.error.UserNotFoundException;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;

    public User registerUser(String username, String email, String profileImageUrl) {
        if(isExistingEmail(email)){
            throw new UserNotFoundException();
        }
        return userRepository.save(new User(username, email, profileImageUrl));
    }

    public User updateUser(Long userId, String username, String profileImageUrl) {
        User user = findUserById(userId);
        user.updateUser(username, profileImageUrl);
        userRepository.save(user);
        return user;
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public User findUserById(Long userId){
        return userRepository.findUserById(userId).orElseThrow((UserNotFoundException::new));
    }

    public void deleteUser() {
        userRepository.delete(SecurityUtil.getCurrentUser());
    }

    public User getUserProfile(Long userId) {
        return userRepository.findUserById(userId).orElseThrow(UserNotFoundException::new);
    }

    public String getProfileImg(Long userId) {
        User user = findUserById(userId);
        return user.getProfileImageUrl();
    }

    public boolean isExistingEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean logoutUser(Long userId) {
        return false;
    }

    public List<User> searchUserByUsername(String username) {
        return userRepository.searchAllByUsername(username);
    }
}
