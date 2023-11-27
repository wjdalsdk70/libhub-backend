package se.libraryhub.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.libraryhub.global.error.library.LibraryNotFoundException;
import se.libraryhub.global.error.project.ProjectNotFoundException;
import se.libraryhub.hashtag.domain.Hashtag;
import se.libraryhub.hashtag.repository.HashtagRepository;
import se.libraryhub.library.domain.Library;
import se.libraryhub.library.domain.dto.request.LibraryContentRequestDto;
import se.libraryhub.library.domain.dto.response.LibraryContentResponseDto;
import se.libraryhub.library.domain.dto.response.LibraryResponseDto;
import se.libraryhub.library.repository.LibraryRepository;
import se.libraryhub.librarycount.domain.LibraryCount;
import se.libraryhub.librarycount.repository.LibraryCountRepository;
import se.libraryhub.project.domain.Project;
import se.libraryhub.project.domain.dto.response.ProjectContentResponseDto;
import se.libraryhub.project.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LibraryService {

    private final ProjectRepository projectRepository;
    private final LibraryRepository libraryRepository;
    private final HashtagRepository hashtagRepository;
    private final LibraryCountRepository libraryCountRepository;

    public LibraryContentResponseDto getLibrary(Long libraryId) {
        Library library = libraryRepository.findLibraryByLibraryId(libraryId).orElseThrow(LibraryNotFoundException::new);
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

    public LibraryContentResponseDto addLibrary(Long projectId, LibraryContentRequestDto libraryContentRequestDto) {
        Project project = projectRepository.findProjectByProjectId(projectId)
                .orElseThrow(ProjectNotFoundException::new);
        Library library = LibraryContentRequestDto.toEntity(libraryContentRequestDto, project);
        Library savedLibrary = libraryRepository.save(library);

        List<String> libraryHashtags = libraryContentRequestDto.getLibraryHashtags();
        libraryHashtags.forEach((hashtag) -> {
            hashtagRepository.save(Hashtag.libraryHashtag(savedLibrary, hashtag));
        });

        LibraryCount libraryCount = libraryCountRepository.findByLibraryname(libraryContentRequestDto.getLibraryname())
                .orElse(null);
        if(libraryCount != null){
            libraryCount.incrementCount();
            libraryCountRepository.save(libraryCount);
        }else{
            libraryCountRepository.save(LibraryCount.builder()
                            .libraryname(libraryContentRequestDto.getLibraryname())
                            .count(1)
                            .build());
        }

        return getLibrary(savedLibrary.getLibraryId());
    }

    public LibraryContentResponseDto updateLibrary(Long libraryId, LibraryContentRequestDto libraryContentRequestDto){
        Library findLibrary = libraryRepository.findLibraryByLibraryId(libraryId)
                .orElseThrow(LibraryNotFoundException::new);
        List<Hashtag> findAllLibraryHashtags = hashtagRepository.findAllByLibrary(findLibrary);
        hashtagRepository.deleteAll(findAllLibraryHashtags);

        findLibrary.updateLibrary(libraryContentRequestDto);
        Library updatedLibrary = libraryRepository.save(findLibrary);
        List<String> libraryHashtags = libraryContentRequestDto.getLibraryHashtags();
        libraryHashtags.forEach((hashtag) -> {
            hashtagRepository.save(Hashtag.libraryHashtag(updatedLibrary, hashtag));
        });
        return LibraryContentResponseDto.of(libraryRepository.save(findLibrary), libraryHashtags);
    }

    public void deleteLibrary(Long libraryId){
        Library findLibrary = libraryRepository.findLibraryByLibraryId(libraryId)
                .orElseThrow(LibraryNotFoundException::new);
        List<Hashtag> hashtags = hashtagRepository.findAllByLibrary(findLibrary);

        LibraryCount libraryCount = libraryCountRepository.findByLibraryname(findLibrary.getLibraryname())
                .orElse(null);
        if(libraryCount != null){
            libraryCount.decreaseCount();
            libraryCountRepository.save(libraryCount);
        }

        libraryRepository.delete(findLibrary);
    }
}
