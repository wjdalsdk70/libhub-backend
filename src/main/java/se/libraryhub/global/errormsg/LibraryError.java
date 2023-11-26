package se.libraryhub.global.errormsg;

import lombok.Getter;

@Getter
public enum LibraryError {

    LIBRARY_NOT_EXIST("해당 라이브러리가 존재하지 않습니다.");

    private final String message;

    LibraryError(String message) {
        this.message = message;
    }
}
