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
import cn.edu.jxnu.x3104.riceroll.bean.Condiment;
import cn.edu.jxnu.x3104.riceroll.bean.CookBook;
import cn.edu.jxnu.x3104.riceroll.bean.DailyRecommend;
import cn.edu.jxnu.x3104.riceroll.bean.FoodMaterial;
import cn.edu.jxnu.x3104.riceroll.util.MyConstants;

public class DoHttp {
	private final static String TAG = "DoHttp";

	/**从服务器获取每日推荐*/
	public List<DailyRecommend> getDyilyRecommend(){
		Log.d(TAG,"获取每日推荐");
		List<DailyRecommend> list=new ArrayList<DailyRecommend>();
		try{
			HttpClient hc=new DefaultHttpClient();
			HttpPost request=new HttpPost(new MyConstants().ServerGetDailyRecommend);
			//request.setHeader("Cookie",new DoSharedPreferences().getCookies(context));
			//request.setEntity(new UrlEncodedFormEntity(null,HTTP.UTF_8));
			HttpResponse response=hc.execute(request);
			Log.w(TAG,response.getStatusLine().getStatusCode()+"");
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity=response.getEntity();
				String strJson=EntityUtils.toString(entity,"utf-8");
				if(strJson!=null){
					JSONArray jsonArray=new JSONArray(strJson);
					for(int i=0;i<jsonArray.length();i++){
						JSONObject json=jsonArray.getJSONObject(i);	
						//Log.e(TAG,json.toString());
						String id=json.getString("recommend_id");
						String cookbook_name=json.getString("recommend_cookbook_name");
						String introduction_text=json.getString("recommend_brief_introduction");
						String recommend_reason=json.getString("recommend_reason");
						String img_path=json.getString("recommend_pictures_link");
						list.add(new DailyRecommend(id, cookbook_name,
								introduction_text, recommend_reason, img_path));
					}					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	/**从服务器获取食材表*/
	public List<FoodMaterial> updateFoodMaterial(Context context){
		List<FoodMaterial> foodMaterialList=new ArrayList<FoodMaterial>();
		try{
			HttpClient hc=new DefaultHttpClient();
			HttpPost request=new HttpPost(new MyConstants().ServerUpdateFoodMaterial);
			request.setHeader("Cookie",new DoSharedPreferences().getCookies(context));
			//request.setEntity(new UrlEncodedFormEntity(null,HTTP.UTF_8));
			HttpResponse response=hc.execute(request);
			Log.w(TAG,response.getStatusLine().getStatusCode()+"");
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity=response.getEntity();
				String strJson=EntityUtils.toString(entity,"utf-8");
				if(strJson!=null){
					JSONArray jsonArray=new JSONArray(strJson);
					for(int i=0;i<jsonArray.length();i++){
						JSONObject json=jsonArray.getJSONObject(i);						
						String foodMaterial_id=json.getString("food_material_id");
						String foodMaterial_name=json.getString("food_material_name");
						String foodMaterial_photo_link=json.getString("food_material_photo_link");
						int foodMaterial_protein=json.getInt("food_material_protein");
						int foodMaterial_sugar=json.getInt("food_material_sugar");
						int foodMaterial_fat=json.getInt("food_material_fat");
						int foodMaterial_dietary_fiber=json.getInt("food_material_dietary_fiber");
						int foodMaterial_vc=json.getInt("food_material_vc");
						int foodMaterial_calcium=json.getInt("food_material_calcium");
						int foodMaterial_muriate=json.getInt("food_material_muriate");
						int foodMaterial_natrium=json.getInt("food_material_natrium");
						int foodMaterial_phosphorus=json.getInt("food_material_phosphorus");
						int foodMaterial_calories=json.getInt("food_material_calories");
						foodMaterialList.add(new FoodMaterial(foodMaterial_id, foodMaterial_name,
								foodMaterial_photo_link, foodMaterial_protein, 
								foodMaterial_sugar, foodMaterial_fat, 
								foodMaterial_dietary_fiber, foodMaterial_vc,
								foodMaterial_calcium, foodMaterial_muriate,
								foodMaterial_natrium, foodMaterial_phosphorus, 
								foodMaterial_calories));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Log.d(TAG, "成功从服务器获取食材列表"); 
		return foodMaterialList;
	}

	/**从服务器获取调料表*/
	public List<Condiment> updateCondiment(Context context){
		List<Condiment> condimentList=new ArrayList<Condiment>();
		try{
			HttpClient hc=new DefaultHttpClient();
			HttpPost request=new HttpPost(new MyConstants().ServerUpdateCondiment);
			request.setHeader("Cookie",new DoSharedPreferences().getCookies(context));
			//request.setEntity(new UrlEncodedFormEntity(null,HTTP.UTF_8));
			HttpResponse response=hc.execute(request);
			Log.w(TAG,response.getStatusLine().getStatusCode()+"");
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity=response.getEntity();
				String strJson=EntityUtils.toString(entity,"utf-8");
				if(strJson!=null){
					JSONArray jsonArray=new JSONArray(strJson);
					for(int i=0;i<jsonArray.length();i++){
						JSONObject json=jsonArray.getJSONObject(i);						
						String condiment_id=json.getString("condiment_id");
						String condiment_name=json.getString("condiment_name");
						String condiment_photo_link=json.getString("condiment_photo_link");
						int condiment_protein=json.getInt("condiment_protein");
						int condiment_sugar=json.getInt("condiment_sugar");
						int condiment_fat=json.getInt("condiment_fat");
						int condiment_dietary_fiber=json.getInt("condiment_dietary_fiber");
						int condiment_vc=json.getInt("condiment_vc");
						int condiment_calcium=json.getInt("condiment_calcium");
						int condiment_muriate=json.getInt("condiment_muriate");
						int condiment_natrium=json.getInt("condiment_natrium");
						int condiment_phosphorus=json.getInt("condiment_phosphorus");
						int condiment_calories=json.getInt("condiment_calories");
						condimentList.add(new Condiment(condiment_id, condiment_name,
								condiment_photo_link, condiment_protein, 
								condiment_sugar, condiment_fat, 
								condiment_dietary_fiber, condiment_vc,
								condiment_calcium, condiment_muriate,
								condiment_natrium, condiment_phosphorus, 
								condiment_calories));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Log.d(TAG, "成功从服务器获取调料列表"); 
		return condimentList;
	}

	/**通过菜谱id搜索菜谱*/
	public CookBook getCookBookByName(String name){
		CookBook cb=null;
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("cookbook_name",name));
		try{			
			HttpClient hc=new DefaultHttpClient();
			HttpPost request=new HttpPost(new MyConstants().ServerGetCookBookByName);
			request.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			HttpResponse response=hc.execute(request);
			Log.w(TAG,response.getStatusLine().getStatusCode()+"");
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity=response.getEntity();
				String strJson=EntityUtils.toString(entity,"utf-8");
				//Log.e(TAG, strJson);
				if(strJson!=null){
					JSONObject json=new JSONObject(strJson);
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
					cb=new CookBook(cookbook_id, cookbook_name,
							cookbook_keyword, cookbook_brief_introduction, 
							cookbook_photo_link, cookbook_tip,
							cookbook_step, cookbook_food_material_id, 
							cookbook_condiment_id, cookbook_click_times,
							cookbook_download_times, cookbook_collect_times,
							false);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return cb;
	}


}