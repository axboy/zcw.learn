package cn.edu.jxnu.x3104.team3.service;

import com.alibaba.fastjson.JSONArray;
/**
 * 菜谱操作服务
 */
public interface CookBookOperateService {
	/**
	 * 通过关键字获取菜谱
	 * @return
	 */
	public JSONArray SearchCookbookByKeyword(String cookbook_keyword);
	
	/**
	 * 通过食材id获取菜谱数组
	 * @return
	 */
	public JSONArray BuildCookbookArray(String foodMaterial_id);
}
