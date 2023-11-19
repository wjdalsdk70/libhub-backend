package se.libraryhub.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.libraryhub.hashtag.domain.Hashtag;
import se.libraryhub.hashtag.repository.HashtagRepository;
import se.libraryhub.hashtag.service.HashtagService;
import se.libraryhub.library.domain.Library;
import se.libraryhub.library.domain.dto.request.LibraryRequestDto;
import se.libraryhub.library.domain.dto.response.LibraryContentResponseDto;
import se.libraryhub.library.domain.dto.response.LibraryResponseDto;
import se.libraryhub.library.repository.LibraryRepository;
import se.libraryhub.project.domain.Project;
import se.libraryhub.project.repository.ProjectRepository;
import se.libraryhub.project.service.ProjectService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService{

    private final ProjectService projectService;
    private final ProjectRepository projectRepository;
    private final LibraryRepository libraryRepository;
    private final HashtagRepository hashtagRepository;
    private final HashtagService hashtagService;

    public LibraryContentResponseDto getLibrary(Long libraryId) {
        Library library = libraryRepository.findLibraryByLibraryId(libraryId).orElseThrow();
        List<String> libraryHashtags = hashtagRepository.findAllByLibrary(library).stream().map(Hashtag::getContent).toList();
        return LibraryContentResponseDto.of(library, libraryHashtags);
    }

    public LibraryResponseDto addLibrary(LibraryRequestDto libraryRequestDto){
        Project project = projectRepository.findProjectByProjectId((libraryRequestDto.getProjectId()))
                .orElseThrow();
        Library library = LibraryRequestDto.toEntity(libraryRequestDto, project);
        return LibraryResponseDto.of(libraryRepository.save(library));
    }
}
