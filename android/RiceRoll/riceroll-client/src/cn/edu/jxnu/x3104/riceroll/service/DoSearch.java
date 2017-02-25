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
import cn.edu.jxnu.x3104.riceroll.bean.CookBook;
import cn.edu.jxnu.x3104.riceroll.bean.DailyRecommend;
import cn.edu.jxnu.x3104.riceroll.util.MyConstants;

public class DoSearch {
	private final static String TAG = "DoSearch";

	/**用户通过关键词搜索食谱*/
	public List<CookBook> searchCookBookByKeyWord(String keyword,Context context){
		Log.d(TAG, "keyword"+keyword);
		List<CookBook> items=new ArrayList<CookBook>();
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("cookbook_keyword",keyword));
		try{
			HttpClient hc=new DefaultHttpClient();
			HttpPost request=new HttpPost(new MyConstants().ServerSearchCookBookByKeyWord);
			request.setHeader("Cookie",new DoSharedPreferences().getCookies(context));
			request.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			HttpResponse response=hc.execute(request);
			Log.w(TAG,response.getStatusLine().getStatusCode()+"");
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity=response.getEntity();
				String strJson=EntityUtils.toString(entity,"utf-8");
				if(strJson!=null){
					JSONArray jsonArray=new JSONArray(strJson);
					for(int i=0;i<jsonArray.length();i++){
						JSONObject json=jsonArray.getJSONObject(i);
						String cookbook_id=json.getString("cookbook_id");
						String cookbook_tip=json.getString("cookbook_tip");
						int cookbook_download_times=json.getInt("cookbook_download_time");
						String cookbook_photo_link=json.getString("cookbook_photo_link");
						String cookbook_name=json.getString("cookbook_name");
						String cookbook_keyword=json.getString("cookbook_keyword");
						String cookbook_step=json.getString("cookbook_step");
						int cookbook_click_times=json.getInt("cookbook_click_time");
						String cookbook_brief_introduction=json.getString("cookbook_brief_introduction");
						String cookbook_condiment_id=json.getString("cookbook_condiment_id");
						String cookbook_food_material_id=json.getString("cookbook_food_material_id");
						int cookbook_collect_times=json.getInt("cookbook_collect_time");
						items.add(new CookBook(cookbook_id, cookbook_name,
								cookbook_keyword, cookbook_brief_introduction,
								cookbook_photo_link, cookbook_tip,
								cookbook_step, cookbook_food_material_id,
								cookbook_condiment_id, cookbook_click_times,
								cookbook_download_times, cookbook_collect_times, false));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return items;
	}

	/**用户通过勾选冰箱内食材搜索食谱*/
	public List<CookBook> searchCookBookByFMId(String fm_id,Context context){
		Log.d(TAG, "keyword"+fm_id);
		List<CookBook> items=new ArrayList<CookBook>();
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("foodMaterial_id",fm_id));
		try{
			HttpClient hc=new DefaultHttpClient();
			HttpPost request=new HttpPost(new MyConstants().ServerSearchCookBookByFoodMaterial);
			request.setHeader("Cookie",new DoSharedPreferences().getCookies(context));
			request.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			HttpResponse response=hc.execute(request);
			Log.w(TAG,response.getStatusLine().getStatusCode()+"");
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity=response.getEntity();
				String strJson=EntityUtils.toString(entity,"utf-8");
				if(strJson!=null){
					JSONArray jsonArray=new JSONArray(strJson);
					for(int i=0;i<jsonArray.length();i++){
						JSONObject json=jsonArray.getJSONObject(i);	
						String cookbook_id=json.getString("cookbook_food_material_id");
						String cookbook_tip=json.getString("cookbook_tip");
						int cookbook_download_times=json.getInt("cookbook_download_time");
						String cookbook_photo_link=json.getString("cookbook_photo_link");
						String cookbook_name=json.getString("cookbook_name");
						String cookbook_keyword=json.getString("cookbook_keyword");
						String cookbook_step=json.getString("cookbook_step");
						int cookbook_click_times=json.getInt("cookbook_click_time");
						String cookbook_brief_introduction=json.getString("cookbook_brief_introduction");
						String cookbook_condiment_id=json.getString("cookbook_condiment_id");
						String cookbook_food_material_id=json.getString("cookbook_food_material_id");
						int cookbook_collect_times=json.getInt("cookbook_collect_time");
						items.add(new CookBook(cookbook_id, cookbook_name,
								cookbook_keyword, cookbook_brief_introduction,
								cookbook_photo_link, cookbook_tip,
								cookbook_step, cookbook_food_material_id,
								cookbook_condiment_id, cookbook_click_times,
								cookbook_download_times, cookbook_collect_times, false));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return items;
	}
}
