package se.libraryhub.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.libraryhub.favorite.service.FavoriteService;
import se.libraryhub.folllow.service.FollowService;
import se.libraryhub.project.domain.PagingMode;
import se.libraryhub.project.domain.dto.response.ProjectResponseDto;
import se.libraryhub.project.domain.dto.response.ProjectResult;
import se.libraryhub.security.oauth.SecurityUtil;
import se.libraryhub.global.error.user.UserNotFoundException;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.domain.dto.request.UserUpdateRequestDto;
import se.libraryhub.user.domain.dto.response.UserContentResponseDto;
import se.libraryhub.user.domain.dto.response.UserLibraryResponseDto;
import se.libraryhub.user.domain.dto.response.UserResponseDto;
import se.libraryhub.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final FavoriteService favoriteService;
    private final FollowService followService;

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

    public ProjectResult getFavoriteProjects(int pageNumber, PagingMode pagingMode) {
        return favoriteService.userFavoriteInfo(pageNumber, pagingMode);
    }

    public List<UserLibraryResponseDto> getUsedLibraries(Long userId){
        List<UserLibraryResponseDto> userLibraryResponseDtoList = new ArrayList<>();

        List<Object[]> libraries = userRepository.findFrequentlyUsedLibrariesGroupedByLibraryName(userId);
        libraries.forEach(objects -> {
            if (Objects.nonNull(objects) && objects.length == 2) {
                String libraryName = (String) objects[0];
                Long count = (Long) objects[1];

                userLibraryResponseDtoList.add(UserLibraryResponseDto.builder()
                        .libraryName(libraryName)
                        .count(count.intValue())
                        .build());
            }
        });

        return userLibraryResponseDtoList;
    }

    public UserContentResponseDto getAnotherUserInfo(Long userId) {
        User anotherUser = getUserProfile(userId);
        boolean isFollowed = followService.isFollowed(SecurityUtil.getCurrentUser().getId(), userId);
        return UserContentResponseDto.builder()
                .userResponseDto(UserResponseDto.of(anotherUser))
                .isFollowed(isFollowed)
                .build();
    }
}
