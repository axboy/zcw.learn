package cn.edu.jxnu.x3104.team3.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.edu.jxnu.x3104.team3.service.LoginService;
import cn.edu.jxnu.x3104.team3.serviceImpl.LoginServiceImpl;
import cn.edu.jxnu.x3104.team3.tool.Constants;
import cn.edu.jxnu.x3104.team3.tool.CookieTool;
import cn.edu.jxnu.x3104.team3.tool.ResponseTool;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 用户登录
 */
public class UserLoginAction extends ActionSupport implements ServletRequestAware,
ServletResponseAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7466781096012479786L;
	/**
	 * 用户名
	 */
	private String user_name ;
	/**
	 * 用户密码
	 */
	private String user_password ;
	/**
	 * 用户类型
	 */
	private String user_type ;
	/**
	 * json返回值
	 */
	private JSONObject login_result = new JSONObject();
	LoginService loginService = new LoginServiceImpl();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpServletRequest request=ServletActionContext.getRequest();
	CookieTool cookieTool = new CookieTool(); 

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	public void setLogin_result(JSONObject json_result) {
		this.login_result = json_result;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void login(){
		int type = Integer.parseInt(user_type); 
		if (StringUtils.isNotBlank(this.user_name)
				&& StringUtils.isNotBlank(user_password)) {
			int result_code = -1;
			result_code = loginService.Login(user_name, user_password, type);
			if(result_code==Constants.USER_LOGIN_SUCCESS){
				Cookie cookie = cookieTool.getCookie(user_name,type);
				if(cookie==null){
					login_result.put("login_result",Constants.LOGIN_UNKNOW_ERROR);			
					setLogin_result(login_result);
					ResponseTool.initResponse(response,login_result);
				}else{
					response.addCookie(cookie);
					// 在cookie中添加用户类型 
					Cookie ut_cookie = new Cookie("ut", "" + user_type);
					ut_cookie.setPath("/");
					ut_cookie.setMaxAge(60 * 60 * 24 * 14);// cookie保存两周
					response.addCookie(ut_cookie);
					login_result.put("login_result",Constants.USER_LOGIN_SUCCESS);			
					setLogin_result(login_result);
					ResponseTool.initResponse(response,login_result);
				}				
			}else if(result_code==Constants.USER_LOGIN_NAME_INEXISTENCE){
				login_result.put("login_result",Constants.USER_LOGIN_NAME_INEXISTENCE);			
				setLogin_result(login_result);
				ResponseTool.initResponse(response,login_result);
			}else if(result_code==Constants.USER_LOGIN_PASSWORD_ERROR){
				login_result.put("login_result",Constants.USER_LOGIN_PASSWORD_ERROR);			
				setLogin_result(login_result);
				ResponseTool.initResponse(response,login_result);
			}else if(result_code==Constants.LOGIN_UNKNOWTYPE_ERROR){
				login_result.put("login_result",Constants.LOGIN_UNKNOWTYPE_ERROR);			
				setLogin_result(login_result);
				ResponseTool.initResponse(response,login_result);
			}else{
				login_result.put("login_result",Constants.LOGIN_UNKNOW_ERROR);			
				setLogin_result(login_result);
				ResponseTool.initResponse(response,login_result);
			}
		}else{
			login_result.put("login_result",Constants.LOGIN_UNKNOW_ERROR);			
			setLogin_result(login_result);
			ResponseTool.initResponse(response,login_result);
		}
	}

}
