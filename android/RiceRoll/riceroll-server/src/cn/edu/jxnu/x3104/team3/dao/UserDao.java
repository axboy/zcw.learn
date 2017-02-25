package cn.edu.jxnu.x3104.team3.dao;

import java.util.List;
import java.util.Map;

import cn.edu.jxnu.x3104.team3.bean.User;

/**
 * 用户信息操作
 */
public interface UserDao {
	/**
	 * 保存用户信息
	 * @param user          
	 *            用户信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean save(User user);   //添加用户
	/**
	 * 删除用户信息
	 * @param user          
	 *            用户信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean delete(User user); //删除用户
	/**
	 * 修改用户信息
	 * @param user          
	 *            用户信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean update(User user);//修改用户
	/**
	 * 通过用户名查找指定用户信息
	 * @param user_name          
	 *            用户名
	 * @return 用户信息类
	 */
	List<User> findByName(String user_name);  //根据用户名查找指定用户
	/**
	 * 分页显示用户信息
	 * @param currentPageNum          
	 *                      当前页数
	 * @param pageSize
	 *                      每页人数
	 * @return 用户信息类集合
	 */
	Map<String, Object> findByName(int currentPageNum,int pageSize);  //根据页数和每页数量批量查找用户
	/**
	 * 获取用户总数
	 * @return 用户总数
	 */
	int getUserNun();
	
}
