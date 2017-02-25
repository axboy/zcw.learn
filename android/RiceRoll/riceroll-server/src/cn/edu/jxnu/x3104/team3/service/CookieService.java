package cn.edu.jxnu.x3104.team3.service;


public interface CookieService {
	/**
	 * 保存授权信息
	 * 
	 * @param token
	 *            授权码
	 * @param user_type
	 *            用户类别
	 * @param user_account
	 *            用户名
	 * @return 保存是否成功
	 */
	public boolean saveCookie(String token, int user_type, String user_account); 

	/**
	 * 检查token是否有效
	 * 
	 * @param token
	 *            待检查的token
	 * @return true为有效，false为无效
	 */
	public boolean checkToken(String token);
	
	/**
	 * 检查token是否有效
	 * 
	 * @param token
	 *            待检查的token
	 * @return null为无效 ，admin则有效
	 */
	//public User checkToken(String token);

	/**
	 * 清除授权信息
	 * 
	 * @param token
	 *            授权信息
	 * @return 清楚是否成功
	 */
	public boolean clearCookie(String token);
}
