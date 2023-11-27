package se.libraryhub.security.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import se.libraryhub.user.domain.User;
import se.libraryhub.user.service.UserService;

import java.util.ArrayList;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        return processOAuth2User(oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2User oAuth2User){
        Map<String, Object> attributes = oAuth2User.getAttributes();
        OAuth2UserInfo oAuth2UserInfo = new OAuth2UserInfo(attributes);
        User user = saveIfNotExist(oAuth2UserInfo);
        return new PrincipalDetails(user, attributes);
    }

    private User saveIfNotExist(OAuth2UserInfo oAuth2UserInfo) {
        if (!userService.isExistingEmail(oAuth2UserInfo.getEmail())) {
            return userService.registerUser(oAuth2UserInfo.getName(), oAuth2UserInfo.getEmail(),
                    oAuth2UserInfo.getPicture(), new ArrayList<>());
        }
        return userService.findUserByEmail(oAuth2UserInfo.getEmail());
    }
}
