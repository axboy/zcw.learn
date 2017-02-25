package cn.edu.jxnu.x3104.team3.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.edu.jxnu.x3104.team3.bean.Admin;
import cn.edu.jxnu.x3104.team3.bean.Cookie;
import cn.edu.jxnu.x3104.team3.dao.AdminDao;
import cn.edu.jxnu.x3104.team3.dao.AdminDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.CookieDao;
import cn.edu.jxnu.x3104.team3.dao.CookieDaoImpl;
import cn.edu.jxnu.x3104.team3.service.CookieService;
import cn.edu.jxnu.x3104.team3.serviceImpl.CookieServiceImpl;
import cn.edu.jxnu.x3104.team3.tool.Constants;
import cn.edu.jxnu.x3104.team3.tool.CookieTool;

import com.opensymphony.xwork2.ActionSupport;

public class AdminUpdateInfoAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7425552011290236576L;

	/**
	 * 用户名
	 */
	private String admin_name;
	
	/**
	 * 邮箱地址
	 */
	private String admin_mail_address;
	
	/**
	 * 电话号码
	 */
	private String admin_phone_number;
	
	private String errorMsg;
	
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpServletRequest request=ServletActionContext.getRequest();
	CookieTool cookieTool = new CookieTool(); 
	CookieService cookieService = new CookieServiceImpl();
	AdminDao adminDao = new AdminDaoImpl();
	CookieDao cookieDao = new CookieDaoImpl();

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getAdmin_mail_address() {
		return admin_mail_address;
	}

	public void setAdmin_mail_address(String admin_mail_address) {
		this.admin_mail_address = admin_mail_address;
	}

	public String getAdmin_phone_number() {
		return admin_phone_number;
	}

	public void setAdmin_phone_number(String admin_phone_number) {
		this.admin_phone_number = admin_phone_number;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	public String UpdateInfo(){
		System.out.println(admin_phone_number);
		admin_name = StringUtils.deleteWhitespace(admin_name);
		admin_mail_address = StringUtils.deleteWhitespace(admin_mail_address);
		admin_phone_number = StringUtils.deleteWhitespace(admin_phone_number);
		String token = cookieTool.getToken(request);		
		if(StringUtils.isNotBlank(token)){
			if(cookieService.checkToken(token)){
				Cookie cookie = cookieDao.findByToken(token);
				List<Admin> admin = adminDao.findByName(cookie.getCookie_account());
				if(!admin.isEmpty()&&admin!=null){
					if(StringUtils.isNotBlank(admin_name)){
						admin.get(0).setAdmin_name(admin_name);
					}
					if(StringUtils.isNotBlank(admin_mail_address)){
						admin.get(0).setAdmin_mail_address(admin_mail_address);
					}
					if(StringUtils.isNotBlank(admin_phone_number)){
						admin.get(0).setAdmin_phone_number(admin_phone_number);
					}					
					adminDao.update(admin.get(0));
					return "success";
				}else{
					errorMsg = Integer.toString(Constants.ADMIN_LOGIN_UNKNOW_ERROR);
					return "error";
				}
			}else{
				errorMsg = Integer.toString(Constants.USER_COOKIE_ERROR);
				return "error";
			}
		}else{
			errorMsg = Integer.toString(Constants.USER_ILLEGAL_ERROR);
			return "error";
		}	
		
	}
	
}
