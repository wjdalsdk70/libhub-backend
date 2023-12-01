package se.libraryhub.security.filter.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
public class TokenInfo{
    private String accessToken;
    private Date accessTokenExpirationTime;
}