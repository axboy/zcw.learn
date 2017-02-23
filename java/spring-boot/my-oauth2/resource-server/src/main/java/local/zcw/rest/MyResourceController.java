package local.zcw.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

/**
 * Created by zcw on 2017/02/09.
 */
@RestController
public class MyResourceController {

    @RequestMapping("/")
    @ResponseBody
    @PermitAll
    public String index() {
        return "Hello Worid!";
    }

    /**
     * 必须授权才能请求的api
     *
     * @return
     */
    @RequestMapping("/o2/photo")
    @ResponseBody
    // OAuth2MethodSecurityExpressionHandler, OAuth2SecurityExpressionMethods, OAuth2Authentication
    //@PreAuthorize("#oauth2.hasScope('login')")      //#oauth2.clientHasRole,#oauth2.clientHasAnyRole
    /////Failed to evaluate expression '#oauth2.hasRole('ADMIN')'
    //@PreAuthorize("#oauth2.hasScope('login')")
    //@CrossOrigin                //支持跨域请求
    public String photo() {
        return "this is a photo!must login";
    }

    /**
     * 模拟无需登录的api
     *
     * @return
     */
    @RequestMapping("/o2/item")
    @ResponseBody
    @PermitAll
    public String item() {
        return "this is item info!no need login";
    }
}
