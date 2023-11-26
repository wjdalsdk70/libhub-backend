package se.libraryhub.security.oauth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import se.libraryhub.global.error.user.UnauthorizedAccessException;
import se.libraryhub.user.domain.User;

public class SecurityUtil {

    public static User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication.getPrincipal() == null || !(authentication.getPrincipal() instanceof PrincipalDetails principalDetails)){
            throw new UnauthorizedAccessException();
        }
        return principalDetails.getUser();
    }
}
