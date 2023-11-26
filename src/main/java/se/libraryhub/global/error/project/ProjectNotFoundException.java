package se.libraryhub.global.error.project;

import se.libraryhub.global.errormsg.ProjectError;
import se.libraryhub.global.errormsg.UserError;

public class ProjectNotFoundException extends RuntimeException{

    public ProjectNotFoundException() {
        super(ProjectError.PROJECT_NOT_EXIST.getMessage());
    }
}
