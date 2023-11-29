package se.libraryhub.librarycount.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LibraryCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String libraryname;

    @Column(nullable = false)
    private int count;

    @Builder
    public LibraryCount(String libraryname, int count) {
        this.libraryname = libraryname;
        this.count = count;
    }

    public void incrementCount() {
        this.count++;
    }

    public void decreaseCount(){
        this.count--;
    }
}