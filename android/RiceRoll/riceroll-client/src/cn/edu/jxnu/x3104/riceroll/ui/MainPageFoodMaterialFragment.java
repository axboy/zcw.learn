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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter.ViewBinder;
import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.bean.DailyRecommend;
import cn.edu.jxnu.x3104.riceroll.bean.FoodMaterial;
import cn.edu.jxnu.x3104.riceroll.db.DbDailyRecommend;
import cn.edu.jxnu.x3104.riceroll.db.DbFoodMaterial;
import cn.edu.jxnu.x3104.riceroll.service.DoHttp;
import cn.edu.jxnu.x3104.riceroll.service.DoLocalPicture;
import cn.edu.jxnu.x3104.riceroll.ui.MainPageMainFragment.RefreshDataAsynTask;
import cn.edu.jxnu.x3104.riceroll.util.MyListView;
import cn.edu.jxnu.x3104.riceroll.util.MyListView.IOnRefreshListener;

public class MainPageFoodMaterialFragment extends Fragment{
	private final static String TAG = "MainPageFindFragment";

	private Builder alertBulider;
	private AlertDialog alert;
	private Context context;

	private RefreshDataAsynTask mRefreshAsynTask;

	private MyListView listView; 
	private List<FoodMaterial> list;
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
		View  view=  inflater.inflate(R.layout.mainpage_food_material, null);	

		init(view);
		setOnItemClickListener();
		getFoodMaterialListFromDb();
		return view;
	}

	/**获取数据库里的食材列表*/
	private void getFoodMaterialListFromDb() {
		try{
			list=new DbFoodMaterial().getAllData();
		}catch(SQLException e){
			/*表不存在*/
			new DbFoodMaterial().createTableFoodMaterial();
			list=new DbFoodMaterial().getAllData();
		}			
		items.clear();
		for(FoodMaterial foodMaterial:list){
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("fm_name",foodMaterial.getFoodMaterial_name());
			map.put("fm_ka",foodMaterial.getFoodMaterial_calories()+"卡");
			String []links=foodMaterial.getFoodMaterial_photo_link().split(";");
			Bitmap  b=new DoLocalPicture().readFromSD("FOODMATERIALIMAGE", links[0]);
			map.put("fm_img",b);
			items.add(map);
		}
		mainhandler.sendEmptyMessage(0x300);
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
		listView=(MyListView)view.findViewById(R.id.mainpage_mylistview_food_material);
		sa=new SimpleAdapter(
				context,
				items,
				R.layout.mainpage_food_material_mylistview_item,
				new String[]{"fm_img","fm_name","fm_ka"},
				new int[]{R.id.mainpage_foodmaterial_img,R.id.mainpage_foodmaterial_name,R.id.mainpage_foodmaterial_ka});

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
			list=new DoHttp().updateFoodMaterial(context);
			if(list.size()==0||list==null){
				return null;
			}
			try{
				new DbFoodMaterial().deleteAllData();
				for(FoodMaterial fm:list){
					new DbFoodMaterial().insertData(fm);
				}
			}catch(SQLiteException e){
				new DbFoodMaterial().createTableFoodMaterial();
				for(FoodMaterial fm:list){
					new DbFoodMaterial().insertData(fm);
				}
			}
			getFoodMaterialListFromDb();
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
				intent.putExtra("open_foodMaterial_detail",list.get(_position-1).getFoodMaterial_id());
				intent.setClass(context,MainPageFoodMaterialDetailActivity.class);
				startActivity(intent);
			}
		}
	};
}
