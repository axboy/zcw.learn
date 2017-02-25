package cn.edu.jxnu.x3104.team3.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.edu.jxnu.x3104.team3.bean.Admin;
import cn.edu.jxnu.x3104.team3.bean.Condiment;
import cn.edu.jxnu.x3104.team3.bean.Cookbook;
import cn.edu.jxnu.x3104.team3.bean.Cookie;
import cn.edu.jxnu.x3104.team3.bean.FoodMaterial;
import cn.edu.jxnu.x3104.team3.dao.AdminDao;
import cn.edu.jxnu.x3104.team3.dao.AdminDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.CookieDao;
import cn.edu.jxnu.x3104.team3.dao.CookieDaoImpl;
import cn.edu.jxnu.x3104.team3.service.AdminGetListService;
import cn.edu.jxnu.x3104.team3.service.CookieService;
import cn.edu.jxnu.x3104.team3.serviceImpl.AdminGetListServiceImpl;
import cn.edu.jxnu.x3104.team3.serviceImpl.CookieServiceImpl;
import cn.edu.jxnu.x3104.team3.tool.Constants;
import cn.edu.jxnu.x3104.team3.tool.CookieTool;

import com.opensymphony.xwork2.ActionSupport;

public class AdminGetListAction extends ActionSupport implements ServletRequestAware,
ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3588384898577361809L;
	/**
	 * 菜谱列表
	 */
	private List<Cookbook> cookbooks;
	/**
	 * 调料列表
	 */
	private List<Condiment> condiments;
	/**
	 * 食材列表
	 */
	private List<FoodMaterial> foodMaterials;
	/**
	 * 选项
	 */
	private String select;
	/**
	 * 搜索词
	 */
	private String word;
	/**
	 * 页数
	 */
	private String page;
	/**
	 * 用户名
	 */
	private String admin_name;
	private String errorMsg;


	HttpServletResponse response=ServletActionContext.getResponse();
	HttpServletRequest request=ServletActionContext.getRequest();
	AdminDao adminDao = new AdminDaoImpl();
	CookieDao cookieDao = new CookieDaoImpl();
	CookieTool cookieTool = new CookieTool(); 
	CookieService cookieService = new CookieServiceImpl();
	AdminGetListService adminGetListService = new AdminGetListServiceImpl();

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<Cookbook> getCookbooks() {
		return cookbooks;
	}

	public void setCookbooks(List<Cookbook> cookbooks) {
		this.cookbooks = cookbooks;
	}

	public List<Condiment> getCondiments() {
		return condiments;
	}

	public void setCondiments(List<Condiment> condiments) {
		this.condiments = condiments;
	}

	public List<FoodMaterial> getFoodMaterials() {
		return foodMaterials;
	}

	public void setFoodMaterials(List<FoodMaterial> foodMaterials) {
		this.foodMaterials = foodMaterials;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub

	}

	public String getCookbookList(){
		String token = cookieTool.getToken(request);	
		if(StringUtils.isNotBlank(token)){
			if(cookieService.checkToken(token)){
				Cookie cookie = cookieDao.findByToken(token);
				List<Admin> admin = adminDao.findByName(cookie.getCookie_account());
				if(!admin.isEmpty()&&admin!=null){
					admin_name = admin.get(0).getAdmin_name();
					word = StringUtils.deleteWhitespace(word);
					cookbooks = adminGetListService.getCookbooks(page, select, word);
					System.out.println(cookbooks.get(0).getCookbook_id());
					return "success";
				}else{
					errorMsg = Integer.toString(Constants.PRODUCE_COOKBOOK_FAIL);
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
