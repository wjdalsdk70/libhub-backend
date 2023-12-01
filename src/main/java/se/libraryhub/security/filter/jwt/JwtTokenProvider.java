package se.libraryhub.security.filter.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import se.libraryhub.security.oauth.PrincipalDetails;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.repository.UserRepository;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

//JWT Token 관리하는 메서드
@Slf4j
@Component
public class JwtTokenProvider {

    public static final String BEARER_TYPE = "Bearer ";
    public static final String TYPE_ACCESS = "access";
    public static final long ACCESS_TOKEN_EXPIRE_TIME =  7*24*60*60*1000L; // 7d

    private static String secretKey;
    private static UserRepository userRepository;

    @Autowired
    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey, UserRepository userRepository){
        JwtTokenProvider.secretKey = secretKey;
        JwtTokenProvider.userRepository = userRepository;
    }

    public static TokenInfo generateToken(Authentication authentication){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        String accessToken = generateAccessToken(principalDetails.getUser().getId());

        return TokenInfo.builder()
                .accessToken(accessToken)
                .accessTokenExpirationTime(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
                .build();
    }

    public static String generateAccessToken(Long userId){
        return BEARER_TYPE + JWT.create()
                .withSubject("jwtToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
                .withClaim("userId", userId)
                .withClaim("type",TYPE_ACCESS)
                .sign(HMAC512(secretKey));
    }

    public static Authentication getAuthentication(String accessToken){
        try{
            DecodedJWT jwt = JWT.require(HMAC512(secretKey)).build()
                    .verify(accessToken);
            Long userId = jwt.getClaim("userId").asLong();
            if (userId != 0) {
                User user = userRepository.findUserById(userId).orElse(null);
                if(user != null){
                    PrincipalDetails principalDetails = new PrincipalDetails(user);
                    return new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
                }
            }
        }
        catch(TokenExpiredException e){
            log.error("Access Token is Expired on "+e.getExpiredOn());
        }catch(SignatureVerificationException sve){
            log.error("Access Signature is invalidate");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Access Signature is invalidate",sve);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Access Token 인증에 문제가 발견했습니다");
        }
        return null;
    }
}