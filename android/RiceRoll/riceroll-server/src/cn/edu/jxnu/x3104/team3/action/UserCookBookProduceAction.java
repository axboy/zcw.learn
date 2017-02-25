package cn.edu.jxnu.x3104.team3.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.edu.jxnu.x3104.team3.bean.Cookbook;
import cn.edu.jxnu.x3104.team3.dao.CookbookDao;
import cn.edu.jxnu.x3104.team3.dao.CookbookDaoImpl;
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
 * 通过冰箱内选定食材生成菜谱
 *
 */
public class UserCookBookProduceAction extends ActionSupport implements ServletRequestAware,
ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7362929725561702636L;


	/**
	 * 食材id串，以;分割
	 */
	private String foodMaterial_id;

	/**
	 * 食谱id
	 */
	private String cookbook_name;
	
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpServletRequest request=ServletActionContext.getRequest();
	CookieTool cookieTool = new CookieTool(); 
	CookieService cookieService = new CookieServiceImpl();
	CookBookOperateService cookbookOperateService = new CookBookOperateServiceImpl();
	CookbookDao cookbookDao = new CookbookDaoImpl();

	private JSONObject json_result = new JSONObject();

	public String getCookbook_name() {
		return cookbook_name;
	}

	public void setCookbook_name(String cookbook_name) {
		this.cookbook_name = cookbook_name;
	}

	public String getFoodMaterial_id() {
		return foodMaterial_id;
	}

	public void setFoodMaterial_id(String foodMaterial_id) {
		this.foodMaterial_id = foodMaterial_id;
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


	public void SearchCookbookByKeyword(){
		JSONArray cookbookArray = new JSONArray();
		if(!StringUtils.isNotBlank(foodMaterial_id)){
			json_result.put("produce_result",Constants.PRODUCE_COOKBOOK_KEYWORD_BLANK);			
			setJson_result(json_result);
			ResponseTool.initResponse(response,json_result);
		}else{
			String token = cookieTool.getToken(request);		
			if(StringUtils.isNotBlank(token)){
				if(cookieService.checkToken(token)){
					cookbookArray = cookbookOperateService.BuildCookbookArray(foodMaterial_id);
				//	System.out.println(cookbookArray.get(0).toString());
					if(cookbookArray!=null&&!cookbookArray.isEmpty()){
						ResponseTool.initResponse(response,cookbookArray);
					}else{
						json_result.put("produce_result",Constants.PRODUCE_COOKBOOK_FAIL);			
						setJson_result(json_result);
						ResponseTool.initResponse(response,json_result);
					}			
				}else{
					json_result.put("produce_result",Constants.USER_COOKIE_ERROR);			
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
	
	public void GetCookbookByName(){
		if(!StringUtils.isNotBlank(cookbook_name)){
			json_result.put("produce_result",Constants.PRODUCE_COOKBOOK_NAME_BLANK);			
			setJson_result(json_result);
			ResponseTool.initResponse(response,json_result);
		}else{
					cookbook_name = StringUtils.deleteWhitespace(cookbook_name);
					List<Cookbook> cookbooks = cookbookDao.findByName(cookbook_name);									
					if(!cookbooks.isEmpty()&&cookbooks!=null){
						json_result = JSONObject.parseObject(JSONObject
								.toJSONString(cookbooks.get(0)));
						setJson_result(json_result);
						ResponseTool.initResponse(response,json_result);
					}else{						
						json_result.put("produce_result",Constants.PRODUCE_COOKBOOK_FAIL);			
						setJson_result(json_result);
						ResponseTool.initResponse(response,json_result);
					}			
		}
	}
}
