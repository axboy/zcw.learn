package cn.edu.jxnu.x3104.riceroll.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import cn.edu.jxnu.x3104.riceroll.bean.User;
import cn.edu.jxnu.x3104.riceroll.util.MyConstants;

public class DoUserInfo {
	private final static String TAG = "DoUserInfo";	

	/**获取用户信息*/
	public User getUserInfo(Context context){
		User u=null;
		try{
			HttpClient hc=new DefaultHttpClient();
			HttpPost request=new HttpPost(new MyConstants().ServerGetUserInfo);
			request.setHeader("Cookie",new DoSharedPreferences().getCookies(context));
			//request.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			HttpResponse response=hc.execute(request);
			Log.w(TAG,response.getStatusLine().getStatusCode()+"");
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity=response.getEntity();
				String strJson=EntityUtils.toString(entity,"utf-8");
				if(strJson!=null){
					JSONObject json=new JSONObject(strJson);
					Log.e(TAG,strJson);
					String userName=json.getString("user_name");	
					String userTel=json.getString("user_phonenumber");	
					String userAddress=json.getString("user_address");	
					String userPwd=json.getString("user_password");	
					String userId=json.getString("user_id");
					String userRegDate=json.getString("user_registerDate");
					u=new User(userId,userName,userPwd,userAddress,userTel,userRegDate);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return u;
	}

	/**更新用户信息*/
	public int updateUserInfo(User u,Context context){

		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("new_user_name",u.getUserNickName()));
		params.add(new BasicNameValuePair("user_password",u.getUserPassWord()));
		params.add(new BasicNameValuePair("user_phonenumber",u.getUserTel()));
		params.add(new BasicNameValuePair("user_address",u.getUserAddress()));
		params.add(new BasicNameValuePair("user_headimage",null));
		try{
			HttpClient hc=new DefaultHttpClient();
			HttpPost request=new HttpPost(new MyConstants().ServerUpdateUserInfo);
			request.setHeader("Cookie",new DoSharedPreferences().getCookies(context));
			request.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			HttpResponse response=hc.execute(request);
			Log.w(TAG,response.getStatusLine().getStatusCode()+"");
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity=response.getEntity();
				String strJson=EntityUtils.toString(entity,"utf-8");
				Log.d(TAG,strJson);
				JSONObject jsonObject=new JSONObject(strJson);
				int result=jsonObject.getInt("update_result");
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 3002;
		
	}
}
