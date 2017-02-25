package cn.edu.jxnu.x3104.team3.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.edu.jxnu.x3104.team3.bean.Admin;
import cn.edu.jxnu.x3104.team3.bean.Cookie;
import cn.edu.jxnu.x3104.team3.dao.AdminDao;
import cn.edu.jxnu.x3104.team3.dao.AdminDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.CookieDao;
import cn.edu.jxnu.x3104.team3.dao.CookieDaoImpl;
import cn.edu.jxnu.x3104.team3.service.AdminGetInfoService;
import cn.edu.jxnu.x3104.team3.service.CookieService;
import cn.edu.jxnu.x3104.team3.serviceImpl.AdminGetInfoServiceImpl;
import cn.edu.jxnu.x3104.team3.serviceImpl.CookieServiceImpl;
import cn.edu.jxnu.x3104.team3.tool.Constants;
import cn.edu.jxnu.x3104.team3.tool.CookieTool;

import com.opensymphony.xwork2.ActionSupport;

public class AdminGetInfoAction extends ActionSupport implements ServletRequestAware,
ServletResponseAware{



	/**
	 * 
	 */
	private static final long serialVersionUID = -968351533910187909L;
 
	/**
	 * 用户名
	 */
	private String admin_name;

	/**
	 * 邮箱地址
	 */
	private String admin_mail_address;

	/**
	 * 电话号码
	 */
	private String admin_phone_number;

	/**
	 * 用户授权权限
	 */
	private int authority;

	/**
	 * 修改菜谱权限
	 */
	private int cookbook;

	/**
	 * 修改食材权限
	 */
	private int foodmaterial;

	/**
	 * 修改公告权限
	 */
	private int announce;

	/**
	 * 修改推荐权限
	 */
	private int recommend;

	/**
	 * 修改调料权限
	 */
	private int condiment;
	
	private int userNum;
	
	private String rank;
	
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public int getTodayUserNum() {
		return todayUserNum;
	}

	public void setTodayUserNum(int todayUserNum) {
		this.todayUserNum = todayUserNum;
	}

	public int getCookbookNum() {
		return cookbookNum;
	}

	public void setCookbookNum(int cookbookNum) {
		this.cookbookNum = cookbookNum;
	}

	public int getDownloadNum() {
		return downloadNum;
	}

	public void setDownloadNum(int downloadNum) {
		this.downloadNum = downloadNum;
	}

	private int todayUserNum;
	
	private int cookbookNum;
	
	private int downloadNum;
	
	private String errorMsg;

	HttpServletResponse response=ServletActionContext.getResponse();
	HttpServletRequest request=ServletActionContext.getRequest();
	CookieTool cookieTool = new CookieTool(); 
	CookieService cookieService = new CookieServiceImpl();
	AdminDao adminDao = new AdminDaoImpl();
	CookieDao cookieDao = new CookieDaoImpl();
	AdminGetInfoService adminGetInfoService = new AdminGetInfoServiceImpl();

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub

	} 

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getAdmin_phone_number() {
		return admin_phone_number;
	}

	public void setAdmin_phone_number(String admin_phone_number) {
		this.admin_phone_number = admin_phone_number;
	}

	public String getAdmin_mail_address() {
		return admin_mail_address;
	}

	public void setAdmin_mail_address(String admin_mail_address) {
		this.admin_mail_address = admin_mail_address;
	}

	
	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public int getCookbook() {
		return cookbook;
	}

	public void setCookbook(int cookbook) {
		this.cookbook = cookbook;
	}

	public int getFoodmaterial() {
		return foodmaterial;
	}

	public void setFoodmaterial(int foodmaterial) {
		this.foodmaterial = foodmaterial;
	}

	public int getAnnounce() {
		return announce;
	}

	public void setAnnounce(int announce) {
		this.announce = announce;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getCondiment() {
		return condiment;
	}

	public void setCondiment(int condiment) {
		this.condiment = condiment;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getUserByCookies(){
		
		
		String token = cookieTool.getToken(request);		
		if(StringUtils.isNotBlank(token)){
			if(cookieService.checkToken(token)){
				Cookie cookie = cookieDao.findByToken(token);
				List<Admin> admin = adminDao.findByName(cookie.getCookie_account());
				if(!admin.isEmpty()&&admin!=null){
					admin_name = admin.get(0).getAdmin_name();
					admin_mail_address = admin.get(0).getAdmin_mail_address();
					admin_phone_number = admin.get(0).getAdmin_phone_number();
					authority = admin.get(0).getAdmin_authorize_authority();
					cookbook = admin.get(0).getAdmin_addcookbook_authority();
					foodmaterial = admin.get(0).getAdmin_addfoodmaterial__authority();
					announce = admin.get(0).getAdmin_addannouncement__authority();
					recommend = admin.get(0).getAdmin_addrecommend__authority();
					condiment = admin.get(0).getAdmin_addcondiment__authority();
					int[] count = adminGetInfoService.getCount();
					userNum = count[0];
					todayUserNum = count[1];
					cookbookNum = count[2];
					downloadNum = count[3];
					rank = adminGetInfoService.getRank();
					return "success";
				}else{
					errorMsg = Integer.toString(Constants.ADMIN_LOGIN_UNKNOW_ERROR);
					return "error";
				}
			}else{
				errorMsg = Integer.toString(Constants.USER_COOKIE_ERROR);
				return "error";
			}
		}else{
			errorMsg = Integer.toString(Constants.USER_ILLEGAL_ERROR);
			return "error";
		}		
	}


}
