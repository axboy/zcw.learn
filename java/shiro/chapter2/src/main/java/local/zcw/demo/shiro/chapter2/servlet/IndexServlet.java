package local.zcw.demo.shiro.chapter2.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2017/12/12 17:09
 */
@WebServlet(name = "IndexServlet", urlPatterns = {"/", "/index"})
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            resp.sendRedirect("/login.jsp");
            return;
        }
        req.setAttribute("user", subject.getPrincipal().toString());
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
