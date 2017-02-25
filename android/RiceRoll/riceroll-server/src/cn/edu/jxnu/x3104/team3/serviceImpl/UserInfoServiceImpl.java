package cn.edu.jxnu.x3104.team3.serviceImpl;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.edu.jxnu.x3104.team3.bean.Cookbook;
import cn.edu.jxnu.x3104.team3.bean.Cookie;
import cn.edu.jxnu.x3104.team3.bean.User;
import cn.edu.jxnu.x3104.team3.dao.CookbookDao;
import cn.edu.jxnu.x3104.team3.dao.CookbookDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.CookieDao;
import cn.edu.jxnu.x3104.team3.dao.CookieDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.UserDao;
import cn.edu.jxnu.x3104.team3.dao.UserDaoImpl;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;
import cn.edu.jxnu.x3104.team3.service.UserInfoService;
import cn.edu.jxnu.x3104.team3.tool.Constants;

public class UserInfoServiceImpl implements UserInfoService{
	UserDao userDao = new UserDaoImpl();
	CookbookDao cookbookDao = new CookbookDaoImpl();
	CookieDao cookieDao = new CookieDaoImpl();

	@Override
	public boolean UserInfoUpdate(String token, String new_user_name,
			String user_password, String user_phonenumber, String user_address,
			File headImg) {
		new_user_name = StringUtils.deleteWhitespace(new_user_name);
		user_password = StringUtils.deleteWhitespace(user_password);
		user_phonenumber = StringUtils.deleteWhitespace(user_phonenumber);
		Cookie cookie = cookieDao.findByToken(token);
		List<User> users = userDao.findByName(cookie.getCookie_account());	
		if(users!=null&&!users.isEmpty()){
			User user = users.get(0);
			if(new_user_name!=null&&new_user_name!=""&&
					new_user_name!="0"&&StringUtils.isNotBlank(new_user_name)){			
				user.setUser_name(new_user_name);			
			}
			if(user_password!=null&&user_password!=""&&
					user_password!="0"&&StringUtils.isNotBlank(user_password)){				
				user.setUser_password(user_password);				
			}
			if(user_phonenumber!=null&&user_phonenumber!=""&&
					user_phonenumber!="0"&&StringUtils.isNotBlank(user_phonenumber)){				
				user.setUser_phonenumber(user_phonenumber);				
			}
			if(user_address!=null&&user_address!=""&&
					user_address!="0"&&StringUtils.isNotBlank(user_address)){
				user.setUser_address(user_address);			
			}
			if(headImg!=null){
				File temp = new File(Constants.HEADIMGPATH);
				String fileName = user.getUser_id()+"_head.png";
				try {
					File savaPictrue = new File(temp,fileName);
					FileUtils.copyFile(headImg, savaPictrue);
				} catch (Exception e) {
					System.err.println("");
					e.printStackTrace();
					HibernateUtil.closeSession();
					return false;
				}
				System.out.println(fileName);
				user.setUser_headimage(fileName);
			}
			userDao.update(user);
			HibernateUtil.closeSession();
			return true;
		}else{
			return false;
		}		
	}

	@Override
	public boolean CookbookClickUpdate(String cookbook_id) {
		Cookbook cookbook = cookbookDao.findById(cookbook_id);
		if(cookbook==null){
			HibernateUtil.closeSession();
			return false;
		}else{			
			cookbook.setCookbook_click_time(cookbook.getCookbook_click_time()+1);
			cookbookDao.update(cookbook);
			HibernateUtil.closeSession();
			return true;
		}		
	}

	@Override
	public boolean CookbookCollectUpdate(String token, String cookbook_id) {
		Cookbook cookbook = cookbookDao.findById(cookbook_id);
		Cookie cookie = cookieDao.findByToken(token);
		List<User> users = userDao.findByName(cookie.getCookie_account());		
		if(users!=null&&!users.isEmpty()){
			User user = users.get(0);
			if(cookbook==null){
				HibernateUtil.closeSession();
				return false;
			}else{			
				cookbook.setCookbook_collect_time(cookbook.getCookbook_collect_time()+1);
				cookbookDao.update(cookbook);
				if(user.getUser_collection()=="NULL"||user.getUser_collection()=="null"||
						user.getUser_collection()==null){
					user.setUser_collection(cookbook_id+";");
				}else{
					user.setUser_collection(user.getUser_collection()+cookbook_id+";");
				}				
				userDao.update(user);
				HibernateUtil.closeSession();
				return true;
			}					
		}else{
			HibernateUtil.closeSession();
			return false;
		}		
	}

	@Override
	public boolean CookbookDownloadUpdate(String cookbook_id) {
		Cookbook cookbook = cookbookDao.findById(cookbook_id);
		if(cookbook==null){
			HibernateUtil.closeSession();
			return false;
		}else{
			cookbook.setCookbook_download_time(cookbook.getCookbook_download_time()+1);
			cookbookDao.update(cookbook);
			HibernateUtil.closeSession();
			return true;
		}		
	}

	@Override
	public JSONObject GetUserInfo(String token) {
		JSONObject result = null;
		Cookie cookie = cookieDao.findByToken(token);
		List<User> users = userDao.findByName(cookie.getCookie_account());	
		if(users!=null&&!users.isEmpty()){
			User user = users.get(0);
			result = JSONObject.parseObject(JSONObject
					.toJSONString(user));
		}
		return result;
	}


}
