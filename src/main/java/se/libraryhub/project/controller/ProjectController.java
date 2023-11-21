package se.libraryhub.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import se.libraryhub.project.domain.Project;
import se.libraryhub.project.domain.dto.*;
import se.libraryhub.project.service.ProjectService;

import static se.libraryhub.security.oauth.SecurityUtil.getCurrentUser;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ProjectResponseDto postProject(@RequestBody ProjectRequestDto projectRequestDto){
        return projectService.postProject(projectRequestDto, getCurrentUser());
    }

    @GetMapping
    public ProjectResult getAllProjects(){
        return projectService.getProjectList(getCurrentUser());
    }

    @GetMapping("/{projectId}")
    public ProjectContentResponseDto getProject(@PathVariable Long projectId){
        return projectService.getProject(projectId);
    }

    @PostMapping("/hashtag")
    public void addHashtag(@RequestBody ProjectHashtagRequestDto projectHashtagRequestDto){
        projectService.addHashtag(projectHashtagRequestDto);
    }

    @GetMapping("/page/{pageNumber}")
    public Page<Project> pagingProject(@PathVariable int pageNumber, Pageable pageable){
        return projectService.pagingProjects(pageNumber);
    }

    @GetMapping("/mypage/{pageNumber}")
    public Page<Project> pagingMyProject(@PathVariable int pageNumber, Pageable pageable){
        return projectService.pagingMyProjects(getCurrentUser(), pageNumber);
    }
}