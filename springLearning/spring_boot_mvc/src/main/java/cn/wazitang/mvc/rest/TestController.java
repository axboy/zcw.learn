package cn.wazitang.mvc.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zcw on 2016/12/06.
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping(value = "/index", produces = "text/plain;charset=UTF-8")
    //@ResponseBody
    public String index(HttpServletRequest req) {
        return "url:" + req.getRequestURL();
    }
}
