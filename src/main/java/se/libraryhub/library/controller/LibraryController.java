package se.libraryhub.library.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import se.libraryhub.library.domain.dto.request.LibraryContentRequestDto;
import se.libraryhub.library.domain.dto.response.LibraryContentResponseDto;
import se.libraryhub.library.domain.dto.response.LibraryResponseDto;
import se.libraryhub.library.service.LibraryService;

@RequestMapping("/api/projects")
@RestController
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @Operation(summary = "라이브러리 상세 정보 조회",
            description = "프로젝트 세부 정보를 조회했을 때 반환되는 Library 정보와 동일하기 때문에 굳이 안 사용해도 무방합니다")
    @GetMapping("/libraries/{libraryId}")
    public LibraryContentResponseDto getLibrary(@PathVariable Long libraryId) {
        return libraryService.getLibrary(libraryId);
    }

    @Operation(summary = "프로젝트에 라이브러리 정보 추가")
    @PostMapping("/{projectId}/libraries")
    public LibraryContentResponseDto addLibrary(@PathVariable Long projectId, @RequestBody LibraryContentRequestDto libraryContentRequestDto){
        return libraryService.addLibrary(projectId, libraryContentRequestDto);
    }

    @Operation(summary = "라이브러리 정보 수정")
    @PatchMapping("/libraries/{libraryId}")
    public LibraryContentResponseDto updateLibrary(@PathVariable Long libraryId, @RequestBody LibraryContentRequestDto libraryContentRequestDto){
        return libraryService.updateLibrary(libraryId, libraryContentRequestDto);
    }

    @Operation(summary = "라이브러리 삭제")
    @DeleteMapping("/libraries/{libraryId}")
    public void deleteLibrary(@PathVariable Long libraryId){
        libraryService.deleteLibrary(libraryId);
    }
}
