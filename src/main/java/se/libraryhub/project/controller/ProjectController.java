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
import se.libraryhub.project.domain.dto.request.ProjectContentRequestDto;
import se.libraryhub.project.domain.dto.request.ProjectHashtagRequestDto;
import se.libraryhub.project.domain.dto.response.ProjectContentResponseDto;
import se.libraryhub.project.domain.dto.response.ProjectResponseDto;
import se.libraryhub.project.domain.dto.response.ProjectResult;
import se.libraryhub.project.service.ProjectService;

import static se.libraryhub.security.oauth.SecurityUtil.getCurrentUser;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "프로젝트 등록",
            description = "로그인한 유저의 정보와 프로젝트 등록 정보를 함께 저장" +
                    " 프로젝트 미리보기 정보 반환, 라이브러리는 없는 상태")
    @PostMapping
    public ProjectResponseDto postProject(@RequestBody ProjectContentRequestDto projectContentRequestDto){
        return projectService.postProject(projectContentRequestDto, getCurrentUser());
    }

    @Operation(summary = "모든 공개 프로젝트 조회")
    @GetMapping
    public ProjectResult getAllProjects(){
        return projectService.getProjectList();
    }

    @Operation(summary = "현재 로그인한 유저의 모든 프로젝트 조회",
            description = "로그인한 상태인지는 세션에 유저가 있는지로 판단")
    @GetMapping("/myprojects")
    public ProjectResult getAllUserProjects(){
        return projectService.getUserProjectList(getCurrentUser());
    }

    @Operation(summary = "프로젝트 상세 내용 조회",
            description = "프로젝트 ID를 이용해 상세 내용 반환, 하위의 라이브러리들과 유저 정보 포함")
    @GetMapping("/{projectId}")
    public ProjectContentResponseDto getProject(@PathVariable Long projectId){
        return projectService.getProject(projectId);
    }

    @Operation(summary = "프로젝트 업데이트",
                description = "프로젝트 업데이트 후, 프로젝트 상세 정보 반환")
    @PatchMapping("/{projectId}")
    public ProjectContentResponseDto updateProject(@PathVariable Long projectId, @RequestBody ProjectContentRequestDto projectContentRequestDto){
        return projectService.updateProject(projectId, projectContentRequestDto);
    }

    @Operation(summary = "프로젝트 삭제")
    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable("projectId") Long projectId){
        projectService.deleteProject(projectId);
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