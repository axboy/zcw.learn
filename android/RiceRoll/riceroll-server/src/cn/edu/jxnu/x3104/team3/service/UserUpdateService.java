package cn.edu.jxnu.x3104.team3.service;

import com.alibaba.fastjson.JSONArray;
/**
 * 用户更新数据服务
 *
 */
public interface UserUpdateService {
	/**
	 * 用户更新食材表
	 * @return 食材表的json数组
	 */
	public JSONArray FoodMaterialUpdate();
	/**
	 * 用户更新调料表
	 * @return 调料表的json数组
	 */
	public JSONArray CondimentUpdate();
}
