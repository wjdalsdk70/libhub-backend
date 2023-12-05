package se.libraryhub.file.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class S3File {

    private String originalFileName;

    private String uploadFileName;

    private String uploadFilePath;

    private String uploadFileUrl;
}
