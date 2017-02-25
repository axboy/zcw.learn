package cn.edu.jxnu.x3104.team3.dao;

import java.util.List;

import cn.edu.jxnu.x3104.team3.bean.Admin;

/**
 * 管理员操作类
 */
public interface AdminDao {
	/**
	 * 保存管理员信息
	 * @param admin          
	 *            管理员信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean save(Admin admin);   
	/**
	 * 删除管理员信息
	 * @param admin          
	 *            管理员信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean delete(Admin admin); 
	/**
	 * 修改管理员信息
	 * @param admin          
	 *           管理员信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean update(Admin admin);
	/**
	 * 通过账户名查找指定管理员信息
	 * @param user_name          
	 *            账户名
	 * @return 管理员信息类
	 */
	List<Admin> findByName(String user_name); 
	/**
	 * 分页查看管理员信息
	 * @param currentPageNum          
	 *                      当前页数
	 * @param pageSize
	 *                      每页推荐数
	 * @return 管理员类集合
	 */
	List<Admin> findByPage(int currentPageNum,int pageSize);  
}
