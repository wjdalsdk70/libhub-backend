package se.libraryhub.folllow.domain;

import lombok.Getter;
import lombok.Setter;
import se.libraryhub.user.domain.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long followUserId;

    Long followerUserId;
}
