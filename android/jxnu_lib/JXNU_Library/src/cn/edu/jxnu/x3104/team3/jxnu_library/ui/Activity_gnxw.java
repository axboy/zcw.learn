package cn.edu.jxnu.x3104.team3.jxnu_library.ui;

import java.util.ArrayList;
import java.util.HashMap;
import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import cn.edu.jxnu.x3104.team3.jxnu_library.parameters.Gnxw;
import cn.edu.jxnu.x3104.team3.jxnu_library.services.DoHttp;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

@SuppressLint("HandlerLeak")
public class Activity_gnxw extends Activity{

	private ListView gnxw_ListView;
	private ArrayList<Gnxw> gnxwList;							//馆内新闻列表
	private ArrayList<HashMap<String,Object>> gnxw_list_items=new ArrayList<HashMap<String,Object>>();	//列表对应项目
	private Handler mainhandler;
	private int _position;
	
	//创建视图
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_list);
		gnxw_ListView=(ListView) findViewById(R.id.list_gnxw);	
		gnxw_ListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				_position=position;
				
				new Thread(new Runnable(){
					public void run() {
						mainhandler.sendEmptyMessage(0x400);
					}					
				}).start();
			}		
		});
		 final SimpleAdapter sa=new SimpleAdapter(
					this,
					gnxw_list_items, 
					R.layout.news_list_option,
					new String[]{"list_title","list_date" }, 
					new int[]{R.id.list_title,R.id.list_date} );
		mainhandler=new  Handler(){
			public void handleMessage(Message msg){
				if(msg.what==0x300){
					gnxw_ListView.setAdapter(sa);
				} else if(msg.what==0x400){
					Intent intent=new Intent();
					intent.putExtra("qwe", gnxwList.get(_position).news_url);
					Log.i("1234657987",  gnxwList.get(_position).news_url);
					intent.setClass(Activity_gnxw.this,Activity_gnxw_detail.class);
					startActivity(intent);
				}
			}
		};	
		new Thread(new Runnable(){
			public void run() {
				gnxwList=new DoHttp().get_gnxwList();
				for(Gnxw gnxw_list_item:gnxwList){
					HashMap<String,Object> map=new HashMap<String,Object>();
					map.put("list_title", gnxw_list_item.news_title);
					Log.d("list_title",gnxw_list_item.news_title.toString());
					map.put("list_date", gnxw_list_item.news_date);		
					Log.d("list_date",gnxw_list_item.news_date.toString());
					gnxw_list_items.add(map);
				}
				mainhandler.sendEmptyMessage(0x300);
			}
		}).start();
	}
}