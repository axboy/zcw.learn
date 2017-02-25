package cn.edu.jxnu.x3104.team3.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.edu.jxnu.x3104.team3.bean.Admin;
import cn.edu.jxnu.x3104.team3.service.RegisterService;
import cn.edu.jxnu.x3104.team3.serviceImpl.RegisterServiceImpl;
import cn.edu.jxnu.x3104.team3.tool.Constants;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 管理员注册
 */
public class AdminRegisterAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3124848437417754723L;
	/**
	 * 账户名
	 */
	private String admin_name ;
	/**
	 * 管理员密码
	 */
	private String admin_password ;
	/**
	 * 管理员密码检测
	 */
	private String admin_check_password ;
	/**
	 * 管理员邮箱地址
	 */
	private String admin_mail_address ;
	/**
	 * 管理员手机号
	 */
	private String admin_phone_number ;
	/**
	 * 返回错误类型
	 */
	private int errorMsg;

	HttpServletResponse response=ServletActionContext.getResponse();
	RegisterService registerservice = new RegisterServiceImpl();

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
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

	public String getAdmin_phone_number() {
		return admin_phone_number;
	}

	public void setAdmin_phone_number(String admin_phone_number) {
		this.admin_phone_number = admin_phone_number;
	}

	public int getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(int errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public String getAdmin_check_password() {
		return admin_check_password;
	}

	public void setAdmin_check_password(String admin_check_password) {
		this.admin_check_password = admin_check_password;
	}

	public String register() {				
		Admin admin = new Admin();
		int resultCode = -1;
		admin_name = StringUtils.deleteWhitespace(admin_name);
		admin_password = StringUtils.deleteWhitespace(admin_password);
		admin_mail_address = StringUtils.deleteWhitespace(admin_mail_address);
		admin_phone_number = StringUtils.deleteWhitespace(admin_phone_number);
		boolean isName = StringUtils.isNotBlank(admin_name);
		boolean isPassword = StringUtils.isNotBlank(admin_password);
		boolean isMailAddress = StringUtils.isNotBlank(admin_mail_address);
		boolean isPhonenumber = StringUtils.isNotBlank(admin_phone_number);
		if(isName&&isPassword&&isPhonenumber&&isMailAddress){
			admin.setAdmin_name(admin_name);
			admin.setAdmin_password(admin_password);
			admin.setAdmin_phone_number(admin_phone_number);
			admin.setAdmin_mail_address(admin_mail_address);
			resultCode = registerservice.Register(admin);
			if(resultCode == Constants.ADMIN_REGISTER_SUCCESS){
				return "success";
				
			}else if(resultCode == Constants.ADMIN_REGISTER_NAME_ERROR){
				errorMsg=Constants.ADMIN_REGISTER_NAME_ERROR;//重名
				return "error";
			}else{
				errorMsg=Constants.ADMIN_REGISTER_FAIL;//失败
				return "error";
			}
		}else{			
			errorMsg=Constants.ADMIN_REGISTER_FAIL;//失败
			return "error";
		}
	}
}
