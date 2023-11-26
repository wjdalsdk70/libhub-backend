package se.libraryhub.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.libraryhub.favorite.service.FavoriteService;
import se.libraryhub.project.domain.dto.response.ProjectResponseDto;
import se.libraryhub.security.oauth.SecurityUtil;
import se.libraryhub.global.error.user.UserNotFoundException;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.domain.dto.request.UserUpdateRequestDto;
import se.libraryhub.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final FavoriteService favoriteService;

    public User registerUser(String username, String email, String profileImageUrl, List<String> userLinks) {
        if(isExistingEmail(email)){
            throw new UserNotFoundException();
        }
        return userRepository.save(new User(username, email, profileImageUrl, userLinks));
    }

    public User updateUser(User user, UserUpdateRequestDto userUpdateRequestDto) {
        User findUser = findUserById(user.getId());
        findUser.updateUser(userUpdateRequestDto.getUsername(),
                userUpdateRequestDto.getProfileImageUrl(),
                userUpdateRequestDto.getUserLinks());
        userRepository.save(findUser);
        return findUser;
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

    public List<ProjectResponseDto> getFavoriteProjects() {
        return favoriteService.userFavoriteInfo();
    }
}
