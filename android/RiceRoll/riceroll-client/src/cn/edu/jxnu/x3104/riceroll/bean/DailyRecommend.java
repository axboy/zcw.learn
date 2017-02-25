package cn.edu.jxnu.x3104.riceroll.bean;

public class DailyRecommend {
	/**id*/
	String id;
	
	/**菜谱名字*/
	String cookbook_name;
	
	/**简介*/
	String introduction_text;
	
	/**推荐理由*/
	String recommend_reason;
	
	/**图片地址*/
	String img_path;

	/**构造函数*/
	public DailyRecommend(String id, String cookbook_name,
			String introduction_text, String recommend_reason, String img_path) {
		super();
		this.id = id;
		this.cookbook_name = cookbook_name;
		this.introduction_text = introduction_text;
		this.recommend_reason = recommend_reason;
		this.img_path = img_path;
	}	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCookbook_name() {
		return cookbook_name;
	}

	public void setCookbook_name(String cookbook_name) {
		this.cookbook_name = cookbook_name;
	}

	public String getIntroduction_text() {
		return introduction_text;
	}

	public void setIntroduction_text(String introduction_text) {
		this.introduction_text = introduction_text;
	}

	public String getRecommend_reason() {
		return recommend_reason;
	}

	public void setRecommend_reason(String recommend_reason) {
		this.recommend_reason = recommend_reason;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

}
