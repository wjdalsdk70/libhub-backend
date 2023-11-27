package se.libraryhub.folllow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.libraryhub.folllow.domain.Follow;
import se.libraryhub.folllow.service.FollowService;
import se.libraryhub.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findByFollowerUserId(Long followerUserId);

    List<Follow> findByFollowUserId(Long followUserId);

    Optional<Follow> findByFollowUserIdAndFollowerUserId(Long followUserId, Long followerUserId);
}
