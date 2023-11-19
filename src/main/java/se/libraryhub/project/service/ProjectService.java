package se.libraryhub.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.libraryhub.global.error.ProjectNotFoundException;
import se.libraryhub.hashtag.domain.Hashtag;
import se.libraryhub.hashtag.repository.HashtagRepository;
import se.libraryhub.project.domain.Project;
import se.libraryhub.project.domain.dto.*;
import se.libraryhub.project.repository.ProjectRepository;
import se.libraryhub.user.domain.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService{

    private final ProjectRepository projectRepository;
    private final HashtagRepository hashtagRepository;

    public ProjectResponseDto postProject(ProjectRequestDto projectRequestDto, User user) {
        Project project = projectRequestDto.toEntity(projectRequestDto, user);
        Project postedProject = projectRepository.save(project);
        return ProjectResponseDto.of(postedProject, user);
    }

    public ProjectResult getProjectList(User user){
        List<Project> userProjects = projectRepository.findAllByUser(user);
        return new ProjectResult(userProjects.stream().map((project -> ProjectResponseDto.of(project, user))).toList());
    }

//    TODO: 라이브러리 리스트들도 가져오도록 수정
    public ProjectContentResponseDto getProject(Long projectId){
        Project project = projectRepository.findProjectByProjectId(projectId)
                .orElseThrow(ProjectNotFoundException::new);
        List<String> projectHashtags = hashtagRepository.findAllByProject(project).stream().map(Hashtag::getContent).toList();
        return ProjectContentResponseDto.of(project, projectHashtags);
    }

    public void addHashtag(ProjectHashtagRequestDto projectHashtagRequestDto){

        Project project = projectRepository.findProjectByProjectId(projectHashtagRequestDto.getProjectId())
                .orElseThrow(ProjectNotFoundException::new);
        Hashtag hashtag = Hashtag.builder()
                .project(project)
                .content(projectHashtagRequestDto.getContent())
                .build();
        hashtagRepository.save(hashtag);
    }
}
