package local.zcw.conf;

import local.zcw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by zcw on 2/8/17.
 * Spring Security配置
 */
//@Order(2)           //控制配置类的加载顺序，越小越先加载
@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class WebSecurityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAuthenticationProvider provider;

    @Autowired
    UserService userService;

//    @Autowired
//    AuthenticationManager authenticationManager;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
//        auth.inMemoryAuthentication()
//                .withUser("a_admin").password("a_admin").roles("ADMIN")
//                .and()
//                .withUser("a_user").password("a_user").roles("USER");
        auth.authenticationProvider(provider);
        //auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);
        // 只对以下路径规则应用该安全设置。
//        http.requestMatchers()
//                //.antMatchers("/","/login").permitAll()
//                .antMatchers("/", "/oauth/token", "/wxLogin", "/check", "/virtual_wx", "/choose", "/login", "/j_spring_security_check")
//                .antMatchers("/wx")
//                .antMatchers("/sec")
//                .antMatchers("/oauth/authorize")
//                .antMatchers("/oauth/confirm_access")
//
//                .and()
//                .formLogin()                     //通过fromLogin方法定制登录操作
//                .loginPage("/check")             //定制登录页面
//                .defaultSuccessUrl("/oauth/authorize?response_type=code&client_id=my-client&redirect_uri=http://a.localhost:10010")          //登录成功跳转页面
//                .failureUrl("/login?error")       //登录失败后的跳转页面
//                .permitAll()
//
//                .and()
//                .rememberMe()                    //开启cookie存储用户信息
//                .tokenValiditySeconds(3600)      //指定cookie有效期
//                .key("authKey")                  //cookie中的私钥
//
//                .and()
//                .logout()                       //定制注销行为
//                .logoutUrl("/logout")           //指定注销的url
//                .logoutSuccessUrl("/")          //注销成功后的跳转页面
//                .permitAll()
//
//                .and()
//                .authorizeRequests()
//                .antMatchers("/", "/oauth/token", "/wxLogin", "/check", "/virtual_wx", "/choose", "/login", "/j_spring_security_check")
//                .permitAll()            //对根路径不拦截
//
//                .anyRequest()
//                .authenticated()        //其它路径都必须登录
//
//                .and()
//                .httpBasic()
//                .realmName("My OAuth2 Auth Server")
//
//                .and()
//                .csrf() // 仅仅测试用
//                .ignoringAntMatchers("/login")
//                .ignoringAntMatchers("/oauth/confirm_access")
//                .ignoringAntMatchers("/oauth/authorize");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        //忽略静态资源的拦截
        //web.ignoring().antMatchers("resources/static/**");
    }
}
