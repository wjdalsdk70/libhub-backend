package se.libraryhub.project.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.libraryhub.config.oauth.PrincipalDetails;
import se.libraryhub.project.domain.Project;
import se.libraryhub.project.domain.dto.ProjectResponseDto;
import se.libraryhub.project.service.ProjectService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @PostMapping("/post")
    public void postProject(){

    }

//    @GetMapping("/post")
//    public ProjectResponseDto getProject(Long projectId){
//
//    }
}