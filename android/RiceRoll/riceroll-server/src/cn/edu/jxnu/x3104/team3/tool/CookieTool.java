package cn.edu.jxnu.x3104.team3.tool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import cn.edu.jxnu.x3104.team3.db.HibernateUtil;
import cn.edu.jxnu.x3104.team3.service.CookieService;
import cn.edu.jxnu.x3104.team3.serviceImpl.CookieServiceImpl;


/**
 * cookie相关操作
 */
public class CookieTool {
	/**
	 * cookie键名
	 */
	private static final String COOK_NAME = "user_token";
	/**
	 * cookie服务
	 */
	private CookieService cookieService = new CookieServiceImpl();
	/**
	 * DES加密解密工具
	 */
	private static DesEncrypt desEncrypt = new DesEncrypt("JavaWeb");
	/**
	 * 
	 * @param user_account
	 *                    用户账户名
	 * @param user_type
	 *                    用户类型
	 * @return            
	 *                    cookie
	 */
	public Cookie getCookie(String user_account,int user_type) {
		boolean isOk;
		String token = null;
		token = desEncrypt.getEncString(user_account + user_type + System.currentTimeMillis());
		Cookie cookie = new Cookie(COOK_NAME, token);
		//cookie共享
		cookie.setPath("/");
		// cookie保存两周
		cookie.setMaxAge(60 * 60 * 24 * 14);
		/* 保存token */
		isOk = cookieService.saveCookie(token, user_type, user_account);
		HibernateUtil.closeSession();
		if(isOk)
			return cookie;
		else
			return null;
	}

	/**
	 * 
	 * @param request
	 *               请求
	 * @return
	 *               token
	 */
	public String getToken(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String token = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (COOK_NAME.equals(cookie.getName())) {
					token = cookie.getValue();
				}
			}
		}
		return token;
	}

	// 清除cookie
	public Cookie cleanCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookie.setValue("");
				cookie.setPath("/");
				cookie.setMaxAge(1);
				return cookie;
			}
		}
		return null;
	}
}
