package se.libraryhub.library.service;

import se.libraryhub.library.domain.Library;

public interface LibraryService {

    Library findLibraryByLibraryId(Long libraryId);
}
