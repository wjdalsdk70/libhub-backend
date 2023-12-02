package se.libraryhub.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import se.libraryhub.favorite.repository.FavoriteRepository;
import se.libraryhub.folllow.repository.FollowRepository;
import se.libraryhub.global.error.project.ProjectNotFoundException;
import se.libraryhub.library.domain.Library;
import se.libraryhub.library.repository.LibraryRepository;
import se.libraryhub.librarycount.domain.LibraryCount;
import se.libraryhub.librarycount.repository.LibraryCountRepository;
import se.libraryhub.project.domain.PagingMode;
import se.libraryhub.project.domain.Project;
import se.libraryhub.project.domain.dto.request.ProjectContentRequestDto;
import se.libraryhub.project.domain.dto.response.ProjectContentResponseDto;
import se.libraryhub.project.domain.dto.response.ProjectResponseDto;
import se.libraryhub.project.domain.dto.response.ProjectResult;
import se.libraryhub.project.repository.ProjectRepository;
import se.libraryhub.security.oauth.PrincipalDetails;
import se.libraryhub.security.oauth.SecurityUtil;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.repository.UserRepository;
import se.libraryhub.user.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProjectServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    ProjectService projectService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    LibraryRepository libraryRepository;
    @Autowired
    FavoriteRepository favoriteRepository;
    @Autowired
    LibraryCountRepository libraryCountRepository;
    @Autowired
    FollowRepository followRepository;

    private List<User> users = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();

    @BeforeEach
    public void init() {
        User user = userService.registerUser("loginUser", "login@test.com", "profileImageUrl", new ArrayList<>(Arrays.asList("link1", "link2")));
        PrincipalDetails principalDetails = new PrincipalDetails(user, new HashMap<>());
        Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, principalDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        initUser();
        initProject();
        initLibrary();
    }

    @Test
    void postProject() {
        User currentUser = userService.findUserById(1L);
        ProjectContentRequestDto projectContentRequestDto = new ProjectContentRequestDto(
                "Sample Project", // 프로젝트 이름
                Arrays.asList("http://samplelink1.com", "http://samplelink2.com"), // 프로젝트 링크
                "This is a sample project for testing.", // 프로젝트 설명
                true, // 공개 여부
                Arrays.asList("sample", "project", "testing") // 프로젝트 해시태그
        );
        ProjectResponseDto responseDto = projectService.postProject(projectContentRequestDto, currentUser);

        assertNotNull(responseDto);
    }


    @Test
    void getUserProjectList() {
        User currentUser = SecurityUtil.getCurrentUser();
        ProjectResult projectResult = projectService.getUserProjectList(currentUser);

        assertNotNull(projectResult);
    }

    @Test
    void updateProject() {
        User currentUser = SecurityUtil.getCurrentUser();
        ProjectContentRequestDto projectContentRequestDto = new ProjectContentRequestDto(
                "Updated Project",
                Arrays.asList("http://updatedlink1.com", "http://updatedlink2.com"),
                "This is an updated project for testing.",
                false,
                Arrays.asList("updated", "project", "testing")
        );
        Long projectId = 1L;
        ProjectContentResponseDto responseDto = projectService.updateProject(projectId, projectContentRequestDto);

        assertEquals(responseDto.getProjectname(),"Updated Project");
    }

    @Test
    void deleteProject() {
        Long projectId = 1L; // 가정: 삭제하려는 프로젝트의 ID가 1임
        projectService.deleteProject(projectId);

        assertThrows(ProjectNotFoundException.class, () -> projectService.getProject(projectId));
    }

    @Test
    void searchProject() {
        String searchQuery = "sample";
        int pageNumber = 1;
        PagingMode pagingMode = PagingMode.LATEST;
        ProjectResult projectResult = projectService.searchProject(searchQuery, pageNumber, pagingMode);

        assertNotNull(projectResult);
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
}