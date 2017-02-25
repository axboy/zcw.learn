package cn.edu.jxnu.x3104.riceroll.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import cn.edu.jxnu.x3104.riceroll.start.AppStart;
import cn.edu.jxnu.x3104.riceroll.ui.MainWindowActivity;
import cn.edu.jxnu.x3104.riceroll.util.MyConstants;

public class DoLogin {
	private final static String TAG = "DoLogin";
	
	/**登录到服务器*/
	public Boolean loginAction(String name,String pwd,Context context){
		Log.d(TAG,"进入登录函数");
		List<Cookie> cookies=new ArrayList<Cookie>();
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user_name",name));
		params.add(new BasicNameValuePair("user_password",pwd));
		params.add(new BasicNameValuePair("user_type","0"));
		try{
			HttpClient hc=new DefaultHttpClient();
			HttpPost request=new HttpPost(new MyConstants().ServerLogin);
			request.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			HttpResponse response=hc.execute(request);
			Log.w(TAG,response.getStatusLine().getStatusCode()+"");
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity=response.getEntity();
				String strJson=EntityUtils.toString(entity,"utf-8");
				Log.d(TAG,strJson);
				JSONObject jsonObject=new JSONObject(strJson);
				int result=jsonObject.getInt("login_result");
				if(result==2001){
					/**有两个cookie，只要使用第一个*/
					cookies = ((AbstractHttpClient) hc).getCookieStore()
							.getCookies();
					String c=cookies.get(0).getName()+"="+cookies.get(0).getValue()+";";
					new DoSharedPreferences().setCookies(context,c);
					new DoSharedPreferences().setLoginState(context,"login");
					new DoSharedPreferences().setLoginUserName(context,name);
					new DoSharedPreferences().setLoginUserTel(context,new DoUserInfo().getUserInfo(context).getUserTel());
					new DoSharedPreferences().setLoginUserId(context,new DoUserInfo().getUserInfo(context).getUserId());
					
					return true;
					//Toast.makeText(context,"用户"+name+"登录成功", Toast.LENGTH_SHORT).show();
				}else{
					return false;
					//Toast.makeText(context,"用户名或密码错误", Toast.LENGTH_SHORT).show();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
}
