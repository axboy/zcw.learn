package cn.edu.jxnu.x3104.team3.service;

import java.io.File;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户信息服务
 */
public interface UserInfoService {
	/**
	 * 
	 * @param token
	 *                 授权码
	 * @param new_user_name
	 *                 新用户名
	 * @param user_password
	 *                 新密码
	 * @param user_phonenumber
	 *                 新手机号
	 * @param user_address
	 *                 新地址
	 * @param headImg
	 *                 新头像
	 * @return
	 *                 修改是否成功
	 */
	public boolean UserInfoUpdate(String token,String new_user_name,
			String user_password,String user_phonenumber,
			String user_address,File headImg);
	/**
	 * @param cookbook_id
	 *                   菜谱id
	 * @return
	 *        记录是否成功
	 */
	public boolean CookbookClickUpdate(String cookbook_id);
	/**
	 * @param token
	 *                 授权码
	 * @param cookbook_id
	 *                 菜谱id
	 * @return
	 *        记录是否成功
	 */
	public boolean CookbookCollectUpdate(String token,String cookbook_id);
	/**
	 * @param cookbook_id
	 *                   菜谱id
	 * @return
	 *        记录是否成功
	 */
	public boolean CookbookDownloadUpdate(String cookbook_id);
	/**
	 * @param token
	 *                  授权码
	 * @return
	 *         含有用户信息对象的jsonobject
	 */
	public JSONObject GetUserInfo(String token);
	
}
