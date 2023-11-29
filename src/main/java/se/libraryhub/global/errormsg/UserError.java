package se.libraryhub.global.errormsg;

import lombok.Getter;

@Getter
public enum UserError {
    USER_NOT_EXIST("해당 유저가 존재하지 않습니다."),
    USER_NOT_AUTHORIZED("로그인하지 않은 사용자입니다."),
    USER_FOLLOW("팔로우하는 유저가 올바르지 않습니다.");

    private final String message;

    UserError(String message) {
        this.message = message;
    }
}
