package cn.edu.jxnu.x3104.team3.dao;

import java.util.List;
import java.util.Map;

import cn.edu.jxnu.x3104.team3.bean.FoodMaterial;

/**
 * 食材信息操作
 */
public interface FoodMaterialDao {
	/**
	 * 保存食材信息
	 * @param foodMaterial          
	 *            食材信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean save(FoodMaterial foodMaterial);   
	/**
	 * 删除食材信息
	 * @param foodMaterial          
	 *            食材信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean delete(FoodMaterial foodMaterial); 
	/**
	 * 修改用户信息
	 * @param foodMaterial          
	 *            食材信息
	 * @return 保存是否成功 true 成功 false 失败
	 */
	boolean update(FoodMaterial foodMaterial);
	/**
	 * 通过食材名查找指定食材信息	
	 * @param food_material_name          
	 *            食材名
	 * @return 食材信息类
	 */
	FoodMaterial findByName(String food_material_name);  
	/**
	 * 分页显示食材信息
	 * @param currentPageNum          
	 *                      当前页数
	 * @param pageSize
	 *                      每页食材数
	 * @return 食材信息类集合
	 */
	Map<String, Object> findByPage(int currentPageNum,int pageSize);  
	/**
	 * 获取所有食材信息
	 * @return 食材信息类集合
	 */
	List<FoodMaterial> getAllFoodMaterial();  
}
