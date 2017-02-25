package cn.edu.jxnu.x3104.riceroll.bean;

/** 
 * 菜谱类
 * @author 曾超伟
 * @category
 * cookbook_id						菜谱编号<br>
 * cookbook_name					菜谱名字<br>
 * cookbook_keyword					关键字<br>
 * cookbook_brief_introduction		菜谱简介<br>
 * cookbook_photo_link				图片地址链接<br>
 * cookbook_tip						小贴士<br>
 * cookbook_step					步骤<br>
 * cookbook_food_meterial_id		材料id<br>
 * cookbook_condiment_id			调料id<br>
 * cookbook_click_times				点击次数<br>
 * cookbook_download_times			下载次数<br>
 * cookbook_collect_times			收藏次数<br>
 * cookbook_is_collected			是否被收藏<br>
 */

public class CookBook {
	/**菜谱编号**/
	private String cookbook_id;
	
	/**菜谱名字**/
	private String cookbook_name;
	
	/**关键字**/
	private String cookbook_keyword;
	
	/**菜谱简介**/
	private String cookbook_brief_introduction;
	
	/**图片地址链接**/
	private String cookbook_photo_link;
	
	/**小贴士**/
	private String cookbook_tip;
	
	/**步骤**/
	private String cookbook_step;
	
	/**材料id**/
	private String cookbook_food_material_id;
	
	/**调料id**/
	private String cookbook_condiment_id;
	
	/**点击次数**/
	private int cookbook_click_times;
	
	/**下载次数**/
	private int cookbook_download_times;
	
	/**收藏次数**/
	private int cookbook_collect_times;
	
	/**是否被收藏**/
	private boolean cookbook_is_collected;
	

	/**
	 * CookBook构造函数
	 * 
	 * @param cookbook_id  菜谱编号
	 * @param cookbook_name  菜谱名字
	 * @param cookbook_keyword  关键字
	 * @param cookbook_brief_introduction  菜谱简介
	 * @param cookbook_photo_link  图片地址链接
	 * @param cookbook_tip  小贴士
	 * @param cookbook_step  步骤
	 * @param cookbook_food_meterial_id  材料id
	 * @param cookbook_condiment_id  调料id
	 * @param cookbook_click_times  点击次数
	 * @param cookbook_download_times  下载次数
	 * @param cookbook_collect_times  收藏次数
	 * @param cookbook_is_collected  是否被收藏
	 * 
	 * */
	public CookBook(String cookbook_id, String cookbook_name,
			String cookbook_keyword, String cookbook_brief_introduction,
			String cookbook_photo_link, String cookbook_tip,
			String cookbook_step, String cookbook_food_material_id,
			String cookbook_condiment_id, int cookbook_click_times,
			int cookbook_download_times, int cookbook_collect_times,
			boolean cookbook_is_collected) {
		super();
		this.cookbook_id = cookbook_id;
		this.cookbook_name = cookbook_name;
		this.cookbook_keyword = cookbook_keyword;
		this.cookbook_brief_introduction = cookbook_brief_introduction;
		this.cookbook_photo_link = cookbook_photo_link;
		this.cookbook_tip = cookbook_tip;
		this.cookbook_step = cookbook_step;
		this.cookbook_food_material_id = cookbook_food_material_id;
		this.cookbook_condiment_id = cookbook_condiment_id;
		this.cookbook_click_times = cookbook_click_times;
		this.cookbook_download_times = cookbook_download_times;
		this.cookbook_collect_times = cookbook_collect_times;
		this.cookbook_is_collected = cookbook_is_collected;
	}
	
	//set、get方法
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
	public String getCookbook_food_meterial_id() {
		return cookbook_food_material_id;
	}
	public void setCookbook_food_meterial_id(String cookbook_food_meterial_id) {
		this.cookbook_food_material_id = cookbook_food_meterial_id;
	}
	public String getCookbook_condiment_id() {
		return cookbook_condiment_id;
	}
	public void setCookbook_condiment_id(String cookbook_condiment_id) {
		this.cookbook_condiment_id = cookbook_condiment_id;
	}
	public int getCookbook_click_times() {
		return cookbook_click_times;
	}
	public void setCookbook_click_times(int cookbook_click_times) {
		this.cookbook_click_times = cookbook_click_times;
	}
	public int getCookbook_download_times() {
		return cookbook_download_times;
	}
	public void setCookbook_download_times(int cookbook_download_times) {
		this.cookbook_download_times = cookbook_download_times;
	}
	public int getCookbook_collect_times() {
		return cookbook_collect_times;
	}
	public void setCookbook_collect_times(int cookbook_collect_times) {
		this.cookbook_collect_times = cookbook_collect_times;
	}
	public boolean isCookbook_is_collected() {
		return cookbook_is_collected;
	}
	public void setCookbook_is_collected(boolean cookbook_is_collected) {
		this.cookbook_is_collected = cookbook_is_collected;
	}	
}
