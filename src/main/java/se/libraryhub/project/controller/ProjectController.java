package se.libraryhub.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "페이지에서 전체 프로젝트 페이징",
            description = "pageNumber에 페이지 페이지 번호를 입력한다. 처음에 0으로 페이지를 조회해서 전체 페이지 크기를 구할 수 있으므로, 처음 보여줄 때 0을 넣고 조회하도록 추천,")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "최대 프로젝트 페이지 수를 넘는 요청을 했을 때 발생")
    })
    @GetMapping("/page/{pageNumber}")
    public Page<Project> pagingProject(@PathVariable int pageNumber, Pageable pageable){
        return projectService.pagingProjects(pageNumber);
    }

    @Operation(summary = "마이 페이지에서 자신의 프로젝트 페이징",
            description = "pageNumber에 페이지 페이지 번호를 입력한다. 처음에 0으로 페이지를 조회해서 전체 페이지 크기를 구할 수 있으므로, 처음 보여줄 때 0을 넣고 조회하도록 추천,")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "최대 프로젝트 페이지 수를 넘는 요청을 했을 때 발생")
    })
    @GetMapping("/mypage/{pageNumber}")
    public Page<Project> pagingMyProject(@PathVariable int pageNumber, Pageable pageable){
        return projectService.pagingMyProjects(getCurrentUser(), pageNumber);
    }
}