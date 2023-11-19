package se.libraryhub.library.domain.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import se.libraryhub.hashtag.domain.Hashtag;
import se.libraryhub.library.domain.Library;
import se.libraryhub.project.domain.Project;
import se.libraryhub.project.domain.dto.ProjectContentResponseDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LibraryResponseDto {

    private Long libraryId;

    private ProjectContentResponseDto projectContentResponseDto;

    private String libraryname;

    private String version;

    private String usecase;

    private LocalDateTime createDate;

    private LocalDateTime modifiedDate;

    @Builder
    public LibraryResponseDto(Long libraryId, ProjectContentResponseDto projectContentResponseDto, String libraryname,
                              String version, String usecase,
                              LocalDateTime createDate, LocalDateTime modifiedDate) {
        this.libraryId = libraryId;
        this.projectContentResponseDto = projectContentResponseDto;
        this.libraryname = libraryname;
        this.version = version;
        this.usecase = usecase;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

    public static LibraryResponseDto of(Library library){
        return LibraryResponseDto.builder()
                .createDate(library.getCreateDate())
                .libraryId(library.getLibraryId())
                .libraryname(library.getLibraryname())
                .modifiedDate(library.getModifiedDate())
                .usecase(library.getUsecase())
                .version(library.getVersion())
                .build();
    }
}
