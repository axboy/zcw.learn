package cn.edu.jxnu.x3104.team3.jxnu_library.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import cn.edu.jxnu.x3104.team3.jxnu_library.parameters.MyConstants;
import cn.edu.jxnu.x3104.team3.jxnu_library.parameters.SearchResult;
import cn.edu.jxnu.x3104.team3.jxnu_library.services.DoSearch;

@SuppressLint("HandlerLeak")
public class Search_result extends Activity{
	TextView back;
	
	private DoSearch dosearch;
	private ListView Result_ListView;
	private ArrayList<SearchResult> ResultList=new ArrayList<SearchResult>();							//搜索结果列表
	private ArrayList<HashMap<String,Object>> result_list_items=new ArrayList<HashMap<String,Object>>();;	//列表对应项目
	private Handler mainhandler;
	private int _position;
	public String _url="";
	public String _title="";			//标题、书名
	public String _type="";				//图书类型
	public String _guan_quantity="";	//馆藏副本
	public String _ke_quantity="";		//可借副本
	public String _press="";
	public String _author="";			//作者
	public String _publisher="";		//出版社
	ProgressDialog proDialog;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_result);
		Result_ListView=(ListView) findViewById(R.id.list_result);
		back=(TextView)findViewById(R.id.search_result_back_to_main);
		//点击监听事件
		proDialog = ProgressDialog.show(Search_result.this,"请稍候","正在检索...", true, true); 
		Result_ListView.setOnItemClickListener(new OnItemClickListener() {
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

		//返回键点击事件
		back.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Search_result.this.finish();
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}			
		});
		//定义适配器
		final SimpleAdapter sa=new SimpleAdapter(
				this,
				result_list_items, 
				R.layout.search_result_option,
				new String[]{"search_list_title","search_list_option",
						"search_list_author","search_list_collected",
						"search_list_publisher","search_list_lend" }, 
						new int[]{R.id.search_list_title,R.id.search_list_option,
						R.id.search_list_author,R.id.search_list_collected,
						R.id.search_list_publisher,R.id.search_list_lend});
		//数据适配及子项跳转
		mainhandler=new  Handler(){
			public void handleMessage(Message msg){
				if(msg.what==0x200)
				{
					Result_ListView.setAdapter(sa);
					proDialog.dismiss();
				}
				else if(msg.what==0x400){
					Intent intent=new Intent();
					intent.putExtra("qwe", ResultList.get(_position).book_url);
					intent.setClass(Search_result.this,Search_detail.class);
					startActivity(intent);
				}
			}
		};
		//抓取数据
		new Thread(new Runnable(){
			public void run() {
				Intent intent=getIntent();
				String search_type=intent.getStringExtra("type");
				String search_content=intent.getStringExtra("cont");
				String search_option=intent.getStringExtra("option");
				String search_mode=intent.getStringExtra("mode");
				String search_sort=intent.getStringExtra("sort");
				Log.d("顶部搜索选项",search_type);
				Log.d("搜索内容",search_content);
				Log.d("检索条件",search_option);
				Log.d("检索模式",search_mode);
				Log.d("结果排序",search_sort);
				dosearch = new DoSearch();
				String result = dosearch.post_search(search_type,search_content,search_option,search_mode,search_sort);
				if(result.contains("本馆没有您检索的馆藏书目")){
					new Thread(new Runnable(){
						public void run(){
							Intent intent=new Intent();
							intent.putExtra("title", "搜索结果");
							intent.setClass(Search_result.this,Search_result_no_content.class);
							startActivity(intent);
							Search_result.this.finish();
						}
					}).start();
				} else{
					Document doc = Jsoup.parse(result);
					Element div = doc.getElementById("book_content");
					Elements divs = div.select("[class=list_books]");
					for(Element links:divs){
						String temp="";
						_url=links.select("a").attr("href");					
						_url=MyConstants.HTTP_JXNU_LIB_SEARCH+_url;
						Log.i("_url",_url);		//获得链接
						_title=links.select("a").first().text();
						Log.i("_title",_title);	//获得书名
						_type=links.select("span[class=doc_type_class]").first().text();
						Log.i("_type",_type);	//图书类型
						temp=links.select("p").first().toString();
						temp=temp.replace("<br />", "^");
						temp=temp.replace("<p>", "");
						temp=temp.replace("</p>", "");
						temp=temp.replace("<span>", "");
						temp=temp.replace("</span>", "");
						temp=temp.replace("<strong>", "");
						temp=temp.replace("</strong>", "");
						temp=temp.replace("&nbsp;", "");
						_guan_quantity = temp.substring(1,8);
						_ke_quantity = temp.substring(10,17);
						Log.i("guan_quantity",_guan_quantity);
						Log.i("ke_quantity",_ke_quantity);
						temp = temp.substring(18);
						Log.i("_auther&publisher",temp);
						int t=temp.indexOf("^");						
						_author=temp.substring(0,t);
						Log.i("_author",_author);
						_publisher=temp.substring(t+2);
						Log.i("_publisher",_publisher);
						ResultList.add(new SearchResult(_url,_title,_type,_author,_guan_quantity,_publisher,_ke_quantity)); 
					}
					//封装数据
					for(SearchResult result_list_item:ResultList){
						HashMap<String,Object> map=new HashMap<String,Object>();
						map.put("search_list_title",result_list_item.book_title);
						map.put("search_list_option", result_list_item.book_type);
						map.put("search_list_author", result_list_item.book_author);
						map.put("search_list_collected", result_list_item.book_guan_quantity);
						map.put("search_list_publisher",result_list_item.book_publish);
						map.put("search_list_lend", result_list_item.book_ke_quantity);
						result_list_items.add(map);
					}
					mainhandler.sendEmptyMessage(0x200);
				}
			}
		}).start();
	}
}