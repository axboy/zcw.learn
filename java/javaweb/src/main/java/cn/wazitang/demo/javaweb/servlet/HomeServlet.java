package cn.wazitang.demo.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 作者 zcw
 * 时间 2017/7/5 18:18
 * 描述 home
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);       //fixme 不能加这行，why
        resp.setContentType("text/json; charset=UTF-8");
        resp.getOutputStream().write(req.getRequestURI().getBytes("utf-8"));
    }
}
