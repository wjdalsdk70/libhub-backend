package se.libraryhub.library.domain;

import lombok.Getter;
import se.libraryhub.global.util.BaseTimeEntity;
import se.libraryhub.project.domain.Project;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
public class Library extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer library_id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String libraryname;

    private String version;

    private String usecase;
}