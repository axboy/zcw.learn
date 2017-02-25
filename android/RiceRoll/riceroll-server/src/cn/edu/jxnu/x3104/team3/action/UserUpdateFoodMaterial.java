package cn.edu.jxnu.x3104.team3.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

import cn.edu.jxnu.x3104.team3.service.CookieService;
import cn.edu.jxnu.x3104.team3.service.UserUpdateService;
import cn.edu.jxnu.x3104.team3.serviceImpl.CookieServiceImpl;
import cn.edu.jxnu.x3104.team3.serviceImpl.UserUpdateServiceImpl;
import cn.edu.jxnu.x3104.team3.tool.Constants;
import cn.edu.jxnu.x3104.team3.tool.CookieTool;
import cn.edu.jxnu.x3104.team3.tool.ResponseTool;
/**
 * 用户更新食材表
 */

public class UserUpdateFoodMaterial extends ActionSupport implements ServletRequestAware,
ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3476041029986753492L;
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpServletRequest request=ServletActionContext.getRequest();
	CookieTool cookieTool = new CookieTool(); 
	CookieService cookieService = new CookieServiceImpl();
	UserUpdateService userUpdateService = new UserUpdateServiceImpl();
	private JSONObject json_result = new JSONObject();

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

	public void UpdateFoodMaterial(){
		String token = cookieTool.getToken(request);		
		if(StringUtils.isNotBlank(token)){
			if(cookieService.checkToken(token)){
				JSONArray foodMaterialArray = userUpdateService.FoodMaterialUpdate();
				if(!foodMaterialArray.isEmpty()){
					ResponseTool.initResponse(response,foodMaterialArray);
				}else{
					json_result.put("update_result",Constants.USER_UPDATE_UNKNOW_ERROR);			
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
