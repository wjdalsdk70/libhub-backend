package se.libraryhub.project.service;

import org.springframework.stereotype.Service;
import se.libraryhub.project.domain.Project;

public interface ProjectService {

    Project findProjectByProjectId(Long Project);
}
