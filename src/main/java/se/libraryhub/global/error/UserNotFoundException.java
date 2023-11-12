package se.libraryhub.global.error;

import se.libraryhub.global.errormsg.UserError;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super(UserError.USER_NOT_EXIST.getMessage());
    }
}
