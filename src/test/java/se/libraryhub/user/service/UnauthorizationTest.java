package se.libraryhub.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.libraryhub.global.error.user.UnauthorizedAccessException;
import se.libraryhub.security.oauth.SecurityUtil;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class UnauthorizationTest {

    @Test
    public void unauthorizedUserTest(){
        assertThrows(UnauthorizedAccessException.class, SecurityUtil::getCurrentUser);
    }
}
