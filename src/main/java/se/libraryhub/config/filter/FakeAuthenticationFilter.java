package se.libraryhub.config.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import se.libraryhub.config.oauth.PrincipalDetails;
import se.libraryhub.user.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class FakeAuthenticationFilter extends OncePerRequestFilter {

    private final UserService userService;

    public FakeAuthenticationFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        PrincipalDetails principalDetails = new PrincipalDetails(userService.getUserProfile((long) 1), new HashMap<>());
        Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, principalDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
