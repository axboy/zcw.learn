package cn.wazitang.learn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wazitang.learn.domain.User;
import cn.wazitang.learn.form.UserForm;

public class UserSaveController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		UserForm form = new UserForm();
		form.setName(req.getParameter("name"));
		form.setAge(req.getParameter("age"));

		User user = new User();
		user.setName(form.getName());
		try{
			user.setAge(Integer.parseInt(form.getAge()));	
		}catch(NumberFormatException e){
			e.printStackTrace();
			user.setAge(0);
		}
		//req.setAttribute("user", user);
		return "save success";
	}

}
