package se.libraryhub.global.error.library;

import se.libraryhub.global.errormsg.LibraryError;

public class LibraryNotFoundException extends RuntimeException{

    public LibraryNotFoundException() {
        super(LibraryError.LIBRARY_NOT_EXIST.getMessage());
    }
}
