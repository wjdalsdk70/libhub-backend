package se.libraryhub.mockdata;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import se.libraryhub.favorite.domain.Favorite;
import se.libraryhub.favorite.repository.FavoriteRepository;
import se.libraryhub.folllow.domain.Follow;
import se.libraryhub.folllow.repository.FollowRepository;
import se.libraryhub.library.domain.Library;
import se.libraryhub.library.repository.LibraryRepository;
import se.libraryhub.library.service.LibraryService;
import se.libraryhub.librarycount.domain.LibraryCount;
import se.libraryhub.librarycount.repository.LibraryCountRepository;
import se.libraryhub.librarycount.service.LibraryCountService;
import se.libraryhub.project.domain.Project;
import se.libraryhub.project.repository.ProjectRepository;
import se.libraryhub.security.oauth.SecurityUtil;
import se.libraryhub.user.domain.Role;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static se.libraryhub.security.oauth.SecurityUtil.getCurrentUser;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final LibraryRepository libraryRepository;
    private final FavoriteRepository favoriteRepository;
    private final LibraryCountRepository libraryCountRepository;
    private final FollowRepository followRepository;

    private List<User> users = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initUser();
        initProject();
        initLibrary();
        initFavorite();
        initFollow();
    }

    public void initUser(){
        User user1 = User.builder()
                .email("user1@gmail.com")
                .profileImageUrl("url1")
                .username("kim")
                .userLinks(List.of("userLink1","userLink2"))
                .build();
        User user2 = User.builder()
                .email("user2@ajou.ac.kr")
                .profileImageUrl("url2")
                .username("dong")
                .userLinks(List.of("userLink3","userLink4"))
                .build();
        User user3 = User.builder()
                .email("user3@ajou.ac.kr")
                .profileImageUrl("url3")
                .username("hyun")
                .userLinks(List.of("userLink5","userLink6"))
                .build();
        User user4 = User.builder()
                .email("user4@gmail.com")
                .profileImageUrl("url4")
                .username("ajou")
                .userLinks(List.of("userLink7"))
                .build();

        users.add(userRepository.save(user1));
        users.add(userRepository.save(user2));
        users.add(userRepository.save(user3));
        users.add(userRepository.save(user4));
    }

    public void initProject(){
        for(int i = 0; i < 20; i++){
            Project project = Project.builder()
                    .projectLinks(List.of("purl"+i, "purl"+i+1, "purl"+i+2))
                    .projectname("Sample Public Project for User " + (i % users.size() + 1))
                    .description("Public project description")
                    .isPublic(true)
                    .user(users.get(i % users.size()))
                    .build();
            projects.add(projectRepository.save(project));
        }
        for(int i = 0; i < 10; i++){
            Project project = Project.builder()
                    .projectLinks(List.of("purl"+i, "purl"+i+1, "purl"+i+2))
                    .projectname("Sample Private Project for User " + (i % users.size() + 1))
                    .description("Private project description")
                    .isPublic(false)
                    .user(users.get(i % users.size()))
                    .build();
            projects.add(projectRepository.save(project));
        }
    }

    public void initLibrary() {
        for (int i = 0; i < 40; i++) {
            Library library = Library.builder()
                    .project(projects.get(i % projects.size()))
                    .libraryname("lib" + i % 6)
                    .version("1.0.0")
                    .description("Sample Library for project " + (i % projects.size() + 1))
                    .build();

            LibraryCount libraryCount = libraryCountRepository.findByLibraryname(library.getLibraryname())
                    .orElse(null);
            if(libraryCount != null){
                libraryCount.incrementCount();
                libraryCountRepository.save(libraryCount);
            }else{
                libraryCountRepository.save(LibraryCount.builder()
                        .libraryname(library.getLibraryname())
                        .count(1)
                        .build());
            }
            libraryRepository.save(library);
        }
    }

    public void initHashtag(){

    }

    public void initFavorite(){
        for(int i = 0; i < projects.size(); i++){
            Favorite favorite = Favorite.builder()
                    .user(users.get(i % users.size()))
                    .project(projects.get(i % projects.size()))
                    .build();
            Favorite favorite2 = Favorite.builder()
                    .user(users.get(i % users.size()))
                    .project(projects.get((i+5) % projects.size()))
                    .build();
            favoriteRepository.save(favorite);
            favoriteRepository.save(favorite2);
        }
    }

    public void initFollow(){
        Follow follow1 = Follow.builder()
                .followUser(users.get(0))
                .followerUser(users.get(1))
                .build();
        Follow follow2 = Follow.builder()
                .followUser(users.get(3))
                .followerUser(users.get(1))
                .build();
        Follow follow3 = Follow.builder()
                .followUser(users.get(1))
                .followerUser(users.get(0))
                .build();
        followRepository.save(follow1);
        followRepository.save(follow2);
        followRepository.save(follow3);
    }
}
