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
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter.ViewBinder;
import android.app.AlertDialog;
import android.app.AlertDialog.*;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;

//主页，即每日推荐
public class MainPageMainFragment extends Fragment {
	private final static String TAG = "MainPageMainFragment";
	
	private Builder alertBulider;
	private AlertDialog alert;
	private Context context;
	
	private MyListView mRecommendList;
	private SimpleAdapter sa;
	private List<DailyRecommend> recommendList;
	private int _position;
	private List<Map<String,Object>> recommendListItems=new ArrayList<Map<String,Object>>();
	
	private RefreshDataAsynTask mRefreshAsynTask;


	public void SetContext(Context c){
		context=c;
		 alertBulider=new Builder(context);
		 alert=alertBulider.create();
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 View  view=  inflater.inflate(R.layout.mainpage_main, null);	
		 
		 init(view);
		 setOnItemClickListener();
		 getRecommendListFromDb();
		return view;
	}

	private void init(View view) {
		// TODO Auto-generated method stub
		mRecommendList=(MyListView)view.findViewById(R.id.mainpage_mylistview_recommend);
		sa=new SimpleAdapter(
				context,
				recommendListItems,
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
				recommendList=new DbDailyRecommend().getAllData();				
				recommendListItems.clear();
				for(DailyRecommend dr:recommendList){
					HashMap<String,Object> map=new HashMap<String,Object>();
					map.put("crecommend_text",dr.getIntroduction_text());
					String []links=dr.getImg_path().split(";");
					Bitmap  b=new DoLocalPicture().readFromSD("RECOMMEND", links[0]);
					map.put("recommend_img",b);
					recommendListItems.add(map);
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
		mRecommendList.setOnRefreshListener(new IOnRefreshListener() {			
			public void OnRefresh() {				
				mRefreshAsynTask = new RefreshDataAsynTask();
				mRefreshAsynTask.execute();
			}
		});
		
		//item点击
		mRecommendList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				_position=position;	
				Log.d(TAG, "position="+_position);
				mainhandler.sendEmptyMessage(0x400);
			}
		});
	}
	
	class RefreshDataAsynTask extends AsyncTask<Void , Void, Void>
	{
		protected Void doInBackground(Void... arg0) {
			recommendList=new DoHttp().getDyilyRecommend();
			if(recommendList.size()==0){
				return null;
			}
			try{
				for(DailyRecommend dr:recommendList){
					new DbDailyRecommend().removeAll();
					new DbDailyRecommend().insertData(dr);
				}
			}catch(SQLiteException e){
				new DbDailyRecommend().createTableDailyRecommend();
				for(DailyRecommend dr:recommendList){
					new DbDailyRecommend().insertData(dr);
				}
			}
			getRecommendListFromDb();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			mRecommendList.onRefreshComplete();
		}
	}
	

	/**处理消息*/
	private Handler mainhandler=new Handler(){
		public void handleMessage(Message msg){
			/**0x300加载到适配器*/
			if(msg.what==0x300){
				mRecommendList.setAdapter(sa);
			}
			/**0x400点击事件*/
			else if(msg.what==0x400){
				Intent intent=new Intent();
				intent.putExtra("open_daily_recommend_detail",recommendList.get(_position-1).getId());				 
				intent.setClass(context,DailyRecommendDetailActivity.class);
				startActivity(intent);
			}
		}			
	};

}
