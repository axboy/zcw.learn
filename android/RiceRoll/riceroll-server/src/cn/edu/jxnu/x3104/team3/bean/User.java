package cn.edu.jxnu.x3104.team3.bean;

import java.io.Serializable;

/**
 * 用户类
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7025192602856603637L;
	/**
	 * 用户id
	 */
	private String user_id ;
	/**
	 * 用户名
	 */
	private String user_name ;
	/**
	 * 用户密码
	 */
	private String user_password ;
	/**
	 * 注册日期
	 */
	private String user_registerDate ;
	/**
	 * 用户手机号
	 */
	private String user_phonenumber ;
	/**
	 * 用户地址
	 */
	private String user_address ;
	/**
	 * 用户收藏
	 */
	private String user_collection ;
	/**
	 * 用户头像
	 */
	private String user_headimage ;
	
	public String getUser_headimage() {
		return user_headimage;
	}
	public void setUser_headimage(String user_headimage) {
		this.user_headimage = user_headimage;
	}
	public String getUser_collection() {
		return user_collection;
	}
	public void setUser_collection(String user_collection) {
		this.user_collection = user_collection;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_registerDate() {
		return user_registerDate;
	}
	public void setUser_registerDate(String user_registerDate) {
		this.user_registerDate = user_registerDate;
	}
	public String getUser_phonenumber() {
		return user_phonenumber;
	}
	public void setUser_phonenumber(String user_phonenumber) {
		this.user_phonenumber = user_phonenumber;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
}
