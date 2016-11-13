package cn.wazitang.learn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {

	String handleRequest(HttpServletRequest req, HttpServletResponse resp);
}
