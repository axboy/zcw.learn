package cn.edu.jxnu.x3104.team3.dao;

import cn.edu.jxnu.x3104.team3.bean.Cookie;
/**
 * 保存Cookie信息
 */
public interface CookieDao {
	/**
	 * 保存Cookie信息
	 * @param cookie          
	 *            Cookie信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean save(Cookie cookie);   
	/**
	 * 删除Cookie信息
	 * @param cookie          
	 *            Cookie信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean delete(Cookie cookie); 
	/**
	 * 修改Cookie信息
	 * @param cookie          
	 *            Cookie信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean update(Cookie cookie);
	/**
	 * 通过token查找Cookie信息
	 * @param token          
	 *            cookie信息主体
	 * @return Cookie信息类
	 */
	Cookie findByToken(String token);  
}
