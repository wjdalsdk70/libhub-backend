package se.libraryhub.mockdata;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import se.libraryhub.library.domain.Library;
import se.libraryhub.library.repository.LibraryRepository;
import se.libraryhub.project.domain.Project;
import se.libraryhub.project.repository.ProjectRepository;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final LibraryRepository libraryRepository;
    private List<User> users = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initUser();
        initProject();
        initLibrary();
    }

    public void initUser(){
        User user1 = User.builder()
                .email("user1@gmail.com")
                .profileImageUrl("url")
                .username("kim")
                .build();
        User user2 = User.builder()
                .email("user2@ajou.ac.kr")
                .profileImageUrl("url")
                .username("dong")
                .build();
        User user3 = User.builder()
                .email("user3@ajou.ac.kr")
                .profileImageUrl("url")
                .username("hyun")
                .build();

        users.add(userRepository.save(user1));
        users.add(userRepository.save(user2));
        users.add(userRepository.save(user3));
    }

    public void initProject(){
        for(int i = 0; i < 20; i++){
            Project project = Project.builder()
                    .projectLink("purl"+i)
                    .projectname("Sample Project for User " + (i % users.size() + 1))
                    .isPublic(true)
                    .user(users.get(i % users.size()))
                    .build();
            projects.add(projectRepository.save(project));
        }
    }

    public void initLibrary() {
        for (int i = 0; i < 40; i++) {
            Library library = Library.builder()
                    .project(projects.get(i % projects.size()))
                    .libraryname("lib" + i)
                    .version("1.0.0")
                    .usecase("Sample Library for project " + (i % projects.size() + 1))
                    .build();
            libraryRepository.save(library);
        }
    }

    public void initHashtag(){

    }
}
