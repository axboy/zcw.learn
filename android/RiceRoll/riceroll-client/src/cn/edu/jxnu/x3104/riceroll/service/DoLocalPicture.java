package cn.edu.jxnu.x3104.riceroll.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import cn.edu.jxnu.x3104.riceroll.util.MyConstants;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

@SuppressLint("SdCardPath")
public class DoLocalPicture {
	private final static String TAG = "DoLocalPicture";
	String filePath;
	Bitmap bitmap;
	String url;

	/**构造函数*/
	public DoLocalPicture(){
		/**避免文件夹不存在*/
		File folder=new File(new MyConstants().IMG_URL);
		if(!folder.exists()){
			folder.mkdir();
		}
	}

	/**从服务器获取图片，并存入SD卡*/
	public void writeToSD(String fileDir,String fileName){	
		if(fileName.equals("0")||fileName=="0"||fileName.contains( "(" ) || fileName.contains("[")){
			fileName="0.png";
		}
		url=new MyConstants().Server_IMG_URL+fileDir+"/"+fileName;
		//避免文件夹不存在
		File folder=new File(new MyConstants().IMG_URL+fileDir+"/");
		if(!folder.exists()){
			folder.mkdir();
		}
		filePath=new MyConstants().IMG_URL+fileDir+"/"+fileName;
		new Thread(new Runnable(){
			public void run() {
				try { 
					File file=new File(filePath);
					//如果文件已存在，则不下载
					//if(!file.exists()){
						HttpGet get = new HttpGet(url);
						HttpClient client = new DefaultHttpClient();
						HttpResponse response = client.execute(get); 
						HttpEntity entity = response.getEntity(); 
						BufferedHttpEntity bufferedHttpEntity=new BufferedHttpEntity(entity);
						InputStream is = bufferedHttpEntity.getContent(); 
						bitmap = BitmapFactory.decodeStream(is);
						FileOutputStream out = new FileOutputStream(file);
						bitmap.compress(Bitmap.CompressFormat.JPEG,100, out);
						out.flush();
						out.close();
					//}
				} catch (ClientProtocolException e) { 
					e.printStackTrace(); 
				} catch (IOException e) { 
					e.printStackTrace(); 
				}	
			}				
		}).start();	
	}

	/**从SD卡读取图片*/
	public Bitmap readFromSD(String fileDir,String fileName){
		if(fileName.equals("0")||fileName=="0"||fileName.contains( "(" ) || fileName.contains("[")){
			fileName="0.png";
		}
		filePath=new MyConstants().IMG_URL+fileDir+"/"+fileName;
		bitmap=BitmapFactory.decodeFile(filePath);
		return bitmap;
	}

	/**删除SD卡内指定图片*/
	public void deletePictureFromSD(String fileDir,String fileName){
		//容错机制暂时不加
		File file=new File(new MyConstants().IMG_URL+fileDir+"/"+fileName);
		file.delete();
	}

	/**获取服务器图片，不保存*/
	public Bitmap getServerPicture(String fileDir,String fileName){
		if(fileName.equals("0")||fileName=="0"||fileName.contains( "(" ) || fileName.contains("[")){
			fileName="0.png";
		}
		url=new MyConstants().Server_IMG_URL+fileDir+"/"+fileName;
		try { 
			HttpGet get = new HttpGet(url); 
			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(get); 
			HttpEntity entity = response.getEntity(); 
			BufferedHttpEntity bufferedHttpEntity=new BufferedHttpEntity(entity);
			InputStream is = bufferedHttpEntity.getContent(); 
			bitmap = BitmapFactory.decodeStream(is);
		} catch (ClientProtocolException e) { 
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
		return bitmap;
	}
}
