package cn.edu.jxnu.x3104.team3.dao;

import java.util.Map;

import cn.edu.jxnu.x3104.team3.bean.TheChain;

/**
 * 外链信息操作
 */
public interface TheChainDao {
	/**
	 * 保存外链信息
	 * @param theChain          
	 *            外链信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean save(TheChain theChain);  
	/**
	 * 删除外链信息
	 * @param theChain           
	 *            外链信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean delete(TheChain theChain); 
	/**
	 * 修改外链信息
	 * @param theChain          
	 *            外链信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean update(TheChain theChain);
	/**
	 * 通过外链地址查找外链信息
	 * @param the_chain_link          
	 *            外链地址
	 * @return 外链信息类
	 */
	TheChain findByIf(String the_chain_link);  
	/**
	 * 分页显示外链信息
	 * @param currentPageNum          
	 *                      当前页数
	 * @param pageSize
	 *                      每页外链数
	 * @return 外链信息类集合
	 */
	Map<String, Object> findByIf(int currentPageNum,int pageSize);  
}
