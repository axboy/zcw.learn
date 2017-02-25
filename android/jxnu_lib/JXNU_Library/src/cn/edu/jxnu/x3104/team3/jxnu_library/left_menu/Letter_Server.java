package cn.edu.jxnu.x3104.team3.jxnu_library.left_menu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import cn.edu.jxnu.x3104.team3.jxnu_library.SlipButton;
import cn.edu.jxnu.x3104.team3.jxnu_library.SlipButton.OnChangedListener;
import cn.edu.jxnu.x3104.team3.jxnu_library.parameters.MyConstants;
import cn.edu.jxnu.x3104.team3.jxnu_library.services.DoConnection;

@SuppressLint("HandlerLeak")
public class Letter_Server extends Activity{
	TextView back;
	Button enter;
	Button cancel;
	EditText tele_num;
	String info=" ";
	String oldMobile="";
	CheckBox letter_book_arrive;
	CheckBox letter_book_timeout;
	CheckBox letter_book_timeout_back;
	CheckBox letter_book_borrow_achieve;
	CheckBox letter_book_back_achieve;
	CheckBox letter_book_timeout_fine;
	CheckBox letter_book_illegal;
	private Handler mainhandler;

	SlipButton sb1 = null;
	SlipButton sb2 = null;
	SlipButton sb3 = null;
	SlipButton sb4 = null;
	SlipButton sb5 = null;
	SlipButton sb6= null;
	SlipButton sb7 = null;

	boolean sb01=false;
	boolean sb02=false;
	boolean sb03=false;
	boolean sb04=false;
	boolean sb05=false;
	boolean sb06=false;
	boolean sb07=false;

	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.letter_server);
		init();
        setListener();
		new Thread(new Runnable(){
			public void run() {
				info = new DoConnection().gethtmlcode(Letter_Server.this, MyConstants.HTTP_JXNU_LIB_INFO);
				Document div = Jsoup.parse(info);
				Element table = div.select("[style=padding:5px]").first();
				//				Log.i("table",table.toString());
				Elements trs = table.select("tr");
				//				Log.i("trs",trs.toString());
				for(Element tr:trs)
				{
					Elements tds = tr.select("td");
					Log.i("tds",tds.toString());
					for(Element td:tds)
					{
						int i;
						String temp=td.toString();	
						temp = temp.replace("</span>", "");
						temp = temp.replace("</td>", "");
						if(temp.contains("手机号码"))
						{
							i = temp.indexOf("机");
							oldMobile = temp.substring(i+4);
							Log.i("num",oldMobile);
						}
					}
				}
			}
		}).start();
		mainhandler = new Handler(){
			public void handleMessage(Message msg) {
				if(msg.what == 0x200){
					AlertDialog.Builder OK = new AlertDialog.Builder(Letter_Server.this)
					.setTitle("短信服务")
					.setMessage("操作成功");
					setPositiveButton1(OK).create().show();
				}
				else if(msg.what == 0x300) {  
					AlertDialog.Builder FAIL = new AlertDialog.Builder(Letter_Server.this)
					.setTitle("短信服务")
					.setMessage("操作失败"); 
					setNeutralButton(FAIL).create().show();
				}  
				super.handleMessage(msg);
			}
		};
		back.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Letter_Server.this.finish();
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}			
		});
		enter.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Animation inputbox_shack = AnimationUtils.loadAnimation(
						Letter_Server.this, R.anim.loginui_inputbox_warningshack);
				if (tele_num.getText().toString().equals("")) {
					tele_num.startAnimation(inputbox_shack);
				}else if(tele_num.getText().toString().length()!=11){
					tele_num.startAnimation(inputbox_shack);
				}else
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(Letter_Server.this)
					.setTitle("短信服务").setMessage("您确定要订购吗？");
					setPositiveButton(builder);
					setNegativeButton(builder).create().show();
				}
			}
		});    
		cancel.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(Letter_Server.this)
				.setTitle("短信服务").setMessage("您确定要退订吗？");
				setPositiveButton2(builder);
				setNegativeButton(builder).create().show();
			}
		});
	}
	private void setListener()
	{
		sb1.SetOnChangedListener(new OnChangedListener()
		{
			public void OnChanged(boolean CheckState)
			{
				if(CheckState){
					sb01=true;
				}else{
					sb01=false;
				}
			}
		});
		sb2.SetOnChangedListener(new OnChangedListener()
		{
			public void OnChanged(boolean CheckState)
			{
				if(CheckState){
					sb02=true;
				}else{
					sb02=false;
				}
			}
		});
		sb3.SetOnChangedListener(new OnChangedListener()
		{
			public void OnChanged(boolean CheckState)
			{
				if(CheckState){
					sb03=true;
				}else{
					sb03=false;
				}
			}
		});
		sb4.SetOnChangedListener(new OnChangedListener()
		{
			public void OnChanged(boolean CheckState)
			{
				if(CheckState){
					sb04=true;
				}else{
					sb04=false;
				}
			}
		});
		sb5.SetOnChangedListener(new OnChangedListener()
		{
			public void OnChanged(boolean CheckState)
			{
				if(CheckState){
					sb05=true;
				}else{
					sb05=false;
				}
			}
		});
		sb6.SetOnChangedListener(new OnChangedListener()
		{
			public void OnChanged(boolean CheckState)
			{
				if(CheckState){
					sb06=true;
				}else{
					sb06=false;
				}
			}
		});
		sb7.SetOnChangedListener(new OnChangedListener()
		{
			public void OnChanged(boolean CheckState)
			{
				if(CheckState){
					sb07=true;
				}else{
					sb07=false;
				}
			}
		});
	}

	private void init() {
		back = (TextView)findViewById(R.id.letter_server_back_to_main);	
		enter = (Button)findViewById(R.id.letter_server_enter);
		cancel = (Button)findViewById(R.id.letter_server_cancel);
		tele_num = (EditText)findViewById(R.id.letter_server_telenum);
		sb1 = (SlipButton) findViewById(R.id.sb1);
		sb2 = (SlipButton) findViewById(R.id.sb2);
		sb3= (SlipButton) findViewById(R.id.sb3);
		sb4= (SlipButton) findViewById(R.id.sb4);
		sb5= (SlipButton) findViewById(R.id.sb5);
		sb6= (SlipButton) findViewById(R.id.sb6);
		sb7= (SlipButton) findViewById(R.id.sb7);
		sb1.setCheck(false);
		sb2.setCheck(false);
		sb3.setCheck(false);
		sb4.setCheck(false);
		sb5.setCheck(false);
		sb6.setCheck(false);
		sb7.setCheck(false);
	}
	private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder){
		return builder.setPositiveButton("确定", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				new Thread(new Runnable(){
					public void run() {
						SharedPreferences sp= getSharedPreferences("login", Context.MODE_PRIVATE);	
						String cookie = sp.getString("cookie", "none");
						HttpPost httpRequest = new HttpPost(MyConstants.HTTP_JXNU_LIB_LETTER);// post连接
						List<NameValuePair> params = new ArrayList<NameValuePair>();// HTTP
						/* post参数列表 */
						params.add(new BasicNameValuePair("checkOrder[]", "0010"));
						params.add(new BasicNameValuePair("checkOrder[]", "0013"));
						if(sb01)
						{params.add(new BasicNameValuePair("checkOrder[]", "0001"));}
						if(sb02)
						{params.add(new BasicNameValuePair("checkOrder[]", "0002"));}	
						if(sb03)
						{params.add(new BasicNameValuePair("checkOrder[]", "0007"));}
						if(sb04)
						{params.add(new BasicNameValuePair("checkOrder[]", "0011"));}
						if(sb05)
						{params.add(new BasicNameValuePair("checkOrder[]", "0012"));}
						if(sb06)
						{params.add(new BasicNameValuePair("checkOrder[]", "0016"));}
						if(sb07)
						{params.add(new BasicNameValuePair("checkOrder[]", "0017"));}
						params.add(new BasicNameValuePair("mobile", tele_num.getText().toString()));     //新手机号码
						params.add(new BasicNameValuePair("oldMobile", oldMobile));                                  //旧手机号码
						params.add(new BasicNameValuePair("type", "modify"));                                             //预定
						params.add(new BasicNameValuePair("random", ""+Math.random()));                     //随机数
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
								@SuppressWarnings("unused")
								String html = sb.toString();
//								Log.i("html",html);
								mainhandler.sendEmptyMessage(0x200);					
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		});
	}
	private AlertDialog.Builder setPositiveButton2(AlertDialog.Builder builder){
		return builder.setPositiveButton("确定", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				new Thread(new Runnable(){
					public void run() {
						SharedPreferences sp= getSharedPreferences("login", Context.MODE_PRIVATE);	
						String cookie = sp.getString("cookie", "none");
						HttpPost httpRequest = new HttpPost(MyConstants.HTTP_JXNU_LIB_LETTER);// post连接
						List<NameValuePair> params = new ArrayList<NameValuePair>();// HTTP
						/* post参数列表 */
						params.add(new BasicNameValuePair("type", "logout"));                                             //预定
						params.add(new BasicNameValuePair("random", ""+Math.random()));                     //随机数
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
								@SuppressWarnings("unused")
								String html = sb.toString();
//								Log.i("html",html);
								mainhandler.sendEmptyMessage(0x200);							
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
			public void onClick(DialogInterface dialog, int which) {

			}
		});
	}
	private AlertDialog.Builder setNeutralButton(AlertDialog.Builder FAIL){
		return FAIL.setNeutralButton("确定",new OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which) {

			}
		});
	}
}
