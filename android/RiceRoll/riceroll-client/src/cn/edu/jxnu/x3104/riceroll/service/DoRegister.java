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
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import cn.edu.jxnu.x3104.riceroll.bean.User;
import cn.edu.jxnu.x3104.riceroll.ui.MainWindowActivity;
import cn.edu.jxnu.x3104.riceroll.ui.UserLoginActivity;
import cn.edu.jxnu.x3104.riceroll.util.MyConstants;

public class DoRegister {
	private final static String TAG = "DoRegister";

	/**注册账号*/
	public int registerAction(User u,Context context){
		Log.d(TAG,"进入注册函数");
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user_name",u.getUserNickName()));
		params.add(new BasicNameValuePair("user_password",u.getUserPassWord()));
		params.add(new BasicNameValuePair("user_phonenumber",u.getUserTel()));
		params.add(new BasicNameValuePair("user_address",u.getUserAddress()));
		try{
			HttpClient hc=new DefaultHttpClient();
			HttpPost request=new HttpPost(new MyConstants().ServerRegister);
			request.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			HttpResponse response=hc.execute(request);
			Log.w(TAG,response.getStatusLine().getStatusCode()+"");
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity=response.getEntity();
				String strJson=EntityUtils.toString(entity,"utf-8");
				Log.d(TAG,strJson);
				JSONObject jsonObject=new JSONObject(strJson);
				int result=jsonObject.getInt("register_result");
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 1004;
	}
	
}
