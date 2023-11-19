package se.libraryhub.config.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class SecurityUtil {

    public static User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
            throw new IllegalStateException("Cannot find the current user. Please check the security settings.");
        }
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        return principalDetails.getUser();
    }

    public static String getAccessToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
            throw new IllegalStateException("Cannot find the current user. Please check the security settings.");
        }
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        return principalDetails.getAttribute("access_token");
    }
}
