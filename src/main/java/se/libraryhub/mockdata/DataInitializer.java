package se.libraryhub.mockdata;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import se.libraryhub.config.oauth.PrincipalDetails;
import se.libraryhub.project.domain.Project;
import se.libraryhub.project.domain.dto.ProjectRequestDto;
import se.libraryhub.project.domain.dto.ProjectResponseDto;
import se.libraryhub.project.repository.ProjectRepository;
import se.libraryhub.project.service.ProjectService;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private List<User> users = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initUser();
        initProject();
    }

    public void initUser(){
        User user1 = User.builder()
                .email("ilovekdh1208@gmail.com")
                .profileImageUrl("url")
                .username("kim")
                .build();
        User user2 = User.builder()
                .email("ilovekdh1208@ajou.ac.kr")
                .profileImageUrl("url")
                .username("dong")
                .build();
        User user3 = User.builder()
                .email("ilovegame@ajou.ac.kr")
                .profileImageUrl("url")
                .username("hyun")
                .build();

        users.add(userRepository.save(user1));
        users.add(userRepository.save(user2));
        users.add(userRepository.save(user3));
    }

    public void initProject(){
        Project project1 = Project.builder()
                .projectLink("purl1")
                .projectname("pn1")
                .isPublic(true)
                .user(users.get(0))
                .build();
        Project project2 = Project.builder()
                .projectLink("purl2")
                .projectname("pn2")
                .isPublic(false)
                .user(users.get(0))
                .build();
        Project project3 = Project.builder()
                .projectLink("purl3")
                .projectname("pn3")
                .isPublic(true)
                .user(users.get(1))
                .build();
        projects.add(projectRepository.save(project1));
        projects.add(projectRepository.save(project2));
        projects.add(projectRepository.save(project3));
    }
}
