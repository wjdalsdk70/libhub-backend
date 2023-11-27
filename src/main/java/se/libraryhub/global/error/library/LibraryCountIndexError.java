package se.libraryhub.global.error.library;

import se.libraryhub.global.errormsg.LibraryError;

public class LibraryCountIndexError extends RuntimeException{

    public LibraryCountIndexError() {
        super(LibraryError.LIBRARY_COUNT_INDEX_OUT_OF_BOUND.getMessage());
    }
}
