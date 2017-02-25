package cn.edu.jxnu.x3104.team3.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.edu.jxnu.x3104.team3.bean.User;
import cn.edu.jxnu.x3104.team3.service.RegisterService;
import cn.edu.jxnu.x3104.team3.serviceImpl.RegisterServiceImpl;
import cn.edu.jxnu.x3104.team3.tool.Constants;
import cn.edu.jxnu.x3104.team3.tool.ResponseTool;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 用户注册
 */
public class UserRegisterAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 884974522029962045L;
	/**
	 * 用户名
	 */
	private String user_name ;
	/**
	 * 用户密码
	 */
	private String user_password ;
	/**
	 * 用户手机号
	 */
	private String user_phonenumber ;
	/**
	 * 用户地址
	 */
	private String user_address ;
	/**
	 * json返回值
	 */
	private JSONObject json_result = new JSONObject();
	HttpServletResponse response=ServletActionContext.getResponse();
	RegisterService registerservice = new RegisterServiceImpl();
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public void setUser_phonenumber(String user_phonenumber) {
		this.user_phonenumber = user_phonenumber;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public void setJson_result(JSONObject json_result) {
		this.json_result = json_result;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}


	public void register() {				
		User user = new User();
		int resultCode = -1;
		user_name = StringUtils.deleteWhitespace(user_name);
		user_password = StringUtils.deleteWhitespace(user_password);
		user_phonenumber = StringUtils.deleteWhitespace(user_phonenumber);
		boolean isName = StringUtils.isNotBlank(user_name);
		boolean isPassword = StringUtils.isNotBlank(user_password);
		boolean isPhonenumber = StringUtils.isNotBlank(user_phonenumber);
		if(isName&&isPassword&&isPhonenumber){
			user.setUser_name(user_name);
			user.setUser_password(user_password);
			user.setUser_phonenumber(user_phonenumber);
			user.setUser_address(user_address);
			resultCode = registerservice.Register(user);
			if(resultCode == Constants.USER_REGISTER_SUCCESS){
				json_result.put("register_result",Constants.USER_REGISTER_SUCCESS);//成功
			}else if(resultCode == Constants.USER_REGISTER_NAME_ERROR){
				json_result.put("register_result",Constants.USER_REGISTER_NAME_ERROR);//重名
			}else{
				json_result.put("register_result",Constants.USER_REGISTER_FAIL);//失败
			}
			setJson_result(json_result);
			ResponseTool.initResponse(response,json_result);
		}else{			
			json_result.put("register_result",Constants.USER_REGISTER_FAIL);			
			setJson_result(json_result);
			ResponseTool.initResponse(response,json_result);
		}
	}
	
	

}
