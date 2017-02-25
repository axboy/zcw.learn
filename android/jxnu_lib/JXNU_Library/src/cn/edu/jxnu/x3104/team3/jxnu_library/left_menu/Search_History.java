package cn.edu.jxnu.x3104.team3.jxnu_library.left_menu;

import java.util.ArrayList;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import cn.edu.jxnu.x3104.team3.jxnu_library.parameters.MyConstants;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class Search_History extends Activity {
	class History { 		
		public String history_url;
		public String history_title;
		public String history_date;
		public History(String url,String title,String date){
			history_url=url;			
			history_title=title;	
			history_date=date;		
		}
	}
	private ListView history_ListView;
	private ArrayList<History> historyList;							
	private ArrayList<HashMap<String,Object>> history_list_items=new ArrayList<HashMap<String,Object>>();	//列表对应项目
	private Handler mainhandler;
	private int _position;
	TextView back;
	TextView search_date_history;
	TextView search_content_history;
	String html;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_history);
		history_ListView=(ListView) findViewById(R.id.search_history_listview);	
		init();
		DisplayMetrics dMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dMetrics);
		int ScreenWIDTH = dMetrics.widthPixels;
		LinearLayout.LayoutParams sp_params = new LinearLayout.LayoutParams(
               LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		sp_params.width = 3*ScreenWIDTH/5;
		search_date_history.setLayoutParams(sp_params);
		sp_params.width = 2*ScreenWIDTH/5;
		search_content_history.setLayoutParams(sp_params);
		Intent intent=getIntent();
		html=intent.getStringExtra("codes");
		history_ListView.setOnItemClickListener(new OnItemClickListener() {
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
		back.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Search_History.this.finish();
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}			
		});
		 final SimpleAdapter sa=new SimpleAdapter(
					this,
					history_list_items, 
					R.layout.search_history_option,
					new String[]{"list_date" ,"list_title"}, 
					new int[]{R.id.search_history_date,R.id.search_history_title} );
		mainhandler=new  Handler(){
			public void handleMessage(Message msg){
				if(msg.what==0x300){
					history_ListView.setAdapter(sa);
				} else if(msg.what==0x400){
					Intent intent=new Intent();
					intent.putExtra("history_url", historyList.get(_position).history_url);
					Log.i("history_url", historyList.get(_position).history_url.toString());
					intent.setClass(Search_History.this,Search_Result_History.class);
					startActivity(intent);
				}
			}
		};	
		new Thread(new Runnable(){
			public void run() {
				historyList= get_historyList();
				for(History history_list_item:historyList){
					HashMap<String,Object> map=new HashMap<String,Object>();
					map.put("list_title", history_list_item.history_title);
					map.put("list_date", history_list_item.history_date);		
					map.put("list_url", history_list_item.history_url);		
					history_list_items.add(map);
				}
				mainhandler.sendEmptyMessage(0x300);
			}			
		}).start();
	}
	private void init() {
		 back = (TextView)findViewById(R.id.back_to_main7);
		 search_date_history = (TextView)findViewById(R.id.search_date_history);
		 search_content_history = (TextView)findViewById(R.id.search_content_history);		
	}
	public ArrayList<History> get_historyList(){
		ArrayList<History> history_list=new ArrayList<History>();
		String _title=" ";		//标题
		String _url=" ";		//地址
		String _date=" ";	    //时间
		Document doc=Jsoup.parse(html);	//将馆内新闻页存进doc
		Element div_element=doc.select("table").first();	
		Elements trs=div_element.select("tr");				//通过tr分取数据
		int i=0;
		for(Element tr:trs){
			if(i!=0)
			{
				Elements tds=tr.getElementsByTag("td");
				int j=0;
				for(Element td:tds)
				{
					if(j==1) _date=td.text()+"            ";
					if(j==2) _title=td.text()+" ";
					if(j==2) 
					{
						_url=td.select("a").attr("href");
						_url=_url.substring(2);
						_url=MyConstants.HTTP_JXNU_LIB_HISTORY_SEARCH+_url;
						}
					j++;
				}
			}
			i++;
			Log.i("_title",_title);
			Log.i("_url",_url);
			Log.i("_date",_date);
			if(i>1) history_list.add(new History(_url,_title,_date)); 
		}
		return history_list;
	}
}

