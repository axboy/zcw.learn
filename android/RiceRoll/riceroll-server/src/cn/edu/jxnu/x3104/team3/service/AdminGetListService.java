package cn.edu.jxnu.x3104.team3.service;

import java.util.List;

import cn.edu.jxnu.x3104.team3.bean.Condiment;
import cn.edu.jxnu.x3104.team3.bean.Cookbook;
import cn.edu.jxnu.x3104.team3.bean.FoodMaterial;

public interface AdminGetListService {
	/**
	 * 获取菜谱列表
	 * @param page
	 *            页数
	 * @return
	 *            菜谱列表
	 */
	List<Condiment> getCondiments(int page);
	
	/**
	 * 获取菜谱列表
	 * @param page
	 *            页数
	 * @return
	 *            菜谱列表
	 */
	List<FoodMaterial> getFoodMaterials(int page);
	
	/**
	 * 获取菜谱列表
	 * @param page
	 *            页数
	 * @return
	 *            菜谱列表
	 */
	List<Cookbook> getCookbooks(String page,String select,String word);
}
