package se.libraryhub.global.errormsg;

import lombok.Getter;

@Getter
public enum LibraryError {

    LIBRARY_NOT_EXIST("해당 라이브러리가 존재하지 않습니다."),
    LIBRARY_COUNT_INDEX_OUT_OF_BOUND("인기 라이브러리 요청 개수가 최대 개수보다 큽니다");

    private final String message;

    LibraryError(String message) {
        this.message = message;
    }
}
