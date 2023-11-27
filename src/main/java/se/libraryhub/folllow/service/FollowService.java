//package se.libraryhub.folllow.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import se.libraryhub.folllow.domain.Follow;
//import se.libraryhub.folllow.repository.FollowRepository;
//import se.libraryhub.user.domain.User;
//
//import java.util.List;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class FollowService {
//
//    private final FollowRepository followRepository;
//
//    // 함수 추가: 유저가 다른 유저를 팔로우
//    public void followUser(User userToFollow) {
//        Follow follow = new Follow();
//        follow.setFollowerUser(this);
//        follow.setFollowUser(userToFollow);
//
//        // Follow 저장
//        followRepository.save(follow);
//    }
//
//    // 함수 추가: 유저가 팔로우 취소
//    public void unfollowUser(User userToUnfollow) {
//        Follow follow = followRepository.findByFollowerUserAndFollowUser(this, userToUnfollow);
//        if (follow != null) {
//            followRepository.delete(follow);
//        }
//    }
//
//    // 함수 추가: 유저가 팔로우하는 유저들 정보 가져오기
//    public List<User> getFollowing() {
//        List<Follow> follows = followRepository.findByFollowerUser(this);
//        return follows.stream()
//                .map(Follow::getFollowUser)
//                .toList();
//    }
//}
