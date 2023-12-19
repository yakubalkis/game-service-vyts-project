package com.game.server.security.oauth2;

import com.game.server.security.JwtUserDetails;
import com.game.server.security.WebSecurityConfig;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
public class GitHubOAuth2UserInfoExtractor implements OAuth2UserInfoExtractor {
    @Override
    public JwtUserDetails extractUserInfo(OAuth2User oAuth2User) {
        JwtUserDetails customUserDetails = new JwtUserDetails();
        customUserDetails.setUsername(retrieveAttr("login", oAuth2User));
        customUserDetails.setEmail(retrieveAttr("email", oAuth2User));
        customUserDetails.setProvider(AuthProvider.GITHUB);
        customUserDetails.setAttributes(oAuth2User.getAttributes());
        customUserDetails.setRole(WebSecurityConfig.USER);
        return customUserDetails;
    }

    @Override
    public boolean accepts(OAuth2UserRequest userRequest) {
        return AuthProvider.GITHUB.name().equalsIgnoreCase(userRequest.getClientRegistration().getRegistrationId());
    }

    private String retrieveAttr(String attr, OAuth2User oAuth2User) {
        Object attribute = oAuth2User.getAttributes().get(attr);
        return attribute == null ? "" : attribute.toString();
    }
}
