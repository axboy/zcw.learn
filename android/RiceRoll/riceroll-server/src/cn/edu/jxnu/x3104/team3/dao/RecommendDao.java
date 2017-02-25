package cn.edu.jxnu.x3104.team3.dao;

import java.util.List;

import cn.edu.jxnu.x3104.team3.bean.Recommend;

/**
 * 每日推荐操作
 */
public interface RecommendDao {

	/**
	 * 保存每日推荐
	 * @param recommend          
	 *            每日推荐
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean save(Recommend recommend);   
	/**
	 * 删除每日推荐
	 * @param recommend          
	 *            每日推荐
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean delete(Recommend recommend); 
	/**
	 * 修改每日推荐
	 * @param recommend          
	 *            每日推荐
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean update(Recommend recommend);
	/**
	 * 通过id查找指定推荐	
	 * @param recommend_id         
	 *            推荐id
	 * @return 每日推荐类
	 */
	Recommend findById(String recommend_id);  
	/**
	 * 分页每日推荐
	 * @param currentPageNum          
	 *                      当前页数
	 * @param pageSize
	 *                      每页推荐数
	 * @return 推荐类集合
	 */
	List<Recommend> findByPage(int currentPageNum,int pageSize);  
	/**
	 * 获取所有推荐信息
	 * @return 推荐类集合
	 */
	List<Recommend> getAllRecommend();
}
