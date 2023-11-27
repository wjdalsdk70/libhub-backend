package se.libraryhub.librarycount.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.libraryhub.librarycount.domain.LibraryCount;
import se.libraryhub.librarycount.repository.LibraryCountRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LibraryCountService {

    private final LibraryCountRepository libraryCountRepository;

    public List<LibraryCount> getTop5LibraryCountsOrderedByCount() {
        return libraryCountRepository.findTop5ByOrderByCountDesc();
    }
}
