package cn.edu.jxnu.x3104.riceroll.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import cn.edu.jxnu.x3104.riceroll.util.MyConstants;

public class DoFeedBack {
	private final static String TAG = "DoFeedBack";

	/**
	 * 反馈、建议到服务器
	 * @param type 类型
	 * @param content 内容
	 * */
	public int feedBackToServer(String id,String type,String content,Context context){
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("feedback_user_id",id));
		params.add(new BasicNameValuePair("feedback_keyword",type));
		params.add(new BasicNameValuePair("feedback_content",content));
		//params.add(new BasicNameValuePair("pictures",null));
		try{
			HttpClient hc=new DefaultHttpClient();
			HttpPost request=new HttpPost(new MyConstants().ServerFeedBack);
			request.setHeader("Cookie",new DoSharedPreferences().getCookies(context));
			request.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			HttpResponse response=hc.execute(request);
			Log.w(TAG,response.getStatusLine().getStatusCode()+"");
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity=response.getEntity();
				String strJson=EntityUtils.toString(entity,"utf-8");
				Log.d(TAG,strJson);
				JSONObject jsonObject=new JSONObject(strJson);
				int result=jsonObject.getInt("feedback_result");
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 5003;	//默认反馈失败
	}
}
