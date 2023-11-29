package se.libraryhub.security.oauth;

import java.util.Map;

public class OAuth2UserInfo {

    private final Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes){
        this.attributes = attributes;
    }

    public String getProviderId(){
        return (String) attributes.get("sub");
    }

    public String getName(){
        return (String) attributes.get("name");
    }

    public String getEmail(){
        return (String) attributes.get("email");
    }

    public String getPicture(){
        return (String) attributes.get("picture");
    }
}
