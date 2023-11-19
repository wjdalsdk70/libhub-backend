package se.libraryhub.hashtag.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.libraryhub.hashtag.domain.Hashtag;
import se.libraryhub.hashtag.repository.HashtagRepository;
import se.libraryhub.library.domain.Library;
import se.libraryhub.library.domain.dto.response.LibraryResponseDto;
import se.libraryhub.library.repository.LibraryRepository;
import se.libraryhub.library.service.LibraryService;
import se.libraryhub.project.domain.Project;
import se.libraryhub.project.domain.dto.ProjectContentResponseDto;
import se.libraryhub.project.repository.ProjectRepository;
import se.libraryhub.project.service.ProjectService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HashtagService {

    private final HashtagRepository hashtagRepository;
    private final ProjectRepository projectRepository;
    private final LibraryRepository libraryRepository;
    private final ProjectService projectService;
    private final LibraryService libraryService;

    public List<Hashtag> getProjectHashtags(Long projectId) {
        Project findProject = projectRepository.findProjectByProjectId(projectId).orElseThrow();
        return hashtagRepository.findAllByProject(findProject);
    }

    public List<Hashtag> getLibraryHashtags(Long libraryId){
        Library findLibrary = libraryRepository.findLibraryByLibraryId(libraryId).orElseThrow();
        return hashtagRepository.findAllByLibrary(findLibrary);
    }

    public List<Hashtag> searchHashtagsFromProject(Long projectId, String content){
        return getProjectHashtags(projectId).stream()
                .filter(hashtag -> hashtag.getContent().contains(content))
                .toList();
    }

    public List<Hashtag> searchHashtagsFromLibrary(Long libraryId, String content){
        return getLibraryHashtags(libraryId).stream()
                .filter(hashtag -> hashtag.getContent().contains(content))
                .toList();
    }
}
