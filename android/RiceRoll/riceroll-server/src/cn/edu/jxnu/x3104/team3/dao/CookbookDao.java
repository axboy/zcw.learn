package cn.edu.jxnu.x3104.team3.dao;

import java.util.List;
import java.util.Map;

import cn.edu.jxnu.x3104.team3.bean.Cookbook;

/**
 * 菜谱信息操作
 */
public interface CookbookDao {
	/**
	 * 保存菜谱信息
	 * @param cookbook          
	 *            菜谱信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean save(Cookbook cookbook);   
	/**
	 * 删除菜谱信息
	 * @param cookbook          
	 *            菜谱信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean delete(Cookbook cookbook); 
	/**
	 * 修改菜谱信息
	 * @param cookbook          
	 *            菜谱信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean update(Cookbook cookbook);
	/**
	 * 通过菜谱id查找指定菜谱信息	
	 * @param cookbook_id          
	 *            菜谱id
	 * @return 菜谱信息类
	 */
	Cookbook findById(String cookbook_id);  
	/**
	 * 分页显示菜谱信息
	 * @param currentPageNum          
	 *                      当前页数
	 * @param pageSize
	 *                      每页菜谱数
	 * @return 菜谱信息类集合
	 */
	Map<String, Object> findByPage(int currentPageNum,int pageSize);  
	/**
	 * 通过菜谱名查找菜谱信息	
	 * @param cookbook_name          
	 *                        菜谱名
	 * @return 菜谱信息类列表
	 */
	List<Cookbook> findByName(String cookbook_name);
	/**
	 * 通过关键字查找菜谱信息	
	 * @param cookbook_keyword          
	 *                        关键字列表
	 * @return 菜谱信息类列表
	 */
	List<Cookbook> findByKeyword(String cookbook_keyword);
	/**
	 * 通过关键字查找菜谱信息	
	 * @param cookbook_keyword          
	 *                        关键字列表
	 * @return 菜谱信息类列表
	 */
	List<Cookbook> findByKeyword(List<String> cookbook_keyword);
	/**
	 * 分页获取菜谱列表
	 * @param currentPageNum          
	 *                      当前页数
	 * @param hql
	 *                      搜索方式
	 * @return 菜谱类集合
	 */
	List<Cookbook> findByPage(int currentPageNum,String hql);  
	/**
	 * 获取全部菜谱信息
	 * @return 菜谱信息类列表
	 */
	List<Cookbook> getAllCookbook();
}
