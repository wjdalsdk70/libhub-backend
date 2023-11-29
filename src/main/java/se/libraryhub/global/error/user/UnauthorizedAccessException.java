package se.libraryhub.global.error.user;

import se.libraryhub.global.errormsg.UserError;

public class UnauthorizedAccessException extends RuntimeException{

    public UnauthorizedAccessException() {
        super(UserError.USER_NOT_AUTHORIZED.getMessage());
    }
}
