package cn.edu.jxnu.x3104.team3.dao;

import cn.edu.jxnu.x3104.team3.bean.Count;

/**
 * 统计信息操作
 */
public interface CountDao {
	/**
	 * 保存统计信息
	 * @param count        
	 *            统计信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean save(Count count);   
	/**
	 * 修改统计信息
	 * @param count        
	 *            统计信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean update(Count count);   
	/**
	 * 通过类型查找统计信息	
	 * @param count_type      
	 *            统计类型
	 * @return 统计信息类
	 */
	Count findByType(int count_type);  
}
