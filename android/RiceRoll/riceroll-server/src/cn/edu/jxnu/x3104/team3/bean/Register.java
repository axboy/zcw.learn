package cn.edu.jxnu.x3104.team3.bean;

import java.io.Serializable;
/**
 * 注册信息类
 * */
public class Register implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6982939076684223952L;
	/**
	 * 注册类型
	 * */
	private int register_type;
	/**
	 * 注册日期
	 * */
	private String register_date;
	/**
	 * 注册编号
	 * */
	private int register_num;
	public int getRegister_type() {
		return register_type;
	}
	public void setRegister_type(int register_type) {
		this.register_type = register_type;
	}
	public String getRegister_date() {
		return register_date;
	}
	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}
	public int getRegister_num() {
		return register_num;
	}
	public void setRegister_num(int register_num) {
		this.register_num = register_num;
	}

}
