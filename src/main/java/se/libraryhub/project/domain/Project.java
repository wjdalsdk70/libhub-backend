package se.libraryhub.project.domain;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import se.libraryhub.global.util.BaseTimeEntity;
import se.libraryhub.user.domain.User;

import javax.persistence.*;

@Entity
@Getter
public class Project extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer project_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String projectname;

    private String projectLink;

    private Boolean isPublic;
}
