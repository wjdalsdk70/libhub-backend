package se.libraryhub.contolleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import se.libraryhub.global.error.library.LibraryCountIndexError;
import se.libraryhub.global.error.library.LibraryNotFoundException;
import se.libraryhub.global.error.user.UserNotFoundException;

@RestControllerAdvice
public class LibraryControllerAdvice {

    @ExceptionHandler(LibraryNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleLibraryNotFoundException(LibraryNotFoundException notFoundException){
        return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LibraryCountIndexError.class)
    @ResponseBody
    public ResponseEntity<?> handleLibraryOutOfBoundException(LibraryCountIndexError indexException){
        return new ResponseEntity<>(indexException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
