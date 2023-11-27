package se.libraryhub.global.error.user;

import se.libraryhub.global.errormsg.UserError;

public class UserFollowException extends RuntimeException{
    public UserFollowException() {
        super(UserError.USER_FOLLOW.getMessage());
    }
}
