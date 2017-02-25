package cn.edu.jxnu.x3104.team3.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.edu.jxnu.x3104.team3.service.CookieService;
import cn.edu.jxnu.x3104.team3.service.UserInfoService;
import cn.edu.jxnu.x3104.team3.serviceImpl.CookieServiceImpl;
import cn.edu.jxnu.x3104.team3.serviceImpl.UserInfoServiceImpl;
import cn.edu.jxnu.x3104.team3.tool.Constants;
import cn.edu.jxnu.x3104.team3.tool.CookieTool;
import cn.edu.jxnu.x3104.team3.tool.ResponseTool;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 用户更新个人信息
 */
public class UserInfoOperateAction extends ActionSupport implements ServletRequestAware,
ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7210276637851027292L;
	/**
	 * 新用户名
	 */
	private String new_user_name ;
	/**
	 * 新用户密码
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
	 * 用户头像
	 */
	private File user_headimage;
	private String user_headimageContentType;
	private String user_headimageFileName;

	public String getUser_headimageContentType() {
		return user_headimageContentType;
	}

	public void setUser_headimageContentType(String user_headimageContentType) {
		this.user_headimageContentType = user_headimageContentType;
	}

	public String getUser_headimageFileName() {
		return user_headimageFileName;
	}

	public void setUser_headimageFileName(String user_headimageFileName) {
		this.user_headimageFileName = user_headimageFileName;
	}


	HttpServletResponse response=ServletActionContext.getResponse();
	HttpServletRequest request=ServletActionContext.getRequest();
	CookieTool cookieTool = new CookieTool(); 
	CookieService cookieService = new CookieServiceImpl();
	UserInfoService userInfoService = new UserInfoServiceImpl();
	private JSONObject json_result = new JSONObject();


	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub

	}

	public void setNew_user_name(String new_user_name) {
		this.new_user_name = new_user_name;
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

	public void setHeadImg(File user_headimage) {
		this.user_headimage = user_headimage;
	}

	public void setJson_result(JSONObject json_result) {
		this.json_result = json_result;
	}

	public File getUser_headimage() {
		return user_headimage;
	}

	public void setUser_headimage(File user_headimage) {
		this.user_headimage = user_headimage;
	}

	public String getNew_user_name() {
		return new_user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public String getUser_phonenumber() {
		return user_phonenumber;
	}

	public String getUser_address() {
		return user_address;
	}

	public JSONObject getJson_result() {
		return json_result;
	}
	
	public void UserInfoUpdate(){
		String token = cookieTool.getToken(request);		
		if(StringUtils.isNotBlank(token)){
			if(cookieService.checkToken(token)){
				if(userInfoService.UserInfoUpdate(token, new_user_name, 
						user_password, user_phonenumber, user_address, user_headimage)){
					json_result.put("update_result",Constants.USERINFO_UPDATE_SUCCESS);			
					setJson_result(json_result);
					ResponseTool.initResponse(response,json_result);
				}else{
					json_result.put("update_result",Constants.USERINFO_UPDATE_FAIL);			
					setJson_result(json_result);
					ResponseTool.initResponse(response,json_result);
				}
			}else{
				json_result.put("update_result",Constants.USER_COOKIE_ERROR);			
				setJson_result(json_result);
				ResponseTool.initResponse(response,json_result);
			}
		}else{
			json_result.put("update_result",Constants.USER_ILLEGAL_ERROR);			
			setJson_result(json_result);
			ResponseTool.initResponse(response,json_result);
		}
	}


	public void GetUserInfo(){
		String token = cookieTool.getToken(request);		
		if(StringUtils.isNotBlank(token)){
			if(cookieService.checkToken(token)){
				json_result = userInfoService.GetUserInfo(token);
				if(!json_result.isEmpty()&&json_result!=null){
					setJson_result(json_result);
					ResponseTool.initResponse(response,json_result);
				}else{
					json_result.put("update_result",Constants.USERINFO_GET_FAIL);			
					setJson_result(json_result);
					ResponseTool.initResponse(response,json_result);
				}				
			}else{
				json_result.put("update_result",Constants.USER_COOKIE_ERROR);			
				setJson_result(json_result);
				ResponseTool.initResponse(response,json_result);
			}
		}else{
			json_result.put("update_result",Constants.USER_ILLEGAL_ERROR);			
			setJson_result(json_result);
			ResponseTool.initResponse(response,json_result);
		}
	}

}
