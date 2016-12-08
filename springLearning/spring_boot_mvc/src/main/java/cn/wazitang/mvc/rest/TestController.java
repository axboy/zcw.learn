package cn.wazitang.mvc.rest;

import cn.wazitang.mvc.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zcw on 2016/12/06.
 */
@RestController         //RestController可以不写ResponseBody
@RequestMapping("/test")
public class TestController {
    @RequestMapping(value = "/", produces = "text/plain;charset=UTF-8")
    //@ResponseBody
    public String index(HttpServletRequest req) {
        return "url:" + req.getRequestURL();
    }

    //带路径参数的请求
    @RequestMapping(value = "/pathVar/{Str}")
    public String pathVar(@PathVariable String str,HttpServletRequest req){
        return "url:" + req.getRequestURL() + ",str=" + str;
    }

    @RequestMapping(value = "returnJson",produces = "application/json;charset=UTF-8")
    public User returnJson(HttpServletRequest req){
        User u = new User();
        u.setId("id1001");
        u.setName("zcw");
        return u;
    }
}
