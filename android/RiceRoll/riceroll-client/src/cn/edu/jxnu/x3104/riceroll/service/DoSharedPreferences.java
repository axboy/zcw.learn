package cn.edu.jxnu.x3104.riceroll.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**SharedPreferences相关*/
public class DoSharedPreferences {
	private final static String TAG = "DoSharedPreferences";	

	/**存入cookies*/
	public void setCookies(Context context,String cookie){
		SharedPreferences sp=context.getSharedPreferences("RiceRollSharedPreferences", context.MODE_PRIVATE);
		SharedPreferences.Editor editor=sp.edit();
		editor.putString("cookie",cookie);
		editor.commit();
	}

	/**从本机获得cookies*/
	public String getCookies(Context context){
		SharedPreferences sp=context.getSharedPreferences("RiceRollSharedPreferences", context.MODE_PRIVATE);
		String cookie=sp.getString("cookie", "none");
		Log.d(TAG,cookie);
		return cookie;
	}	

	/**存储登录状态*/
	public void setLoginState(Context context,String state){
		SharedPreferences sp=context.getSharedPreferences("RiceRollSharedPreferences", context.MODE_PRIVATE);
		SharedPreferences.Editor editor=sp.edit();
		editor.putString("LoginState",state);
		editor.commit();
	}

	/**从本机获得用户的登录状态*/
	public String getLoginState(Context context){
		SharedPreferences sp=context.getSharedPreferences("RiceRollSharedPreferences", context.MODE_PRIVATE);
		return sp.getString("LoginState","unlogin");
	}

	/**存储已登录的用户名*/
	public void setLoginUserName(Context context,String userName){
		SharedPreferences sp=context.getSharedPreferences("RiceRollSharedPreferences", context.MODE_PRIVATE);
		SharedPreferences.Editor editor=sp.edit();
		editor.putString("userName",userName);
		editor.commit();
	}
	/**从本机获得当前已登录的用户名*/
	public String getLoginUserName(Context context){
		SharedPreferences sp=context.getSharedPreferences("RiceRollSharedPreferences", context.MODE_PRIVATE);
		return sp.getString("userName","none");
	}

	/**从本机获得当前已登录的手机号*/
	public String getLoginUserTel(Context context){
		SharedPreferences sp=context.getSharedPreferences("RiceRollSharedPreferences", context.MODE_PRIVATE);
		return sp.getString("userTel","none");
	}
	/**存储已登录的手机号*/
	public void setLoginUserTel(Context context,String userTel){
		SharedPreferences sp=context.getSharedPreferences("RiceRollSharedPreferences", context.MODE_PRIVATE);
		SharedPreferences.Editor editor=sp.edit();
		editor.putString("userTel",userTel);
		editor.commit();
	}


	/**存储已登录的用户id*/
	public void setLoginUserId(Context context,String userId){
		SharedPreferences sp=context.getSharedPreferences("RiceRollSharedPreferences", context.MODE_PRIVATE);
		SharedPreferences.Editor editor=sp.edit();
		editor.putString("userId",userId);
		editor.commit();
	}

	/**从本机获得当前已登录的用户id*/
	public String getLoginUserId(Context context){
		SharedPreferences sp=context.getSharedPreferences("RiceRollSharedPreferences", context.MODE_PRIVATE);
		return sp.getString("userId","none");
	}

	/**用户注销*/
	public void logoutServer(Context context){
		SharedPreferences sp=context.getSharedPreferences("RiceRollSharedPreferences", context.MODE_PRIVATE);
		SharedPreferences.Editor editor=sp.edit();
		editor.putString("cookie",null);
		editor.putString("LoginState",null);		//储存登录状态
		editor.putString("userName",null);			/**储存用户名，值待修改*/
		editor.commit();
	}
}
