package cn.edu.jxnu.x3104.riceroll.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.bean.DailyRecommend;
import cn.edu.jxnu.x3104.riceroll.db.DbDailyRecommend;
import cn.edu.jxnu.x3104.riceroll.service.DoHttp;
import cn.edu.jxnu.x3104.riceroll.service.DoLocalPicture;
import cn.edu.jxnu.x3104.riceroll.util.MyListView;
import cn.edu.jxnu.x3104.riceroll.util.MyListView.IOnRefreshListener;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter.ViewBinder;

//未登录时的主页，即每日推荐
public class MainWindowActivityUnlogin extends FragmentActivity{
	private final static String TAG = "MainWindowActivity";

	private MyListView listView;
	private SimpleAdapter sa;
	private List<DailyRecommend> list;
	private int _position;
	private List<Map<String,Object>> items=new ArrayList<Map<String,Object>>();

	private RefreshDataAsynTask mRefreshAsynTask;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_window_unlogin);
		init();
		setOnItemClickListener();
		getRecommendListFromDb();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		listView=(MyListView)findViewById(R.id.mainpage_mylistview_recommend);
		sa=new SimpleAdapter(
				this,
				items,
				R.layout.mainpage_main_mylistview_item,
				new String[]{"crecommend_text","recommend_img"},
				new int[]{R.id.mainpage_recommend_text,R.id.mainpage_recommend_img});

		sa.setViewBinder(new ViewBinder() {
			public boolean setViewValue(View view, Object data, String textRepresentation) { 
				if(view instanceof ImageView  && data instanceof Bitmap){  
					ImageView iv = (ImageView) view; 
					iv.setImageBitmap((Bitmap) data);
					return true;  
				}else  
					return false;  
			}  
		}); 
	}

	private void getRecommendListFromDb() {
		// TODO Auto-generated method stub
		if(true){
			try{
				list=new DbDailyRecommend().getAllData();				
				items.clear();
				for(DailyRecommend dr:list){
					HashMap<String,Object> map=new HashMap<String,Object>();
					map.put("crecommend_text",dr.getIntroduction_text());
					String []links=dr.getImg_path().split(";");
					Bitmap  b=new DoLocalPicture().readFromSD("RECOMMEND", links[0]);
					map.put("recommend_img",b);
					items.add(map);
				}
				mainhandler.sendEmptyMessage(0x300);
			}catch(SQLException e){
				/*表不存在*/
				new DbDailyRecommend().createTableDailyRecommend();
				return;
			}
		}
	}

	private void setOnItemClickListener() {
		
		//下拉刷新
		listView.setOnRefreshListener(new IOnRefreshListener() {			
			public void OnRefresh() {				
				mRefreshAsynTask = new RefreshDataAsynTask();
				mRefreshAsynTask.execute();
			}
		});
		
		//item点击
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				_position=position;				
				new Thread(new Runnable(){
					public void run(){
						mainhandler.sendEmptyMessage(0x400);
					}
				}).start();
			}
		});
	}
	
	class RefreshDataAsynTask extends AsyncTask<Void , Void, Void>
	{
		protected Void doInBackground(Void... arg0) {
			list=new DoHttp().getDyilyRecommend();
			if(list.size()==0){
				return null;
			}
			try{
				for(DailyRecommend dr:list){
					new DbDailyRecommend().removeAll();
					new DbDailyRecommend().insertData(dr);
				}
			}catch(SQLiteException e){
				new DbDailyRecommend().createTableDailyRecommend();
				for(DailyRecommend dr:list){
					new DbDailyRecommend().insertData(dr);
				}
			}
			getRecommendListFromDb();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			listView.onRefreshComplete();
		}
	}
	

	/**处理消息*/
	private Handler mainhandler=new Handler(){
		public void handleMessage(Message msg){
			/**0x300加载到适配器*/
			if(msg.what==0x300){
				listView.setAdapter(sa);
			}
			/**0x400点击事件*/
			else if(msg.what==0x400){
				Intent intent=new Intent();
				intent.putExtra("open_daily_recommend_detail",list.get(_position-1).getId());				 
				intent.setClass(MainWindowActivityUnlogin.this,DailyRecommendDetailActivity.class);
				startActivity(intent);
			}
		}			
	};
	

	public void openFridge(View v){
		Intent intent = new Intent (MainWindowActivityUnlogin.this,UserLoginActivity.class);						
		startActivity(intent);
	}
}
