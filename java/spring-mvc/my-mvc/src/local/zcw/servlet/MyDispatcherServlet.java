package local.zcw.servlet;

import local.zcw.controller.Controller;
import local.zcw.controller.TestController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zcw on 2017/02/26.
 */
@WebServlet(name = "MyDispatcherServlet", urlPatterns = {"/test", "/other"})
public class MyDispatcherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.handle(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.handle(request, response);
    }

    private void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        System.out.println("uri:" + uri);
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);
        String dispatchUrl = null;
        Controller controller = null;
        switch (action) {
            case "test":
                controller = new TestController();
                dispatchUrl = controller.handleRequest(req, resp);
                break;
            default:
                dispatchUrl = "404";
        }
        if (dispatchUrl != null) {
            // RequestDispatcher rd = req.getRequestDispatcher(dispatchUrl);
            // rd.forward(req, resp);
        }
        resp.getOutputStream().write(dispatchUrl.getBytes("UTF-8"));
        resp.setContentType("text/json; charset=UTF-8");
    }
}
