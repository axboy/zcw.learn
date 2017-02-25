package cn.edu.jxnu.x3104.team3.test;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4067930375324716160L;
	//Action属性
	private String helo;

	public String getHelo() {
		return helo;
	}

	public void setHelo(String helo) {
		this.helo = helo;
	}
	//重载execute()方法
	public String execute() throws Exception {
		helo="hello world";
		return SUCCESS;
	}
}
