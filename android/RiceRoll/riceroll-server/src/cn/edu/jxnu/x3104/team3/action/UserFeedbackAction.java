package cn.edu.jxnu.x3104.team3.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.edu.jxnu.x3104.team3.service.CookieService;
import cn.edu.jxnu.x3104.team3.service.FeedbackService;
import cn.edu.jxnu.x3104.team3.serviceImpl.CookieServiceImpl;
import cn.edu.jxnu.x3104.team3.serviceImpl.FeedbackServiceImpl;
import cn.edu.jxnu.x3104.team3.tool.Constants;
import cn.edu.jxnu.x3104.team3.tool.CookieTool;
import cn.edu.jxnu.x3104.team3.tool.ResponseTool;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
/**
 *用户反馈
 */
public class UserFeedbackAction extends ActionSupport implements ServletRequestAware,
ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1905598276038074024L;
	/**
	 *用户id
	 */
	private String feedback_user_id;
	/**
	 *用户反馈内容
	 */
	private String feedback_content;
	/**
	 *用户反馈关键词
	 */
	private String feedback_keyword;
	/**
	 *用户图片数组
	 */
	private List<File> pictrues;

	private JSONObject json_result = new JSONObject();

	HttpServletResponse response=ServletActionContext.getResponse();
	HttpServletRequest request=ServletActionContext.getRequest();
	CookieTool cookieTool = new CookieTool(); 
	CookieService cookieService = new CookieServiceImpl();
	FeedbackService feedbackService = new FeedbackServiceImpl();

	public void setFeedback_user_id(String feedback_user_id) {
		this.feedback_user_id = feedback_user_id;
	}

	public void setFeedback_content(String feedback_content) {
		this.feedback_content = feedback_content;
	}

	public void setFeedback_keyword(String feedback_keyword) {
		this.feedback_keyword = feedback_keyword;
	}

	public void setPictrues(List<File> pictrues) {
		this.pictrues = pictrues;
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

	public void Feedback(){
		if(!StringUtils.isNotBlank(feedback_user_id)||!StringUtils.isNotBlank(feedback_content)||
				!StringUtils.isNotBlank(feedback_keyword)){
			json_result.put("feedback_result",Constants.FEEDBACK_BLANK);	
			setJson_result(json_result);
			ResponseTool.initResponse(response,json_result);
		}else{
			feedback_content = StringUtils.deleteWhitespace(feedback_content);
			String token = cookieTool.getToken(request);		
			if(StringUtils.isNotBlank(token)){
				if(cookieService.checkToken(token)){
					if(feedbackService.saveUserFeedback(feedback_user_id,
							feedback_content, feedback_keyword,pictrues)){
						json_result.put("feedback_result",Constants.FEEDBACK_SUCCESS);			
						setJson_result(json_result);
						ResponseTool.initResponse(response,json_result);
					}else{
						json_result.put("feedback_result",Constants.FEEDBACK_FAIL);			
						setJson_result(json_result);
						ResponseTool.initResponse(response,json_result);
					}
				}else{
					json_result.put("feedback_result",Constants.USER_COOKIE_ERROR);			
					setJson_result(json_result);
					ResponseTool.initResponse(response,json_result);
				}
			}else{
				json_result.put("feedback_result",Constants.USER_ILLEGAL_ERROR);			
				setJson_result(json_result);
				ResponseTool.initResponse(response,json_result);
			}
		}
	}

}
