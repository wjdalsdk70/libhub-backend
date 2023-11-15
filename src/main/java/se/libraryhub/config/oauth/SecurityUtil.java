package se.libraryhub.config.oauth;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import se.libraryhub.user.domain.User;

public class SecurityUtil {

    public static User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
            throw new IllegalStateException("Cannot find the current user. Please check the security settings.");
        }
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        return principalDetails.getUser();
    }
}
