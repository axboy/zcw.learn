package cn.edu.jxnu.x3104.team3.bean;

import java.io.Serializable;

/**
 * 管理员类
 */
public class Admin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6994099136830219880L;

	/**
	 * 管理员id
	 */
	private String admin_id;
	
	/**
	 * 管理员账户名
	 */
	private String admin_name;
	
	/**
	 * 管理员密码
	 */
	private String admin_password;

	/**
	 * 管理员邮箱地址
	 */
	private String admin_mail_address;
	
	/**
	 * 管理员电话号码
	 */
	private String admin_phone_number;

	/**
	 * 管理员授权权限
	 */
	private int admin_authorize_authority;
	
	/**
	 * 管理员添加菜谱权限
	 */
	private int admin_addcookbook_authority;

	/**
	 * 管理员添加食材权限
	 */
	private int admin_addfoodmaterial__authority;
	
	/**
	 * 管理员添加推荐权限
	 */
	private int admin_addrecommend__authority;
	
	/**
	 * 管理员添加公告权限
	 */
	private int admin_addannouncement__authority;
	
	/**
	 * 管理员添加调料权限
	 */
	private int admin_addcondiment__authority;

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}

	public String getAdmin_mail_address() {
		return admin_mail_address;
	}

	public void setAdmin_mail_address(String admin_mail_address) {
		this.admin_mail_address = admin_mail_address;
	}

	public int getAdmin_authorize_authority() {
		return admin_authorize_authority;
	}

	public void setAdmin_authorize_authority(int admin_authorize_authority) {
		this.admin_authorize_authority = admin_authorize_authority;
	}

	public int getAdmin_addcookbook_authority() {
		return admin_addcookbook_authority;
	}

	public void setAdmin_addcookbook_authority(int admin_addcookbook_authority) {
		this.admin_addcookbook_authority = admin_addcookbook_authority;
	}

	public int getAdmin_addfoodmaterial__authority() {
		return admin_addfoodmaterial__authority;
	}

	public void setAdmin_addfoodmaterial__authority(
			int admin_addfoodmaterial__authority) {
		this.admin_addfoodmaterial__authority = admin_addfoodmaterial__authority;
	}

	public int getAdmin_addrecommend__authority() {
		return admin_addrecommend__authority;
	}

	public void setAdmin_addrecommend__authority(int admin_addrecommend__authority) {
		this.admin_addrecommend__authority = admin_addrecommend__authority;
	}

	public int getAdmin_addannouncement__authority() {
		return admin_addannouncement__authority;
	}

	public void setAdmin_addannouncement__authority(
			int admin_addannouncement__authority) {
		this.admin_addannouncement__authority = admin_addannouncement__authority;
	}

	public int getAdmin_addcondiment__authority() {
		return admin_addcondiment__authority;
	}

	public void setAdmin_addcondiment__authority(int admin_addcondiment__authority) {
		this.admin_addcondiment__authority = admin_addcondiment__authority;
	}

	public String getAdmin_phone_number() {
		return admin_phone_number;
	}

	public void setAdmin_phone_number(String admin_phone_number) {
		this.admin_phone_number = admin_phone_number;
	}

	
	
}
