package cn.edu.jxnu.x3104.team3.service;

import java.io.File;
import java.util.List;

/**
 * 管理员添加菜谱相关数据操作服务
 */
public interface AdminAddService {
	/**
	 * 
	 * @param recommend_cookbook_name
	 *                                菜谱名
	 * @param recommend_brief_introduction
	 *                                推荐简介
	 * @param recommend_reason
	 *                                推荐理由
	 * @param pictures
	 *                                推荐图片
	 * @return
	 *                                返回码
	 */
	public int addRecommend(String recommend_cookbook_name,String recommend_brief_introduction,
			String recommend_reason,List<File> pictrues);
}
