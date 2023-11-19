package se.libraryhub.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import se.libraryhub.library.domain.Library;
import se.libraryhub.library.domain.dto.request.LibraryRequestDto;
import se.libraryhub.library.domain.dto.response.LibraryContentResponseDto;
import se.libraryhub.library.domain.dto.response.LibraryResponseDto;
import se.libraryhub.library.service.LibraryService;

import java.util.List;

@RequestMapping("/api/library")
@RestController
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping("/{libraryId}")
    public LibraryContentResponseDto getLibrary(@PathVariable Long libraryId) {
        return libraryService.getLibrary(libraryId);
    }

    @PostMapping
    public LibraryResponseDto addLibrary(LibraryRequestDto libraryRequestDto){
        return libraryService.addLibrary(libraryRequestDto);
    }
}
