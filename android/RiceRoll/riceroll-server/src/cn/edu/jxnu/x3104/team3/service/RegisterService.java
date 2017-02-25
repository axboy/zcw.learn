package cn.edu.jxnu.x3104.team3.service;

import cn.edu.jxnu.x3104.team3.bean.Admin;
import cn.edu.jxnu.x3104.team3.bean.User;


public interface RegisterService {
	/**
	 * 注册服务
	 * @param user
	 *            用户类
	 * @return
	 *            操作返回码
	 */
	int Register(User user);
	
	/**
	 * 注册服务
	 * @param admin
	 *            管理员类
	 * @return
	 *            操作返回码
	 */
	int Register(Admin admin);
}
