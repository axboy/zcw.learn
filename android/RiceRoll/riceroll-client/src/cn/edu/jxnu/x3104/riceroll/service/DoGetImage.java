package cn.edu.jxnu.x3104.riceroll.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class DoGetImage {
	private final static String TAG = "DoGetImage";
	String urlImg;
	Bitmap img=null;

	/**
	 * 从服务器获取图片
	 * @param link 完整的图片地址
	 * @return Bitmap对象
	 */
	public Bitmap getImageFromServer(String link){
		HttpGet get = new HttpGet(link); 
		HttpClient client = new DefaultHttpClient(); 
		Bitmap pic = null; 
		try { 
			HttpResponse response = client.execute(get); 
			HttpEntity entity = response.getEntity(); 
			InputStream is = entity.getContent();
			pic = BitmapFactory.decodeStream(is);
		} catch (ClientProtocolException e) { 
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		return pic; 
	}

	/**处理消息*/
	private Handler mainhandler=new Handler(){
		public void handleMessage(Message msg){
			/**0x300加载到适配器*/
			new Thread(new Runnable(){
				public void run() {	
					try{
						URL url=new URL(urlImg);
						HttpURLConnection connection = (HttpURLConnection)url.openConnection();
						connection.setConnectTimeout(5*1000);
						connection.setDoInput(true);
						connection.connect();
						InputStream is=connection.getInputStream();
						img=BitmapFactory.decodeStream(is);
					}catch(Exception e){
						Log.e(TAG,"从服务器获取失败");
					}
				}			
			}).start();
		}			
	};
}