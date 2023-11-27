package se.libraryhub.folllow.domain;

import lombok.*;
import se.libraryhub.global.error.user.UserFollowException;
import se.libraryhub.user.domain.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "follow_user_id")
    private User followUser;

    @ManyToOne
    @JoinColumn(name = "follower_user_id")
    private User followerUser;

    @Builder
    public Follow(User followUser, User followerUser) {
        validUser(followUser, followerUser);
        this.followUser = followUser;
        this.followerUser = followerUser;
    }

    private void validUser(User followUser, User followerUser){
        if(Objects.equals(followUser, followerUser)){
            throw new UserFollowException();
        }
    }
}
