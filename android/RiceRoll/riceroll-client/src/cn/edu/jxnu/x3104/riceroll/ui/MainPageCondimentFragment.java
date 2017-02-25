package cn.edu.jxnu.x3104.riceroll.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.Toast;
import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.bean.Condiment;
import cn.edu.jxnu.x3104.riceroll.db.DbDailyRecommend;
import cn.edu.jxnu.x3104.riceroll.db.DbCondiment;
import cn.edu.jxnu.x3104.riceroll.service.DoHttp;
import cn.edu.jxnu.x3104.riceroll.service.DoLocalPicture;
import cn.edu.jxnu.x3104.riceroll.util.MyListView;
import cn.edu.jxnu.x3104.riceroll.util.MyListView.IOnRefreshListener;

public class MainPageCondimentFragment extends Fragment{
	private final static String TAG = "MainPageCondimentFragment";

	private Builder alertBulider;
	private AlertDialog alert;
	private Context context;
	
	private RefreshDataAsynTask mRefreshAsynTask;

	private MyListView listView; 
	private List<Condiment> list;
	private int _position;
	private SimpleAdapter sa;
	private List<Map<String,Object>> items=new ArrayList<Map<String,Object>>();

	public void SetContext(Context c){
		context=c;
		alertBulider=new Builder(context);
		alert=alertBulider.create();
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View  view=  inflater.inflate(R.layout.mainpage_condiment, null);	

		init(view);
		setOnItemClickListener();
		getCondimentListFromDb();
		return view;
	}
	
	/**开线程获取数据库里的食材列表*/
	private void getCondimentListFromDb() {
		new Thread(new Runnable(){
			public void run() {
				if(list==null){
					try{
						list=new DbCondiment().getAllData();
					}catch(SQLException e){
						/*表不存在*/
						new DbCondiment().createTableCondiment();
						list=new DbCondiment().getAllData();
					}
				}				
				items.clear();
				for(Condiment c:list){
					HashMap<String,Object> map=new HashMap<String,Object>();
					map.put("name",c.getCondiment_name());
					map.put("ka",c.getCondiment_calories()+"卡");
					String []links=c.getCondiment_photo_link().split(";");
					Bitmap  b=new DoLocalPicture().readFromSD("CONDIMENTIMAGE", links[0]);
					map.put("img",b);
					items.add(map);
				}
				mainhandler.sendEmptyMessage(0x300);
			}			
		}).start();
	}

	/**添加item点击事件监听*/
	private void setOnItemClickListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				_position=position;		
				Log.d(TAG, "position="+_position);
				mainhandler.sendEmptyMessage(0x400);
			}
		});

		//下拉刷新
		listView.setOnRefreshListener(new IOnRefreshListener() {			
			public void OnRefresh() {				
				mRefreshAsynTask = new RefreshDataAsynTask();
				mRefreshAsynTask.execute();
			}
		});
	}

	/**初始化组件*/
	private void init(View view) {
		listView=(MyListView)view.findViewById(R.id.mainpage_mylistview_condiment);
		sa=new SimpleAdapter(
				context,
				items,
				R.layout.mainpage_condiment_mylistview_item,
				new String[]{"img","name","ka"},
				new int[]{R.id.mainpage_condiment_img,R.id.mainpage_condiment_name,R.id.mainpage_condiment_ka});

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

	/**异步加载信息*/
	class RefreshDataAsynTask extends AsyncTask<Void , Void, Void>
	{
		protected Void doInBackground(Void... arg0) {
			list=new DoHttp().updateCondiment(context);
			if(list.size()==0){
				return null;
			}
			try{
				new DbCondiment().removeAllData();
				for(Condiment c:list){
					new DbCondiment().insertData(c);
				}
			}catch(SQLiteException e){
				new DbDailyRecommend().createTableDailyRecommend();
				for(Condiment c:list){
					new DbCondiment().insertData(c);
				}
			}
			getCondimentListFromDb();
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
				intent.putExtra("open_condiment_detail",list.get(_position-1).getCondiment_id());
				intent.setClass(context,MainPageCondimentDetailActivity.class);
				startActivity(intent);
			}
		}
	};
}
