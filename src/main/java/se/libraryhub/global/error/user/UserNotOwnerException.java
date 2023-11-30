package se.libraryhub.global.error.user;

import se.libraryhub.global.errormsg.UserError;

public class UserNotOwnerException extends RuntimeException{
    public UserNotOwnerException() {
        super(UserError.USER_NOT_OWNER.getMessage());
    }
}
