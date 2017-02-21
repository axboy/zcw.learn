package local.zcw.rest;

import local.zcw.conf.MyAuthenticationProvider;
import local.zcw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Collection;
import java.util.Date;

/**
 * Created by zcw on 2017/02/09.
 * 模拟微信登录，回掉接口
 */
@RestController
public class WxController {

    @Autowired
    MyAuthenticationProvider provider;

    @Autowired
    UserService userService;

    @RequestMapping("/check")
    public ModelAndView check(Principal curUser) {
        String url = "redirect:/";              //已登录，返回首页
        if (curUser == null) {
            //未登录
            url = "redirect:/choose";
        }
        System.out.print(url);
        return new ModelAndView(url);
    }

    @RequestMapping("/wxLogin")
    //@ResponseBody
    //@PreAuthorize("permitAll")
    public ModelAndView wxLogin(String username) {
        System.out.println("===============wxLogin:" + username);
        //todo 模拟登录，并返回at
        UserDetails user = userService.loadUserByUsername(username);
        String password = user.getPassword();
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        SecurityContextHolder securityContextHolder = new SecurityContextHolder();
        securityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, password, authorities));

        String backUrl = "redirect:/oauth/authorize?response_type=code&client_id=my-client&redirect_uri=http://a.localhost:10010";
        return new ModelAndView(backUrl);
    }

    @RequestMapping("/j_spring_security_check")
    public ModelAndView login(String j_username, String j_password) {
        UserDetails user = userService.loadUserByUsername(j_username);
        if (!user.getPassword().equals(j_password)) {
            throw new BadCredentialsException("密码错误");
        }
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        SecurityContextHolder securityContextHolder = new SecurityContextHolder();
        securityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, j_password, authorities));
        String backUrl = "redirect:/oauth/authorize?response_type=code&client_id=my-client&redirect_uri=http://a.localhost:10010";
        return new ModelAndView(backUrl);
    }
}
