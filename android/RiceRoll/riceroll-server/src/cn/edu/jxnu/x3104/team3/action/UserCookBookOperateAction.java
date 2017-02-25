package cn.edu.jxnu.x3104.team3.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.edu.jxnu.x3104.team3.service.CookBookOperateService;
import cn.edu.jxnu.x3104.team3.service.CookieService;
import cn.edu.jxnu.x3104.team3.serviceImpl.CookBookOperateServiceImpl;
import cn.edu.jxnu.x3104.team3.serviceImpl.CookieServiceImpl;
import cn.edu.jxnu.x3104.team3.tool.Constants;
import cn.edu.jxnu.x3104.team3.tool.CookieTool;
import cn.edu.jxnu.x3104.team3.tool.ResponseTool;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 根据关键词搜索菜谱操作
 *
 */
public class UserCookBookOperateAction extends ActionSupport implements ServletRequestAware,
ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7941959807226777771L;

	/**
	 * 菜谱关键词
	 */
	public String  cookbook_keyword;

	HttpServletResponse response=ServletActionContext.getResponse();
	HttpServletRequest request=ServletActionContext.getRequest();
	CookieTool cookieTool = new CookieTool(); 
	CookieService cookieService = new CookieServiceImpl();
	CookBookOperateService cookbookOperateService = new CookBookOperateServiceImpl();

	private JSONObject json_result = new JSONObject();

	public void setJson_result(JSONObject json_result) {
		this.json_result = json_result;
	}

	public void setCookbook_keyword(String cookbook_keyword) {
		this.cookbook_keyword = cookbook_keyword;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub

	}
	/**
	 * 通过关键字获取菜谱
	 */
	public void SearchCookbookByKeyword(){
		JSONArray cookbookArray = new JSONArray();
		if(!StringUtils.isNotBlank(cookbook_keyword)){
			json_result.put("search_result",Constants.SEARCH_COOKBOOK_KEYWORD_BLANK);			
			setJson_result(json_result);
			ResponseTool.initResponse(response,json_result);
		}else{
			String token = cookieTool.getToken(request);		
			if(StringUtils.isNotBlank(token)){
				if(cookieService.checkToken(token)){
					cookbookArray = cookbookOperateService.SearchCookbookByKeyword(cookbook_keyword);
				//	System.out.println(cookbookArray.get(0).toString());
					if(cookbookArray!=null&&!cookbookArray.isEmpty()){
						ResponseTool.initResponse(response,cookbookArray);
					}else{
						json_result.put("search_result",Constants.SEARCH_COOKBOOK_FAIL);			
						setJson_result(json_result);
						ResponseTool.initResponse(response,json_result);
					}			
				}else{
					json_result.put("search_result",Constants.USER_COOKIE_ERROR);			
					setJson_result(json_result);
					ResponseTool.initResponse(response,json_result);
				}
			}else{
				json_result.put("search_result",Constants.USER_ILLEGAL_ERROR);			
				setJson_result(json_result);
				ResponseTool.initResponse(response,json_result);
			}		
		}
	}
}
