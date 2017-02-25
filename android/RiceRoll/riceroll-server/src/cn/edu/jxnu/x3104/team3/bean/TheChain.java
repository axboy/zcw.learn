package cn.edu.jxnu.x3104.team3.bean;

import java.io.Serializable;
/**
 * 外链类
 */
public class TheChain implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5395562941920841199L;
	/**
	 * 外链id
	 */
	private String the_chain_link_id ;
	/**
	 * 外链域名
	 */
	private String the_chain_main_id ;
	/**
	 * 外链地址
	 */
	private String the_chain_link ;
	/**
	 * 外链点击次数
	 */
	private int the_chain_click_time ;
	
	public String getThe_chain_link_id() {
		return the_chain_link_id;
	}
	public void setThe_chain_link_id(String the_chain_link_id) {
		this.the_chain_link_id = the_chain_link_id;
	}
	public String getThe_chain_main_id() {
		return the_chain_main_id;
	}
	public void setThe_chain_main_id(String the_chain_main_id) {
		this.the_chain_main_id = the_chain_main_id;
	}
	public String getThe_chain_link() {
		return the_chain_link;
	}
	public void setThe_chain_link(String the_chain_link) {
		this.the_chain_link = the_chain_link;
	}
	public int getThe_chain_click_time() {
		return the_chain_click_time;
	}
	public void setThe_chain_click_time(int the_chain_click_time) {
		this.the_chain_click_time = the_chain_click_time;
	}

}
