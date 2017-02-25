package cn.edu.jxnu.x3104.team3.bean;

import java.io.Serializable;

/**
 * cookie持久类
 */
public class Cookie implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8846393202319186212L;
	/**
	 * 主体信息
	 */
	private String cookie_token;
	/**
	 * 用户名
	 */
	private String cookie_account; 
	/**
	 * 用户类型
	 */
	private int cookie_type;
	
	public String getCookie_token() {
		return cookie_token;
	}
	public void setCookie_token(String cookie_token) {
		this.cookie_token = cookie_token;
	}
	public String getCookie_account() {
		return cookie_account;
	}
	public void setCookie_account(String cookie_account) {
		this.cookie_account = cookie_account;
	}
	public int getCookie_type() {
		return cookie_type;
	}
	public void setCookie_type(int cookie_type) {
		this.cookie_type = cookie_type;
	} 

	
}
