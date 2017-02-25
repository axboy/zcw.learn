package cn.edu.jxnu.x3104.team3.action;

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
 * 用户点击，收藏，下载操作
 */
public class UserCCDAction extends ActionSupport implements ServletRequestAware,
ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8847417711422952173L;

	/**
	 * 操作类型:0为点击，1为收藏，2为下载
	 */
	private String type;

	/**
	 * 菜谱id
	 */
	private String cookbook_id;

	HttpServletResponse response=ServletActionContext.getResponse();
	HttpServletRequest request=ServletActionContext.getRequest();
	CookieTool cookieTool = new CookieTool(); 
	CookieService cookieService = new CookieServiceImpl();
	UserInfoService userInfoService = new UserInfoServiceImpl();
	private JSONObject json_result = new JSONObject();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCookbook_id() {
		return cookbook_id;
	}

	public void setCookbook_id(String cookbook_id) {
		this.cookbook_id = cookbook_id;
	}

	public JSONObject getJson_result() {
		return json_result;
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

	public void CCD(){
		String token = cookieTool.getToken(request);		
		if(StringUtils.isNotBlank(token)){
			if(cookieService.checkToken(token)){
				cookbook_id = StringUtils.deleteWhitespace(cookbook_id);
				if(StringUtils.isNotBlank(cookbook_id)){
					switch(type){
					case "0":{UserClick();break;}
					case "1":{UserCollection();break;}
					case "2":{UserDownload();break;}
					default:{
						json_result.put("update_result",Constants.USER_ILLEGAL_ERROR);			
						setJson_result(json_result);
						ResponseTool.initResponse(response,json_result);
					}
					}
				}else{
					json_result.put("update_result",Constants.USER_ILLEGAL_ERROR);			
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

	public void UserClick(){
		if(userInfoService.CookbookClickUpdate(cookbook_id)){
			json_result.put("update_result",Constants.USER_UPDATE_SUCCESS);			
			setJson_result(json_result);
			ResponseTool.initResponse(response,json_result);
		}else{
			json_result.put("update_result",Constants.USER_CLICK_UPDATE_FAIL);			
			setJson_result(json_result);
			ResponseTool.initResponse(response,json_result);
		}
	}

	public void UserCollection(){
		String token = cookieTool.getToken(request);
			if(userInfoService.CookbookCollectUpdate(token, cookbook_id)){
				json_result.put("update_result",Constants.USER_UPDATE_SUCCESS);			
				setJson_result(json_result);
				ResponseTool.initResponse(response,json_result);
			}else{
				json_result.put("update_result",Constants.USER_COLLECT_UPDATE_FAIL);			
				setJson_result(json_result);
				ResponseTool.initResponse(response,json_result);
			}
	}

	public void UserDownload(){
		if(userInfoService.CookbookDownloadUpdate(cookbook_id)){
			json_result.put("update_result",Constants.USER_UPDATE_SUCCESS);			
			setJson_result(json_result);
			ResponseTool.initResponse(response,json_result);
		}else{
			json_result.put("update_result",Constants.USER_DOWNLOAD_UPDATE_FAIL);			
			setJson_result(json_result);
			ResponseTool.initResponse(response,json_result);
		}
	}


}
