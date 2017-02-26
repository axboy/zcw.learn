package local.zcw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zcw on 2017/02/26.
 */
public interface Controller {

    /**
     * 处理请求
     * @param req
     * @param resp
     * @return
     */
    String handleRequest(HttpServletRequest req, HttpServletResponse resp);
}
