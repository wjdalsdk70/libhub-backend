package se.libraryhub.global.error.user;

import se.libraryhub.global.errormsg.UserError;

public class UserEmailExistException extends RuntimeException{
    public UserEmailExistException() {
        super(UserError.USER_EMAIL_EXIST.getMessage());
    }
}
