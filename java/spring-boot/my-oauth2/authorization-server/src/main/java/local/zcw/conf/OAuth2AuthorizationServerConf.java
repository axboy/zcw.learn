package local.zcw.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * Created by zcw on 2017/02/09.
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConf extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    //这个bean是必不可少的，不然的话就会初始化报错的。
    // FIXME: 2017/02/17 ,why this bean is must?
    @Bean
    public ApprovalStore approvalStore() throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }

    @Autowired
    //@Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;//身份认证管理者

    /**
     * 构造函数，无用
     */
    public OAuth2AuthorizationServerConf() {
        super();
    }
    
    /**
     * Configure the security of the Authorization Server, which means in practical terms the /oauth/token endpoint. The
     * /oauth/authorize endpoint also needs to be secure, but that is a normal user-facing endpoint and should be
     * secured the same way as the rest of your UI, so is not covered here. The default settings cover the most common
     * requirements, following recommendations from the OAuth2 spec, so you don't need to do anything here to get a
     * basic server up and running.
     *
     * @param security a fluent configurer for security features
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
//        security.realm("zcw/oauth2")
//                .tokenKeyAccess("isFullyAuthenticated()")
//                .checkTokenAccess("isFullyAuthenticated()");
//        security.allowFormAuthenticationForClients();
    }

    /**
     * Configure the {@link ClientDetailsService}, e.g. declaring individual clients and their properties. Note that
     * password grant is not enabled (even if some clients are allowed it) unless an {@link AuthenticationManager} is
     * supplied to the {@link #configure(AuthorizationServerEndpointsConfigurer)}. At least one client, or a fully
     * formed custom {@link ClientDetailsService} must be declared or the server will not start.
     *
     * @param clients the client details configurer
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //super.configure(clients);
        clients.inMemory().withClient("my-client")
                .resourceIds("my-rsc")
                .authorizedGrantTypes("authorization_code", "implicit", "password", "client_credentials")
                .authorities("ADMIN", "USER")
                .scopes("login")
                .accessTokenValiditySeconds(7200)
                .secret("my-secret");
                //.redirectUris("http://r.localhost:10011/o2/photo");
    }

    /**
     * Configure the non-security features of the Authorization Server endpoints, like token store, token
     * customizations, user approvals and grant types. You shouldn't need to do anything by default, unless you need
     * password grants, in which case you need to provide an {@link AuthenticationManager}.
     *
     * @param endpoints the endpoints configurer
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        //endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager);
    }
}
