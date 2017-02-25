package cn.edu.jxnu.x3104.team3.jxnu_library.left_menu;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import cn.edu.jxnu.x3104.team3.jxnu_library.parameters.MyConstants;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
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

@SuppressLint("HandlerLeak")
public class Borrow_Now extends Activity {
	TextView back;
	private ListView borrow_ListView;
	private ArrayList<Borrow> borrowList;							//书刊列表
	private ArrayList<HashMap<String,Object>> borrow_list_items=new ArrayList<HashMap<String,Object>>();	//列表对应项目
	private Handler mainhandler;
	private Handler resulthandler;
	private int _position;
	String html=" ";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.left_borrow_now_list);
		back=(TextView)findViewById(R.id.borrow_now_back_to_main);
		borrow_ListView=(ListView) findViewById(R.id.list_borrow_now);
		borrow_ListView.setOnItemClickListener(new OnItemClickListener() {
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
				Borrow_Now.this.finish();
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}			
		});
		Intent intent=getIntent();
		html=intent.getStringExtra("codes");
		final SimpleAdapter sa=new SimpleAdapter(
				this,
				borrow_list_items, 
				R.layout.left_borrow_now_option,
				new String[]{"list_name","list_num","list_time_start", "list_time_end","list_address",
				"list_borrow_num"}, 
				new int[]{R.id.current_borrow_anthor,R.id.current_borrow_no,
						R.id.current_borrow_start,R.id.current_borrow_end,
						R.id.current_borrow_address,R.id.current_borrow_times} );
		mainhandler=new  Handler(){
			public void handleMessage(Message msg){
				if(msg.what==0x300){
					borrow_ListView.setAdapter(sa);
				} else if(msg.what==0x400){
					AlertDialog.Builder builder = new AlertDialog.Builder(Borrow_Now.this)
					.setTitle("书刊续借")
					.setMessage("您确定要续借该书吗");
					setPositiveButton(builder);
					setNegativeButton(builder)
					.create()
					.show();
				}
			}
		};	
		resulthandler = new Handler(){
			public void handleMessage(Message msg) {
				if(msg.what == 0x200){
					AlertDialog.Builder OK = new AlertDialog.Builder(Borrow_Now.this)
					.setTitle("续借成功")
					.setMessage("点击确定继续操作");
					setPositiveButton1(OK).create().show();
				}
				else if(msg.what == 0x300) {  
					AlertDialog.Builder FAIL = new AlertDialog.Builder(Borrow_Now.this)
					.setTitle("续借失败")
					.setMessage("您可能已经续借过本书或未到续借时间"); 
					setNeutralButton(FAIL).create().show();
				}  
				super.handleMessage(msg);
			}
		};
		new Thread(new Runnable(){
			public void run() {
				borrowList= get_borrowList();
				for(Borrow borrow_list_item:borrowList){
					HashMap<String,Object> map=new HashMap<String,Object>();
					map.put("list_name",borrow_list_item.book_name );
					map.put("list_num", "条码号："+borrow_list_item.book_num);
					map.put("list_time_start","借阅日期"+borrow_list_item.book_time_start);		
					map.put("list_time_end", "应还日期"+borrow_list_item.book_time_end);		
					map.put("list_borrow_num","续借次数"+ borrow_list_item.book_borrow_num);	
					map.put("list_address", borrow_list_item.book_address);	
					map.put("list_more_num",borrow_list_item.book_borrow_more_num);
					borrow_list_items.add(map);
				}
				mainhandler.sendEmptyMessage(0x300);
			}			
		}).start();
	}
	private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder){
		return builder.setPositiveButton("确定", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				new Thread(new Runnable(){
					public void run() {
						SharedPreferences sp= getSharedPreferences("login", Context.MODE_PRIVATE);	
						String cookie = sp.getString("cookie", "none");
						long time = System.currentTimeMillis();
						HttpPost httpRequest = new HttpPost(MyConstants.HTTP_JXNU_LIB_BORROW_MORE);// post连接
						List<NameValuePair> params = new ArrayList<NameValuePair>();// HTTP
						/* post参数列表 */
						params.add(new BasicNameValuePair("bar_code",borrowList.get(_position).book_borrow_more_num));
						params.add(new BasicNameValuePair("time",Long.toString(time)));
						/* HTTP连接开始 */
						try {
							// 发出HTTP request
							httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
							String sd = params.toString();
							Log.i("sd",sd);
							httpRequest.setHeader("Cookie", cookie);
							// 取得HTTP response
							HttpResponse httpResponse = new DefaultHttpClient()
							.execute(httpRequest); // 执行
							// 若状态码为200 OK
							if (httpResponse.getStatusLine().getStatusCode() == 200) { // 返回值正常
								// 获取返回的cookie
								StringBuffer sb = new StringBuffer();
								HttpEntity entity = httpResponse.getEntity();
								InputStream is = entity.getContent();
								BufferedReader br = new BufferedReader(
										new InputStreamReader(is, "UTF-8"));
								// 是读取要改编码的源，源的格式是UTF-8的，按源格式读进来，然后再对源码转换成想要的编码就行
								String data = "";
								while ((data = br.readLine()) != null) {
									sb.append(data);
								}
								String html = sb.toString();
								if(html.contains("不得续借"))
								{
									resulthandler.sendEmptyMessage(0x300);
								}
							} else {
								resulthandler.sendEmptyMessage(0x200);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		});
	}
	private AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder){
		return builder.setNegativeButton("取消",new OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which) {}
		});
	}
	private AlertDialog.Builder setPositiveButton1(AlertDialog.Builder OK){
		return OK.setPositiveButton("确定",new OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which) {}
		});			
	}
	private AlertDialog.Builder setNeutralButton(AlertDialog.Builder FAIL){
		return FAIL.setNeutralButton("确定",new OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which) {
			}
		});
	}
	public ArrayList<Borrow> get_borrowList(){
		ArrayList<Borrow> borrow_list=new ArrayList<Borrow>();
		String _name=" ";		//标题
		String _num=" ";		//条码号
		String _time_start=" ";	    //开始时间
		String _time_end=" ";	    //结束时间
		String _borrow_num=" ";  //续借次数	
		String _address=" ";           //馆藏地
		String _more_num=" ";            //续借号
		Document doc=Jsoup.parse(html);
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
					if(j==0) _num=td.text();
					if(j==1) _name=td.text();
					if(j==2) _time_start=td.text()+" ";
					if(j==3) _time_end=td.text()+" ";
					if(j==4) _borrow_num=td.text();
					if(j==5) _address=td.text();
					if(j==7) 
					{
						String temp=td.toString();
						int po = temp.indexOf("(");
						temp=temp.substring(po+2,po+9);
//						Log.i("onlink",temp);
						_more_num = temp;
					}
					j++;
				}
			}
			i++;
//			Log.i("_name",_name);
//			Log.i("_num",_num);
//			Log.i("_time_start",_time_start);
//			Log.i("_time_end",_time_end);
//			Log.i("_borrow_num",_borrow_num);
//			Log.i("_address",_address);
//			Log.i("_more_num",_more_num);
			if(i>1) {borrow_list.add(new Borrow(_name,_num,_time_start,_time_end,_borrow_num,_address,_more_num)); }
		}
		return borrow_list;
	}
	class Borrow { 		
		public String book_name;
		public String book_num;
		public String book_time_start;
		public String book_time_end;
		public String book_borrow_num;
		public String book_address;
		public String book_borrow_more_num;

		public Borrow(String name,String num,String time_start,
				String time_end,String borrow_num,String address,String more_num){
			book_name=name;			
			book_num=num;
			book_time_start=time_start;
			book_time_end=time_end;
			book_borrow_num=borrow_num;
			book_address=address;
			book_borrow_more_num=more_num;
		}
	}
}
