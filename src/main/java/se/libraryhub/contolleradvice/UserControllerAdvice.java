package se.libraryhub.contolleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import se.libraryhub.global.error.UserNotFoundException;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException notFoundException){
        return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

}
