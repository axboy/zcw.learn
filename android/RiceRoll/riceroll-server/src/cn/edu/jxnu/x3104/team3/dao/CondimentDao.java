package cn.edu.jxnu.x3104.team3.dao;

import java.util.List;
import java.util.Map;

import cn.edu.jxnu.x3104.team3.bean.Condiment;
/**
 * 调味品信息操作
 */
public interface CondimentDao {
	/**
	 * 保存调味品信息
	 * @param condiment          
	 *            调味品信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean save(Condiment condiment);   
	/**
	 * 删除调味品信息
	 * @param condiment          
	 *            调味品信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean delete(Condiment condiment); 
	/**
	 * 修改调味品信息
	 * @param condiment          
	 *            调味品信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean update(Condiment condiment);
	/**
	 * 通过调味品名查找指定调味品信息	
	 * @param condiment_name         
	 *            调味品名
	 * @return 调味品信息类
	 */
	Condiment findByName(String condiment_name);  
	/**
	 * 分页显示调味品信息
	 * @param currentPageNum          
	 *                      当前页数
	 * @param pageSize
	 *                      每页调味品数
	 * @return 调味品信息类集合
	 */
	Map<String, Object> findByPage(int currentPageNum,int pageSize);  
	/**
	 * 获取所有调料信息
	 * @return 调料信息类集合
	 */
	List<Condiment> getAllCondiment();
}
