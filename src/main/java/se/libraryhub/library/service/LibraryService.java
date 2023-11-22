package se.libraryhub.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.libraryhub.global.error.ProjectNotFoundException;
import se.libraryhub.hashtag.domain.Hashtag;
import se.libraryhub.hashtag.repository.HashtagRepository;
import se.libraryhub.hashtag.service.HashtagService;
import se.libraryhub.library.domain.Library;
import se.libraryhub.library.domain.dto.request.LibraryHashtagRequestDto;
import se.libraryhub.library.domain.dto.request.LibraryRequestDto;
import se.libraryhub.library.domain.dto.response.LibraryContentResponseDto;
import se.libraryhub.library.domain.dto.response.LibraryResponseDto;
import se.libraryhub.library.repository.LibraryRepository;
import se.libraryhub.project.domain.Project;
import se.libraryhub.project.domain.dto.ProjectHashtagRequestDto;
import se.libraryhub.project.repository.ProjectRepository;
import se.libraryhub.project.service.ProjectService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final ProjectRepository projectRepository;
    private final LibraryRepository libraryRepository;
    private final HashtagRepository hashtagRepository;

    public LibraryContentResponseDto getLibrary(Long libraryId) {
        Library library = libraryRepository.findLibraryByLibraryId(libraryId).orElseThrow();
        List<String> libraryHashtags = hashtagRepository.findAllByLibrary(library).stream().map(Hashtag::getContent).toList();
        return LibraryContentResponseDto.of(library, libraryHashtags);
    }

    public List<LibraryContentResponseDto> getProjectLibraries(Project project){
        List<LibraryContentResponseDto> libraryContentResponseDtos = new ArrayList<>();
        List<Library> libraries = libraryRepository.findLibrariesByProject(project);
        libraries.forEach((library -> {
            List<String> libraryHashtags = hashtagRepository.findAllByLibrary(library).stream().map(Hashtag::getContent).toList();
            libraryContentResponseDtos.add(LibraryContentResponseDto.of(library, libraryHashtags));
        }));
        return libraryContentResponseDtos;
    }

    public LibraryResponseDto addLibrary(LibraryRequestDto libraryRequestDto) {
        Project project = projectRepository.findProjectByProjectId(libraryRequestDto.getProjectId())
                .orElseThrow();
        Library library = LibraryRequestDto.toEntity(libraryRequestDto, project);
        return LibraryResponseDto.of(libraryRepository.save(library));
    }

    public void addHashtag(LibraryHashtagRequestDto libraryHashtagRequestDto) {
        Library library = libraryRepository.findLibraryByLibraryId(libraryHashtagRequestDto.getLibraryId())
                .orElseThrow();
        Hashtag hashtag = Hashtag.builder()
                .library(library)
                .content(libraryHashtagRequestDto.getContent())
                .build();
        hashtagRepository.save(hashtag);
    }
}
