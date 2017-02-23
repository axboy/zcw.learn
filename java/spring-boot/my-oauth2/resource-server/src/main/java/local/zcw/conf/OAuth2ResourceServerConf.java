package local.zcw.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * Created by zcw on 2017/02/09.
 */
@Order(3)
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConf extends ResourceServerConfigurerAdapter {
    @Autowired
    private RemoteTokenServices remoteTokenServices;

    // ResourceServerTokenServicesConfiguration 有自动配置
    @Bean
    RemoteTokenServices remoteTokenServices(RestTemplateBuilder restTemplateBuilder) {
        RemoteTokenServices ts = new RemoteTokenServices();
        ts.setCheckTokenEndpointUrl("http://a.localhost:10010/oauth/check_token");
        ts.setClientId("a_my_rsc");
        ts.setClientSecret("a_my_rsc");
        // 内部默认自己新建的，为了方便跟踪调试，统一使用自己配置的。
        ts.setRestTemplate(restTemplateBuilder.build());
        return ts;
    }

    /**
     * Add resource-server specific properties (like a resource id). The defaults should work for many applications, but
     * you might want to change at least the resource id.
     *
     * @param resources configurer for the resource server
     * @throws Exception if there is a problem
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("my_rsc")
                .tokenServices(remoteTokenServices);
        //.stateless(true);     //用于标记资源，只允许基于令牌的认证，默认为true，故注释
    }

    /**
     * Use this to configure the access rules for secure resources. By default all resources <i>not</i> in "/oauth/**"
     * are protected (but no specific rules about scopes are given, for instance). You also get an
     * {@link OAuth2WebSecurityExpressionHandler} by default.
     *
     * @param http the current http filter configuration
     * @throws Exception if there is a problem
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //同注释，默认/o2/**以外的路径都被保护
        //super.configure(http);
        http.requestMatchers().antMatchers("/o2/**")
                .and()
                .authorizeRequests().antMatchers("/o2/photo").access("#oauth2.hasScope('login')")
                .and()
                .authorizeRequests().anyRequest().permitAll();
    }
}
