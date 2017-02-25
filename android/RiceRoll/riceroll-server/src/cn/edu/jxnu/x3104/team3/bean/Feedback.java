package cn.edu.jxnu.x3104.team3.bean;

import java.io.Serializable;
/**
 * 反馈类
 * */
public class Feedback implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 965330744654948236L;
	/**
	 * 反馈信息id
	 */
	private String feedback_id ;
	/**
	 * 反馈信息的用户id
	 */
	private String feedback_user_id ;
	/**
	 * 反馈信息内容
	 */
	private String feedback_content ;
	/**
	 * 反馈信息日期
	 */
	private String feedback_date ;
	/**
	 * 反馈信息关键词
	 */
	private String feedback_keyword ;
	/**
	 * 反馈信息的图片链接
	 */
	private String feedback_photo_link ;

	public String getFeedback_id() {
		return feedback_id;
	}
	public void setFeedback_id(String feedback_id) {
		this.feedback_id = feedback_id;
	}
	public String getFeedback_user_id() {
		return feedback_user_id;
	}
	public void setFeedback_user_id(String feedback_user_id) {
		this.feedback_user_id = feedback_user_id;
	}
	public String getFeedback_content() {
		return feedback_content;
	}
	public void setFeedback_content(String feedback_content) {
		this.feedback_content = feedback_content;
	}
	public String getFeedback_date() {
		return feedback_date;
	}
	public void setFeedback_date(String feedback_date) {
		this.feedback_date = feedback_date;
	}
	public String getFeedback_keyword() {
		return feedback_keyword;
	}
	public void setFeedback_keyword(String feedback_keyword) {
		this.feedback_keyword = feedback_keyword;
	}
	public String getFeedback_photo_link() {
		return feedback_photo_link;
	}
	public void setFeedback_photo_link(String feedback_photo_link) {
		this.feedback_photo_link = feedback_photo_link;
	}
}
