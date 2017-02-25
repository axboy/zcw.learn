package cn.edu.jxnu.x3104.riceroll.util;

/**常量类*/
public class MyConstants {
	/**数据库存放地址*/
	
	//http://10.3.129.42:8080/
	//http://panli  ang.wicp.net/
	public final static String DB_URL="/data/data/cn.edu.jxnu.x3104.riceroll/database/RiceRoll.db3";
	
	/**图片存放地址*/
	public final static String IMG_URL="/data/data/cn.edu.jxnu.x3104.riceroll/images/";
	//public final static String IMG_URL="/sdcard/RiceRoll/";
	//public final static String IMG_URL="/storage/sdcard0/RiceRoll/";
	
	public final static String Server_IMG_URL="http://10.3.129.42:8080/RiceRoll/IMAGE/";

	/**服务器注册地址*/
	public final static String ServerRegister="http://10.3.129.42:8080/RiceRoll/user/register";
	
	/**服务器注册地址*/
	public final static String ServerLogin="http://10.3.129.42:8080/RiceRoll/user/login";
	
	/**反馈、建议*/
	public final static String ServerFeedBack="http://10.3.129.42:8080/RiceRoll/user/feedback";
	
	/**获取用户信息*/
	public final static String ServerGetUserInfo="http://10.3.129.42:8080/RiceRoll/user/getuserinfo";
	
	/**修改用户信息*/
	public final static String ServerUpdateUserInfo="http://10.3.129.42:8080/RiceRoll/user/updateuserinfo";
	
	/**更新食材表*/
	public final static String ServerUpdateFoodMaterial="http://10.3.129.42:8080/RiceRoll/user/updatefoodmaterial";
	
	/**更新调料表*/
	public final static String ServerUpdateCondiment="http://10.3.129.42:8080/RiceRoll/user/updatecondiment";

	/**用户刷新每日推荐*/
	public final static String ServerGetDailyRecommend="http://10.3.129.42:8080/RiceRoll/user/getrecommenddaily";

	/**用户通过关键词搜索食谱*/
	public final static String ServerSearchCookBookByKeyWord="http://10.3.129.42:8080/RiceRoll/user/searchcookbookbykeyword";

	/**用户通过勾选冰箱内食材搜索食谱*/
	public final static String ServerSearchCookBookByFoodMaterial="http://10.3.129.42:8080/RiceRoll/user/searchcookbookbyfoodmaterial";
	
	/**通过菜谱id查找菜谱*/
	public final static String ServerGetCookBookByName="http://10.3.129.42:8080/RiceRoll/user/getcookbookbyname";
	
	
	
	
	/*********************************************************
	 *                     注册操作码                                                                  *
	 *********************************************************/
	
	/**用户注册成功*/
	public static int USER_REGISTER_SUCCESS=1001;
	
	/**用户注册失败*/
	public static int USER_REGISTER_FAIL=1002;
	
	/**用户名已存在*/
	public static int USER_REGISTER_NAME_ERROR=1003;
	
	/**用户注册时发生未知错误*/
	public static int USER_REGISTER_UNKNOW_ERROR=1004;
	
	/*********************************************************
	 *                     登录操作码                                                                  *
	 *********************************************************/
	
	/**用户登录成功*/
	public static int USER_LOGIN_SUCCESS=2001;
	
	/**用户名不存在*/
	public static int USER_LOGIN_NAME_INEXISTENCE=2002;
	
	/**用户密码错误*/
	public static int USER_LOGIN_PASSWORD_ERROR=2003;
	
	/**用户登录时发生未知错误*/
	public static int LOGIN_UNKNOW_ERROR=2004;
	
	/**用户类型错误*/
	public static int LOGIN_UNKNOWTYPE_ERROR=2100;
	
}