package cn.edu.jxnu.x3104.team3.dao;

import cn.edu.jxnu.x3104.team3.bean.Register;

/**
 * 注册信息操作
 */
public interface RegisterDao {
	/**
	 * 修改注册信息
	 * @param register          
	 *            注册信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean update(Register register);
	/**
	 * 通过类型查找注册信息	
	 * @param register_type       
	 *            注册类型
	 * @return 注册信息类
	 */
	Register findByType(int register_type);  
}
