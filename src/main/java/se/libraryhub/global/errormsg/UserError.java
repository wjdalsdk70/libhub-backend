package se.libraryhub.global.errormsg;

import lombok.Getter;

@Getter
public enum UserError {
    USER_NOT_EXIST("해당 유저가 존재하지 않습니다.");

    private final String message;

    UserError(String message) {
        this.message = message;
    }
}
