package se.libraryhub.user.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import se.libraryhub.global.util.BaseTimeEntity;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String profileImageUrl;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> userLinks;

    @Enumerated(EnumType.STRING)
    private Role role = Role.GUEST;

    @Builder
    public User(String username, String email, String profileImageUrl, List<String> userLinks) {
        this.username = username;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.userLinks = userLinks;
    }

    public void updateUser(String username, String profileImageUrl, List<String> userLinks){
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.userLinks = userLinks;
    }
}
