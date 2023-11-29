package se.libraryhub.contolleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import se.libraryhub.global.error.user.UnauthorizedAccessException;
import se.libraryhub.global.error.user.UserFollowException;
import se.libraryhub.global.error.user.UserNotFoundException;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException notFoundException){
        return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseBody
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedAccessException unauthorizedAccessException){
        return new ResponseEntity<>(unauthorizedAccessException.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserFollowException.class)
    @ResponseBody
    public ResponseEntity<?> handleUserFollowException(UserFollowException userFollowException){
        return new ResponseEntity<>(userFollowException.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
