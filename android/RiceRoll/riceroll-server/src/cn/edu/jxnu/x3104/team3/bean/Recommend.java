package cn.edu.jxnu.x3104.team3.bean;

import java.io.Serializable;

/**
 * 每日推荐类
 */
public class Recommend implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1632624071898290346L;
	/**
	 * 推荐id
	 */
	private String recommend_id;
	/**
	 * 菜谱名
	 */
	private String recommend_cookbook_name;
	/**
	 * 推荐简介
	 */
	private String recommend_brief_introduction;
	/**
	 * 推荐理由
	 */
	private String recommend_reason;
	/**
	 * 推荐图片链接
	 */
	private String recommend_pictures_link;
	
	public String getRecommend_id() {
		return recommend_id;
	}
	public void setRecommend_id(String recommend_id) {
		this.recommend_id = recommend_id;
	}
	public String getRecommend_cookbook_name() {
		return recommend_cookbook_name;
	}
	public void setRecommend_cookbook_name(String recommend_cookbook_name) {
		this.recommend_cookbook_name = recommend_cookbook_name;
	}
	public String getRecommend_brief_introduction() {
		return recommend_brief_introduction;
	}
	public void setRecommend_brief_introduction(String recommend_brief_introduction) {
		this.recommend_brief_introduction = recommend_brief_introduction;
	}
	public String getRecommend_reason() {
		return recommend_reason;
	}
	public void setRecommend_reason(String recommend_reason) {
		this.recommend_reason = recommend_reason;
	}
	public String getRecommend_pictures_link() {
		return recommend_pictures_link;
	}
	public void setRecommend_pictures_link(String recommend_pictures_link) {
		this.recommend_pictures_link = recommend_pictures_link;
	}
	 
	

}
