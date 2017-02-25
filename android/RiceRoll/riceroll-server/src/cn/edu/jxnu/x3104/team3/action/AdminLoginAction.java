package cn.edu.jxnu.x3104.team3.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.edu.jxnu.x3104.team3.dao.CookieDao;
import cn.edu.jxnu.x3104.team3.dao.CookieDaoImpl;
import cn.edu.jxnu.x3104.team3.service.LoginService;
import cn.edu.jxnu.x3104.team3.serviceImpl.LoginServiceImpl;
import cn.edu.jxnu.x3104.team3.tool.Constants;
import cn.edu.jxnu.x3104.team3.tool.CookieTool;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 管理员登录
 */
public class AdminLoginAction extends ActionSupport implements ServletRequestAware,
ServletResponseAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1619947910594782913L;
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
	private String resultCode;
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	LoginService loginService = new LoginServiceImpl();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpSession session = request.getSession();
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
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public String login(){
		user_name = StringUtils.deleteWhitespace(user_name);
		user_password = StringUtils.deleteWhitespace(user_password);
		int type = Integer.parseInt(user_type); 		
		if (StringUtils.isNotBlank(this.user_name)
				&& StringUtils.isNotBlank(user_password)) {
			int result_code = -1;
			result_code = loginService.Login(user_name, user_password, type);
			if(result_code==Constants.ADMIN_LOGIN_SUCCESS){
				Cookie cookie = cookieTool.getCookie(user_name,type);
				if(cookie==null){
					resultCode = Integer.toString(Constants.ADMIN_LOGIN_UNKNOW_ERROR);
					return "error";
				}else{
					response.addCookie(cookie);
					// 在cookie中添加用户类型 
					Cookie ut_cookie = new Cookie("ut", "" + user_type);
					ut_cookie.setPath("/");
					ut_cookie.setMaxAge(60 * 60 * 24 * 14);// cookie保存两周
					response.addCookie(ut_cookie);
					resultCode = Integer.toString(Constants.ADMIN_LOGIN_SUCCESS);
					return "success";
				}				
			}else if(result_code==Constants.ADMIN_NAME_INEXISTENCE){
				resultCode = Integer.toString(Constants.ADMIN_NAME_INEXISTENCE);
				return "error";
			}else if(result_code==Constants.ADMIN_LOGIN_PASSWORD_ERROR){
				resultCode = Integer.toString(Constants.ADMIN_LOGIN_PASSWORD_ERROR);
				return "error";
			}else if(result_code==Constants.LOGIN_UNKNOWTYPE_ERROR){
				resultCode = Integer.toString(Constants.LOGIN_UNKNOWTYPE_ERROR);
				return "error";
			}else{
				resultCode = Integer.toString(Constants.ADMIN_LOGIN_UNKNOW_ERROR);
				return "error";
			}
		}else{
			resultCode = Integer.toString(Constants.LOGIN_UNKNOW_ERROR);
			return "error";
		}
	}

	public String logout() {
		CookieDao cookieDao = new CookieDaoImpl();
		String token = cookieTool.getToken(request);
		cn.edu.jxnu.x3104.team3.bean.Cookie cookie = cookieDao.findByToken(token);
		cookieDao.delete(cookie);
		response.addCookie(cookieTool.cleanCookie(request));
		return "success";
	}
}
