package cn.edu.jxnu.x3104.team3.service;

import com.alibaba.fastjson.JSONArray;

/**
 * 每日推荐服务
 */
public interface RecommendService {
	/**
	 * 刷新每日推荐
	 * @return
	 */
	public JSONArray RefreshRecommend();
}
