package local.zcw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zcw on 2017/02/26.
 */
public class TestController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        //TODO do something
        return "/test.jsp";
    }
}
