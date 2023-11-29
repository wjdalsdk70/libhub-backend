package se.libraryhub.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.libraryhub.library.domain.Library;
import se.libraryhub.user.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(Long id);

    List<User> searchAllByUsername(String username);

    // 사용자가 자주 사용하는 라이브러리 그룹화 및 카운트 조회
    @Query("SELECT l.libraryname, COUNT(l) " +
            "FROM Library l " +
            "INNER JOIN l.project p " +
            "INNER JOIN p.user u " +
            "WHERE u.id = :userId " +
            "GROUP BY l.libraryname " +
            "ORDER BY COUNT(l) DESC")
    List<Object[]> findFrequentlyUsedLibrariesGroupedByLibraryName(@Param("userId") Long userId);
}
