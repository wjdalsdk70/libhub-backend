package se.libraryhub.global.error.project;

import se.libraryhub.global.errormsg.ProjectError;

public class ProjectPageException  extends RuntimeException{

    public ProjectPageException() {
        super(ProjectError.PROJECT_PAGE_OVER.getMessage());
    }
}
