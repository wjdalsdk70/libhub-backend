package se.libraryhub.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.libraryhub.global.error.UserNotFoundException;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User registerUser(String username, String email, String profileImageUrl) {
        if(isExistingEmail(email)){
            throw new UserNotFoundException();
        }
        return userRepository.save(new User(username, email, profileImageUrl));
    }

    @Override
    public User updateUser(Long userId, String username, String profileImageUrl) {
        User user = findUserById(userId);
        user.updateUser(username, profileImageUrl);
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User findUserById(Long userId){
        return userRepository.findUserById(userId).orElseThrow((UserNotFoundException::new));
    }

    @Override
    public boolean deleteUser(Long userId) {
        userRepository.delete(findUserById(userId));
        return false;
    }

    @Override
    public User getUserProfile(Long userId) {
        return null;
    }

    @Override
    public String getProfileImg(Long userId) {
        User user = findUserById(userId);
        return user.getProfileImageUrl();
    }

    @Override
    public boolean isExistingEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
    @Override
    public boolean logoutUser(Long userId) {
        return false;
    }

    @Override
    public List<User> searchUserByUsername(String username) {
        return null;
    }
}
