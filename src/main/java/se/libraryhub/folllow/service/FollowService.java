package se.libraryhub.folllow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.libraryhub.folllow.domain.Follow;
import se.libraryhub.folllow.repository.FollowRepository;
import se.libraryhub.global.error.user.UserNotFoundException;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.domain.dto.response.UserContentResponseDto;
import se.libraryhub.user.domain.dto.response.UserResponseDto;
import se.libraryhub.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public boolean followingUser(Long myId, Long followUserId) {
        Follow follow = followRepository.findByFollowUserIdAndFollowerUserId(followUserId, myId)
                .orElse(null);
        if (follow != null) {
            followRepository.delete(follow);
            return true;
        } else {
            follow = Follow.builder()
                    .followUser(userRepository.findUserById(followUserId).orElseThrow(UserNotFoundException::new))
                    .followerUser(userRepository.findUserById(myId).orElseThrow(UserNotFoundException::new))
                    .build();
            followRepository.save(follow);
            return true;
        }
    }

    public List<UserContentResponseDto> getFollowerList(Long userId) {
        List<Follow> followerList = followRepository.findByFollowUserId(userId);
        List<UserContentResponseDto> responseList = new ArrayList<>();
        for (Follow follow : followerList) {
            User follower = follow.getFollowerUser();
            UserContentResponseDto responseDto = UserContentResponseDto.of
                    (UserResponseDto.of(follower), isFollowed(follow.getId(), userId));
            responseList.add(responseDto);
        }
        return responseList;
    }

    public List<UserContentResponseDto> getFollowList(Long userId) {
        List<Follow> followList = followRepository.findByFollowerUserId(userId);
        List<UserContentResponseDto> responseList = new ArrayList<>();
        for (Follow follow : followList) {
            User followUser = follow.getFollowUser();
            UserContentResponseDto responseDto = UserContentResponseDto.of
                    (UserResponseDto.of(followUser), true);
            responseList.add(responseDto);
        }
        return responseList;
    }

    public boolean isFollowed(Long followUserId, Long followerUserId){
        Follow follow = followRepository.findByFollowUserIdAndFollowerUserId(followUserId, followerUserId)
                .orElse(null);
        if(follow != null){
            return true;
        }
        return false;
    }
}
