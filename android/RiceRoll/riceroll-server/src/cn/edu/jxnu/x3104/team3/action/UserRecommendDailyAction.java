package cn.edu.jxnu.x3104.team3.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.edu.jxnu.x3104.team3.service.CookieService;
import cn.edu.jxnu.x3104.team3.service.RecommendService;
import cn.edu.jxnu.x3104.team3.serviceImpl.CookieServiceImpl;
import cn.edu.jxnu.x3104.team3.serviceImpl.RecommendServiceImpl;
import cn.edu.jxnu.x3104.team3.tool.Constants;
import cn.edu.jxnu.x3104.team3.tool.CookieTool;
import cn.edu.jxnu.x3104.team3.tool.ResponseTool;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class UserRecommendDailyAction extends ActionSupport implements ServletRequestAware,
ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3998242919310982115L;

		private JSONObject json_result = new JSONObject();

		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest request=ServletActionContext.getRequest();
		CookieTool cookieTool = new CookieTool();
		RecommendService recommendService = new RecommendServiceImpl();
		@Override
		public void setServletResponse(HttpServletResponse arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setServletRequest(HttpServletRequest arg0) {
			// TODO Auto-generated method stub
			
		}
		
		CookieService cookieService = new CookieServiceImpl();
		public void setJson_result(JSONObject json_result) {
			this.json_result = json_result;
		}
		
		public void GetRecommendDaily(){
					JSONArray recommendArray = recommendService.RefreshRecommend();
					if(!recommendArray.isEmpty()&&recommendArray!=null){
						ResponseTool.initResponse(response,recommendArray);
					}else{
						json_result.put("recommend_result",Constants.USER_UPDATE_UNKNOW_ERROR);			
						setJson_result(json_result);
						ResponseTool.initResponse(response,json_result);
					}
			
		}
}
