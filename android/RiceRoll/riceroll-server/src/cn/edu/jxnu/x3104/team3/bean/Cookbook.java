package cn.edu.jxnu.x3104.team3.bean;

import java.io.Serializable;

/**
 * 菜谱类
 * */
public class Cookbook implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2907579406764164163L;
	/**
	 * 菜谱id
	 */
	private String cookbook_id ;
	/**
	 * 菜谱名称
	 */
	private String cookbook_name ;
	/**
	 * 菜谱关键字
	 */
	private String cookbook_keyword ;
	/**
	 * 菜谱简介
	 */
	private String cookbook_brief_introduction ;
	/**
	 * 菜谱图片链接
	 */
	private String cookbook_photo_link ;
	/**
	 * 菜谱小贴士
	 */
	private String cookbook_tip ;
	/**
	 * 菜谱步骤
	 */
	private String cookbook_step ;
	/**
	 * 菜谱食材id表
	 */
	private String cookbook_food_material_id ;
	/**
	 * 菜谱调味料id表
	 */
	private String cookbook_condiment_id ;
	/**
	 * 菜谱点击次数
	 */
	private int cookbook_click_time ;
	/**
	 * 菜谱下载次数
	 */
	private int cookbook_download_time ;
	/**
	 * 菜谱收藏次数
	 */
	private int cookbook_collect_time ;
	public String getCookbook_id() {
		return cookbook_id;
	}
	public void setCookbook_id(String cookbook_id) {
		this.cookbook_id = cookbook_id;
	}
	public String getCookbook_name() {
		return cookbook_name;
	}
	public void setCookbook_name(String cookbook_name) {
		this.cookbook_name = cookbook_name;
	}
	public String getCookbook_keyword() {
		return cookbook_keyword;
	}
	public void setCookbook_keyword(String cookbook_keyword) {
		this.cookbook_keyword = cookbook_keyword;
	}
	public String getCookbook_brief_introduction() {
		return cookbook_brief_introduction;
	}
	public void setCookbook_brief_introduction(String cookbook_brief_introduction) {
		this.cookbook_brief_introduction = cookbook_brief_introduction;
	}
	public String getCookbook_photo_link() {
		return cookbook_photo_link;
	}
	public void setCookbook_photo_link(String cookbook_photo_link) {
		this.cookbook_photo_link = cookbook_photo_link;
	}
	public String getCookbook_tip() {
		return cookbook_tip;
	}
	public void setCookbook_tip(String cookbook_tip) {
		this.cookbook_tip = cookbook_tip;
	}
	public String getCookbook_step() {
		return cookbook_step;
	}
	public void setCookbook_step(String cookbook_step) {
		this.cookbook_step = cookbook_step;
	}
	public String getCookbook_food_material_id() {
		return cookbook_food_material_id;
	}
	public void setCookbook_food_material_id(String cookbook_food_material_id) {
		this.cookbook_food_material_id = cookbook_food_material_id;
	}
	public String getCookbook_condiment_id() {
		return cookbook_condiment_id;
	}
	public void setCookbook_condiment_id(String cookbook_condiment_id) {
		this.cookbook_condiment_id = cookbook_condiment_id;
	}
	public int getCookbook_click_time() {
		return cookbook_click_time;
	}
	public void setCookbook_click_time(int cookbook_click_time) {
		this.cookbook_click_time = cookbook_click_time;
	}
	public int getCookbook_download_time() {
		return cookbook_download_time;
	}
	public void setCookbook_download_time(int cookbook_download_time) {
		this.cookbook_download_time = cookbook_download_time;
	}
	public int getCookbook_collect_time() {
		return cookbook_collect_time;
	}
	public void setCookbook_collect_time(int cookbook_collect_time) {
		this.cookbook_collect_time = cookbook_collect_time;
	}
}
