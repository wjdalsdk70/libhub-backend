package se.libraryhub.librarycount.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.libraryhub.librarycount.domain.LibraryCount;
import se.libraryhub.librarycount.service.LibraryCountService;

import java.util.List;

@RestController
@RequestMapping("/api/libraryCount")
@RequiredArgsConstructor
public class LibraryCountController {

    private final LibraryCountService libraryCountService;

    @GetMapping("")
    public List<LibraryCount> getTopNLibraryCount(){
        return libraryCountService.getTop5LibraryCountsOrderedByCount();
    }
}
