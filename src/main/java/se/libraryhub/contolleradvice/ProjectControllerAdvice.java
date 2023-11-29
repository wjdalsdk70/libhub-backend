package se.libraryhub.contolleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import se.libraryhub.global.error.project.ProjectNotFoundException;
import se.libraryhub.global.error.project.ProjectPageException;
import se.libraryhub.global.error.user.UnauthorizedAccessException;
import se.libraryhub.global.error.user.UserNotFoundException;

@RestControllerAdvice
public class ProjectControllerAdvice {

    @ExceptionHandler(ProjectNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleUserNotFoundException(ProjectNotFoundException projectNotFoundException){
        return new ResponseEntity<>(projectNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProjectPageException.class)
    @ResponseBody
    public ResponseEntity<?> handleUnauthorizedException(ProjectPageException projectPageException){
        return new ResponseEntity<>(projectPageException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
