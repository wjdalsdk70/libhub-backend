package se.libraryhub.folllow.domain;

import lombok.*;
import se.libraryhub.user.domain.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "followed_user_id")
    private User followUser;

    @ManyToOne
    @JoinColumn(name = "follower_user_id")
    private User followerUser;

    @Builder
    public Follow(User followUser, User followerUser) {
        this.followUser = followUser;
        this.followerUser = followerUser;
    }
}
