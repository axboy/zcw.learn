package cn.edu.jxnu.x3104.team3.serviceImpl;

import java.util.List;

import cn.edu.jxnu.x3104.team3.bean.Admin;
import cn.edu.jxnu.x3104.team3.bean.Cookie;
import cn.edu.jxnu.x3104.team3.bean.User;
import cn.edu.jxnu.x3104.team3.dao.AdminDao;
import cn.edu.jxnu.x3104.team3.dao.AdminDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.CookieDao;
import cn.edu.jxnu.x3104.team3.dao.CookieDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.UserDao;
import cn.edu.jxnu.x3104.team3.dao.UserDaoImpl;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;
import cn.edu.jxnu.x3104.team3.service.CookieService;

public class CookieServiceImpl implements CookieService{
	CookieDao cookieDao = new CookieDaoImpl();
	UserDao userDao = new UserDaoImpl();
	AdminDao adminDao = new AdminDaoImpl();

	@Override
	public boolean saveCookie(String token, int user_type, String user_account) {
		Cookie cookie = new Cookie();
		cookie.setCookie_account(user_account);
		cookie.setCookie_type(user_type);
		cookie.setCookie_token(token);
		return cookieDao.save(cookie);
	}

	@Override
	public boolean checkToken(String token) {
		Cookie cookie = cookieDao.findByToken(token);
		if(cookie!=null){
			if(cookie.getCookie_type()==0){
				List<User> users = userDao.findByName(cookie.getCookie_account());
				HibernateUtil.closeSession();
				if(!users.isEmpty()&&users!=null){					
					return true;
				}else{
					return false;
				}
			}else if(cookie.getCookie_type()==1){
				List<Admin> admins = adminDao.findByName(cookie.getCookie_account());
				HibernateUtil.closeSession();
				if(!admins.isEmpty()&&admins!=null){					
					return true;
				}else{
					return false;
				}
			}
			else{
				return false;
			}
		}else{
			return false;
		}
	}

	@Override
	public boolean clearCookie(String token) {
		// TODO Auto-generated method stub
		return false;
	}



}
