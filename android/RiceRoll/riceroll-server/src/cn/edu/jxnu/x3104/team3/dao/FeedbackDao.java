package cn.edu.jxnu.x3104.team3.dao;

import java.util.Map;

import cn.edu.jxnu.x3104.team3.bean.Feedback;

/**
 * 反馈信息操作
 */
public interface FeedbackDao {
	/**
	 * 保存反馈信息
	 * @param feedback          
	 *            反馈信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean save(Feedback feedback);   
	/**
	 * 删除反馈信息
	 * @param feedback        
	 *            反馈信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean delete(Feedback feedback); 
	/**
	 * 修改反馈信息
	 * @param feedback          
	 *            反馈信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean update(Feedback feedback);
	/**
	 * 通过关键词查找指定反馈信息	
	 * @param feedback_keyword        
	 *            关键词
	 * @return 反馈信息类
	 */
	Feedback findByIf(String feedback_keyword);  
	/**
	 * 分页显示反馈信息
	 * @param currentPageNum          
	 *                      当前页数
	 * @param pageSize
	 *                      每页信息数
	 * @return 反馈信息类集合
	 */
	Map<String, Object> findByIf(int currentPageNum,int pageSize);  
}
