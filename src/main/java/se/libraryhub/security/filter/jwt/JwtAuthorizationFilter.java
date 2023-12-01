package se.libraryhub.security.filter.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import se.libraryhub.security.oauth.PrincipalDetails;
import se.libraryhub.user.domain.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private static final String accessHeader = "Authorization";
    AuthenticationManager authenticationManager;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String accessToken = request.getHeader(accessHeader);

        log.debug("Call JwtAuthorizationFilter | accessToken :"+accessToken);

        if(accessToken == null || accessToken.equals("null")){
            log.debug("NO ACCESS HEADER FOUND");
            filterChain.doFilter(request,response);
            return;
        }

        Authentication authentication = checkAccessToken(accessToken);
        if(authentication != null){
            log.debug("Authentication 완료 : {}",authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private Authentication checkAccessToken(String accessToken){
        accessToken = accessToken.replace(JwtTokenProvider.BEARER_TYPE, "");
        return JwtTokenProvider.getAuthentication(accessToken);
    }
}
