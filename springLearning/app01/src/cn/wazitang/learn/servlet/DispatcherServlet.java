package cn.wazitang.learn.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wazitang.learn.controller.Controller;
import cn.wazitang.learn.controller.UserSaveController;

/**
 * doGet、doPost最终都调用handle方法
 */
@WebServlet(name = "DispatcherServlet", urlPatterns = { "/save", "/api" })
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DispatcherServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.handle(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.handle(request, response);
	}

	private void handle(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uri = req.getRequestURI();
		System.out.println("uri:" + uri);
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex + 1);
		String dispatchUrl = null;
		Controller controller = null;
		switch (action) {
		case "save":
			controller = new UserSaveController();
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
