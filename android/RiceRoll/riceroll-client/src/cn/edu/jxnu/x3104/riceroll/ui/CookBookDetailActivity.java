package cn.edu.jxnu.x3104.riceroll.ui;

import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.bean.Condiment;
import cn.edu.jxnu.x3104.riceroll.bean.CookBook;
import cn.edu.jxnu.x3104.riceroll.db.DbCondiment;
import cn.edu.jxnu.x3104.riceroll.db.DbDailyCookBook;
import cn.edu.jxnu.x3104.riceroll.service.DoHttp;
import cn.edu.jxnu.x3104.riceroll.service.DoLocalPicture;
import cn.edu.jxnu.x3104.riceroll.service.DoSearch;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CookBookDetailActivity extends Activity{
	private final static String TAG = "CookBookDetailActivity";

	String name;
	Bitmap bitmap;
	ImageView ivImg;
	CookBook cookbook;
	MyAsyncTask mat;
	TextView tvName,tvClickTimes,tvDownLoadTimes,tvTip,tvIntroduction;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cookbook_detail);
		init();
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent=getIntent();
				name=intent.getStringExtra("cookbook_detail");
				Log.d(TAG,name);
				mat=new MyAsyncTask();
				mat.execute();
			}			
		}).start();
	}

	/**初始化控件*/
	private void init() {
		// TODO Auto-generated method stub
		tvName=(TextView)findViewById(R.id.cookbook_name);
		tvClickTimes=(TextView)findViewById(R.id.cookbook_click_times);
		tvDownLoadTimes=(TextView)findViewById(R.id.cookbook_download_times);
		tvTip=(TextView)findViewById(R.id.cookbook_tip);
		tvIntroduction=(TextView)findViewById(R.id.cookbook_brief_introduction);
		ivImg=(ImageView)findViewById(R.id.cookbook_img);
	}
	
	class MyAsyncTask extends AsyncTask<Void , Void, Void>{
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub				
			cookbook=new DoHttp().getCookBookByName(name);
			String []links=cookbook.getCookbook_photo_link().split(";");
			Log.d(TAG,"links="+links[0]);
			bitmap=new DoLocalPicture().getServerPicture("COOKBOOKIMAGE", links[0]);
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			tvName.setText(cookbook.getCookbook_name());
			tvClickTimes.setText("点击次数："+cookbook.getCookbook_click_times());
			tvDownLoadTimes.setText("下载次数："+cookbook.getCookbook_download_times());
			tvIntroduction.setText(splitString(cookbook.getCookbook_brief_introduction()));				
			ivImg.setImageBitmap(bitmap);
			tvTip.setText(splitString(cookbook.getCookbook_tip()));
		}
	}
	
	/**分割字符串*/
	String splitString(String s){
		String []texts=s.split(";");
		String text="";
		for(int i=0;i<texts.length;i++){
			text+=texts[i]+"\n";
		}
		return text;
	}
	
	public void seeStep(View v){
		Intent intent=new Intent();
		intent.putExtra("cookbook_img_links",cookbook.getCookbook_photo_link());
		intent.putExtra("cookbook_step",cookbook.getCookbook_step());
		intent.setClass(CookBookDetailActivity.this,CookBookDetailStepActivity.class);
		startActivity(intent);
	}
	
	public void addToCookBook(View v){
		mainhandler.sendEmptyMessage(0x300);
	}
	
	/**处理消息*/
	private Handler mainhandler=new Handler(){
		public void handleMessage(Message msg){
			/**0x300加载到适配器*/
			if(msg.what==0x300){
				try{
				new DbDailyCookBook().addCookBookToNow(name);
				}catch(SQLException e){
					new DbDailyCookBook().createTableDailyCookBook();
					new DbDailyCookBook().addCookBookToNow(name);
				}
			}
			Toast.makeText(CookBookDetailActivity.this,"加入菜单成功",Toast.LENGTH_SHORT);
			Log.d(TAG,"加入菜单成功");
		}
	};
	
	
	public void exit(View v){
		this.finish();
	}
}
