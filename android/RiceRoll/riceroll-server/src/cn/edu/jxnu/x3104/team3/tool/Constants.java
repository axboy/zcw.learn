package cn.edu.jxnu.x3104.team3.tool;

/**
 * 操作码定义类<br>
 * 以0开头为cookie操作码<br>
 * 以1开头为注册时所用操作码<br>
 * 以2开头为登录时所用操作码<br>
 * 以3开头为用户更新数据时所用操作码<br>
 * 以4开头为搜索菜谱时所用操作码<br>
 * 以5开头为用户反馈时所用操作码<br>
 * 以6开头为管理员操作码<br>
 */
public class Constants {
	/*********************************************************
	 *                     cookie操作码                                                           *
	 *********************************************************/
	/**
	 * 用户cookie失效
	 */
	public static int USER_COOKIE_ERROR=0001;
	/**
	 * 非法访问
	 */
	public static int USER_ILLEGAL_ERROR=0002;
	/*********************************************************
	 *                     注册操作码                                                                  *
	 *********************************************************/
	/**
	 * 用户注册成功
	 */
	public static int USER_REGISTER_SUCCESS=1001;
	/**
	 * 用户注册失败
	 */
	public static int USER_REGISTER_FAIL=1002;
	/**
	 * 用户名已存在
	 */
	public static int USER_REGISTER_NAME_ERROR=1003;
	/**
	 * 用户注册时发生未知错误
	 */
	public static int USER_REGISTER_UNKNOW_ERROR=1004;
	
	/*********************************************************
	 *                     登录操作码                                                                  *
	 *********************************************************/
	/**
	 * 用户登录成功
	 */
	public static int USER_LOGIN_SUCCESS=2001;
	/**
	 * 用户名不存在
	 */
	public static int USER_LOGIN_NAME_INEXISTENCE=2002;
	/**
	 * 用户密码错误
	 */
	public static int USER_LOGIN_PASSWORD_ERROR=2003;
	/**
	 * 用户登录时发生未知错误
	 */
	public static int LOGIN_UNKNOW_ERROR=2004;
	/**
	 * 用户类型错误
	 */
	public static int LOGIN_UNKNOWTYPE_ERROR=2100;

	/*********************************************************
	 *                     用户更新操作码                                                           *
	 *********************************************************/
	/**
	 * 用户更新自身信息成功
	 */
	public static int USERINFO_UPDATE_SUCCESS=3001;
	/**
	 * 用户更新自身信息失败
	 */
	public static int USERINFO_UPDATE_FAIL=3002;
	/**
	 * 用户获取自身信息失败
	 */
	public static int USERINFO_GET_FAIL=3003;
	/**
	 * 用户点击菜谱次数更新失败
	 */
	public static int USER_CLICK_UPDATE_FAIL=3004;
	/**
	 * 用户收藏菜谱数据更新失败
	 */
	public static int USER_COLLECT_UPDATE_FAIL=3005;
	/**
	 * 用户下载菜谱次数更新失败
	 */
	public static int USER_DOWNLOAD_UPDATE_FAIL=3006;
	/**
	 * 用户更新成功
	 */
	public static int USER_UPDATE_SUCCESS=3200;
	/**
	 * 用户更新时遇到未知错误错误
	 */
	public static int USER_UPDATE_UNKNOW_ERROR=3100;
	
	
	/*********************************************************
	 *                     用户菜谱相关操作码                                                   *
	 *********************************************************/
	/**
	 * 查询菜谱出错
	 */
	public static int SEARCH_COOKBOOK_FAIL = 4001;
	/**
	 * 关键词不能为空
	 */
	public static int SEARCH_COOKBOOK_KEYWORD_BLANK = 4002;
	/**
	 * 获取菜谱出错
	 */
	public static int PRODUCE_COOKBOOK_FAIL = 4003;
	/**
	 * 食材ID不能为空
	 */
	public static int PRODUCE_COOKBOOK_KEYWORD_BLANK = 4004;
	/**
	 * 菜谱ID不能为空
	 */
	public static int PRODUCE_COOKBOOK_NAME_BLANK = 4005;
	
	
	/*********************************************************
	 *                     用户反馈操作码                                                           *
	 *********************************************************/
	/**
	 * 用户反馈成功
	 */
	public static int FEEDBACK_SUCCESS = 5001;
	/**
	 * 用户反馈信息不全
	 */
	public static int FEEDBACK_BLANK = 5002;
	/**
	 * 用户反馈失败
	 */
	public static int FEEDBACK_FAIL = 5003;
	
	
	
	/*********************************************************
	 *                         文件路径                                                            *
	 *********************************************************/
	/**
	 * 用户反馈图片路径
	 */
	public static String FEEDBACKPATH = "D:\\Program Files (x86)\\MyProject\\workspace\\RiceRoll\\WebContent\\IMAGE\\FEEDBACK";
	/**
	 * 用户头像图片路径
	 */
	public static String HEADIMGPATH = "D:\\Program Files (x86)\\MyProject\\workspace\\RiceRoll\\WebContent\\IMAGE\\HEADIMG";
	/**
	 * apk下载路径
	 */
	public static String APKPATH = "D:\\Program Files (x86)\\MyProject\\workspace\\RiceRoll\\WebContent\\APK";
	/**
	 * 推荐图片储存路径
	 */
	public static String RECOMMEND_PATH = "D:\\Program Files (x86)\\MyProject\\workspace\\RiceRoll\\WebContent\\IMAGE\\RECOMMEND";
	
	
	
	/*********************************************************
	 *                         管理员操作码                                                     *
	 *********************************************************/
	/**
	 * 今日推荐已满5次
	 */
	public static int RECOMMEND_FULL = 6001;
	/**
	 * 推荐图片不能为空
	 */
	public static int RECOMMEND_PICTRUE_NULL = 6002;
	/**
	 * 推荐图片储存出错
	 */
	public static int RECOMMEND_PICTRUE_ERROR = 6003;
	/**
	 * 推荐成功
	 */
	public static int RECOMMEND_SUCCESS = 6100;
	/**
	 * 注册成功
	 */
	public static int ADMIN_REGISTER_SUCCESS = 6010;
	/**
	 * 注册失败
	 */
	public static int ADMIN_REGISTER_FAIL = 6011;
	/**
	 * 用户名已存在
	 */
	public static int ADMIN_REGISTER_NAME_ERROR = 6012;
	/**
	 * 登陆成功
	 */
	public static int ADMIN_LOGIN_SUCCESS = 6020;	
	/**
	 * 用户名不存在
	 */
	public static int ADMIN_NAME_INEXISTENCE = 6021;
	/**
	 * 密码错误
	 */
	public static int ADMIN_LOGIN_PASSWORD_ERROR = 6022;	
	/**
	 * 管理员登录时发生未知错误
	 */
	public static int ADMIN_LOGIN_UNKNOW_ERROR = 6023;		
	/**
	 * 注册时发生未知错误
	 */
	public static int ADMIN_REGISTER_UNKNOW_ERROR = 6013;
	
	
	
}
