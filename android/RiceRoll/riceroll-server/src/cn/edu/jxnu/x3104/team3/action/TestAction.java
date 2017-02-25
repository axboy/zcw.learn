/*package cn.edu.jxnu.x3104.team3.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.edu.jxnu.x3104.team3.test.StolenData;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport implements ServletRequestAware,
ServletResponseAware{

	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 7411873000350417434L;
	*//**
	 * 网址
	 *//*
	private String link;
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String addLink(){
		String name=null;
		try {
			name = new String(link.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		@SuppressWarnings("unused")	
		StolenData test = new StolenData(name);	
		return "success";
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
*/