package se.libraryhub.global.errormsg;

import lombok.Getter;

@Getter
public enum ProjectError {
    PROJECT_NOT_EXIST("해당 프로젝트가 존재하지 않습니다."),
    PROJECT_PAGE_OVER("유효하지 않은 페이지 번호입니다.");

    private final String message;

    ProjectError(String message) {
        this.message = message;
    }
}
