package se.libraryhub.user.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import se.libraryhub.global.util.BaseTimeEntity;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private Role role = Role.GUEST;

    @Builder
    public User(String username, String email, String profileImageUrl) {
        this.username = username;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
    }

    public User() {
    }

    public void updateUser(String username, String profileImageUrl){
        this.username = username;
        this.profileImageUrl = profileImageUrl;
    }
}
