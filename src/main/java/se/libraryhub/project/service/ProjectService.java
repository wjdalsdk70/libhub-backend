package se.libraryhub.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.libraryhub.global.error.project.ProjectNotFoundException;
import se.libraryhub.hashtag.domain.Hashtag;
import se.libraryhub.hashtag.repository.HashtagRepository;
import se.libraryhub.library.domain.dto.response.LibraryContentResponseDto;
import se.libraryhub.library.repository.LibraryRepository;
import se.libraryhub.library.service.LibraryService;
import se.libraryhub.project.domain.Project;
import se.libraryhub.project.domain.dto.request.ProjectContentRequestDto;
import se.libraryhub.project.domain.dto.request.ProjectHashtagRequestDto;
import se.libraryhub.project.domain.dto.response.ProjectContentResponseDto;
import se.libraryhub.project.domain.dto.response.ProjectResponseDto;
import se.libraryhub.project.domain.dto.response.ProjectResult;
import se.libraryhub.project.repository.ProjectRepository;
import se.libraryhub.user.domain.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService{

    private final ProjectRepository projectRepository;
    private final HashtagRepository hashtagRepository;
    private final LibraryRepository libraryRepository;
    private final LibraryService libraryService;

    public ProjectResponseDto postProject(ProjectContentRequestDto projectContentRequestDto, User user) {
        Project project = ProjectContentRequestDto.toEntity(projectContentRequestDto, user);
        Project postedProject = projectRepository.save(project);

        projectContentRequestDto.getProjectHashtags().forEach(s -> {
            hashtagRepository.save(Hashtag.projectHashtag(postedProject, s));
        });
        List<String> projectHashtags = hashtagRepository.findAllByProject(postedProject).stream()
                .map(Hashtag::getContent).toList();

        return ProjectResponseDto.of(postedProject, projectHashtags, user);
    }

    public ProjectResult getProjectList(){
        List<Project> allProjects = projectRepository.findAll();
        List<Project> publicProjects = allProjects.stream().filter(Project::getIsPublic).toList();
        return new ProjectResult(publicProjects.stream().map((project -> {
            List<String> projectHashtags = hashtagRepository.findAllByProject(project).stream()
                    .map(Hashtag::getContent).toList();
            return ProjectResponseDto.of(project, projectHashtags, project.getUser());
        })).toList());
    }

    public ProjectResult getUserProjectList(User user){
        List<Project> userProjects = projectRepository.findAllByUser(user);
        return new ProjectResult(userProjects.stream().map((project -> {
            List<String> projectHashtags = hashtagRepository.findAllByProject(project).stream()
                    .map(Hashtag::getContent).toList();
            return ProjectResponseDto.of(project, projectHashtags, user);
        })).toList());
    }

    public ProjectContentResponseDto getProject(Long projectId){
        Project project = projectRepository.findProjectByProjectId(projectId)
                .orElseThrow(ProjectNotFoundException::new);
        List<String> projectHashtags = hashtagRepository.findAllByProject(project).stream().map(Hashtag::getContent).toList();
        List<LibraryContentResponseDto> libraryContentResponseDtos = libraryService.getProjectLibraries(project);
        return ProjectContentResponseDto.of(project, projectHashtags, libraryContentResponseDtos);
    }

    public void addHashtag(ProjectHashtagRequestDto projectHashtagRequestDto){

        Project project = projectRepository.findProjectByProjectId(projectHashtagRequestDto.getProjectId())
                .orElseThrow(ProjectNotFoundException::new);
        Hashtag hashtag = Hashtag.projectHashtag(project, projectHashtagRequestDto.getContent());
        hashtagRepository.save(hashtag);
    }

    public ProjectContentResponseDto updateProject(Long projectId, ProjectContentRequestDto projectContentRequestDto){
        Project findProject = projectRepository.findProjectByProjectId
                (projectId).orElseThrow(ProjectNotFoundException::new);
        Project.updateProject(findProject, projectContentRequestDto);
        Project updatedProject = projectRepository.save(findProject);
        hashtagRepository.deleteAllByProject(updatedProject);
        projectContentRequestDto.getProjectHashtags().stream().forEach(s -> {
            hashtagRepository.save(Hashtag.projectHashtag(updatedProject, s));
        });
        List<String> projectHashtags = hashtagRepository.findAllByProject(updatedProject).stream().map(
                hashtag -> hashtag.getContent()
        ).toList();
        List<LibraryContentResponseDto> libraryContentResponseDtos = libraryService.getProjectLibraries(updatedProject);
        return ProjectContentResponseDto.of(updatedProject, projectHashtags, libraryContentResponseDtos);
    }

    public void deleteProject(Long projectId){
        Project findProject = projectRepository.findProjectByProjectId(projectId)
                .orElseThrow(ProjectNotFoundException::new);
        projectRepository.delete(findProject);
    }

    public Page<Project> pagingProjects(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("createDate").descending());
        return projectRepository.findAll(pageable);
    }

    public Page<Project> pagingMyProjects(User user, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("createDate").descending());
        return projectRepository.findByUser(user, pageable);
    }
}
