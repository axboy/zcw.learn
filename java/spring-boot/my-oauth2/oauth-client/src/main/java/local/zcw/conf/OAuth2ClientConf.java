package local.zcw.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zcw on 2017/02/12.
 */
@Configuration
@EnableOAuth2Client
public class OAuth2ClientConf {

    @Autowired
    OAuth2RestTemplate mClientRestTemplate;

    @Bean
    public OAuth2ClientContext clientContext() {
        return mClientRestTemplate.getOAuth2ClientContext();
    }

    @Bean
    public AccessTokenProvider accessTokenProvider() {
        //todo
        return null;
    }

    @Bean
    public OAuth2ProtectedResourceDetails oAuthCodeResourceDetails() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId("my-rsc");
        details.setClientId("my-client");
        details.setClientSecret("my-secret");           //secret
        details.setAccessTokenUri("http://a.localhost:10010/oauth/token");
        details.setUserAuthorizationUri("http://a.localhost:10010/oauth/authorize");
        List list = new ArrayList<String>();
        list.add("read");
        list.add("write");
        list.add("login");
        list.add("other");
        details.setScope(list);
        return details;
    }

    @Bean
    public OAuth2RestTemplate oAuthCodeRestTemplate(OAuth2ClientContext clientContext,
                                                    AccessTokenProvider accessTokenProvider) {
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(oAuthCodeResourceDetails(), clientContext);
        restTemplate.setAccessTokenProvider(accessTokenProvider);
        return restTemplate;
    }
}
