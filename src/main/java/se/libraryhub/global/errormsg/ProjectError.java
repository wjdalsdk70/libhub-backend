package se.libraryhub.global.errormsg;

import lombok.Getter;

@Getter
public enum ProjectError {
    PROJECT_NOT_EXIST("해당 유저가 존재하지 않습니다.");

    private final String message;

    ProjectError(String message) {
        this.message = message;
    }
}
