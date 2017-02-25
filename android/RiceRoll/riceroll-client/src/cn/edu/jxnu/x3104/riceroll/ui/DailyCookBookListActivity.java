package cn.edu.jxnu.x3104.riceroll.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.bean.*;
import cn.edu.jxnu.x3104.riceroll.db.DbCondiment;
import cn.edu.jxnu.x3104.riceroll.db.DbDailyCookBook;
import cn.edu.jxnu.x3104.riceroll.db.DbFridge;
import cn.edu.jxnu.x3104.riceroll.service.DoHttp;
import cn.edu.jxnu.x3104.riceroll.service.DoLocalPicture;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.Toast;

public class DailyCookBookListActivity extends Activity{
	private final static String TAG = "DailyCookBookListActivity";
	
	private ListView listView;
	private List<CookBook> list;
	private int _position;
	private SimpleAdapter sa;
	private List<Map<String,Object>> items=new ArrayList<Map<String,Object>>();

	/**创建视图*/
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
			new DbDailyCookBook().createTableDailyCookBook();
		}catch(SQLException e){
			
		}
		if(!new DbDailyCookBook().isTodayNull()){
			setContentView(R.layout.daily_cookbook_null);
			return ;
		}
		setContentView(R.layout.daily_cookbook_list);
		init();
		getDailyCookBookFromDb();
		setOnItemClickListener();
	}
	

	/**获取数据库里的调料列表*/
	private void getDailyCookBookFromDb() {
		if(true){
			new Thread(new Runnable(){
				@Override
				public void run() {
					try{
						list=new DbDailyCookBook().getTodayCookBook();
					}catch(SQLException e){
						/*表不存在*/
						new DbDailyCookBook().createTableDailyCookBook();
						list=null;
					}					
					items.clear();
					if(list==null){
						return ;
					}
					for(CookBook cb:list){
						HashMap<String,Object> map=new HashMap<String,Object>();
						map.put("name",cb.getCookbook_name());
						Log.d(TAG, cb.getCookbook_name());
						map.put("text",cb.getCookbook_brief_introduction());
						String []links=cb.getCookbook_photo_link().split(";");
						Bitmap  b=new DoLocalPicture().getServerPicture("COOKBOOKIMAGE", links[0]);
						map.put("img",b);
						items.add(map);
					}
					mainhandler.sendEmptyMessage(0x300);
				}				
			}).start();
		}	
	}

	/**添加item点击事件监听*/
	private void setOnItemClickListener() {
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

	/**初始化组件*/
	private void init() {
		listView=(ListView)findViewById(R.id.daily_cookbook_listview);
		sa=new SimpleAdapter(
				this,
				items,
				R.layout.daily_cookbook_item,
				new String[]{"name","img","text"},
				new int[]{R.id.daily_cookbook_list_item_name,R.id.daily_cookbook_list_item_img,R.id.daily_cookbook_list_item_text});

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
				intent.putExtra("cookbook_detail",list.get(_position).getCookbook_name());
				intent.setClass(DailyCookBookListActivity.this,CookBookDetailActivity.class);
				startActivity(intent);
			}
		}			
	};
	
	public void exit(View v){
		this.finish();
	}
}