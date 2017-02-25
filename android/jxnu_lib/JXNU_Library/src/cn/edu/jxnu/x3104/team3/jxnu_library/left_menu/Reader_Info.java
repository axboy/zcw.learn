//个人信息
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
import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import cn.edu.jxnu.x3104.team3.jxnu_library.parameters.MyConstants;
import cn.edu.jxnu.x3104.team3.jxnu_library.ui.Activity_Main_unlogin;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class Reader_Info extends Activity{
	private String name=" ";
	private String sex=" ";
	private String start=" ";
	private String end = " ";
	private String depart = " ";
	private String num = " ";
	private String mail = " ";
	private String per =" ";
	private String illegal =" ";
	private String fine = " ";
	private String count = " ";
	TextView back_to_main1;
	TextView change_password;
	TextView right1;
	TextView right2;
	TextView right3;
	TextView right4;
	TextView right6;
	TextView right7;
	TextView right8;
	TextView right9;
	TextView right10;
	TextView right11;
	EditText left5;
	EditText  right12;
	RelativeLayout call_info;
	RelativeLayout time_info;
	RelativeLayout borrow_info;
	RelativeLayout per_info;
	RelativeLayout password_info;
	private Handler mainhandler;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reader_info);
		init();
		DisplayMetrics dMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dMetrics);
		int ScreenWIDTH = dMetrics.heightPixels;
		LinearLayout.LayoutParams sp_params = new LinearLayout.LayoutParams(
               LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		sp_params.height = ScreenWIDTH/7;
		call_info.setLayoutParams(sp_params);
		borrow_info.setLayoutParams(sp_params);
		time_info.setLayoutParams(sp_params);
		per_info.setLayoutParams(sp_params);
		Intent intent=getIntent();
		String html=intent.getStringExtra("codes");
		Document div = Jsoup.parse(html);
		Element table = div.select("[style=padding:5px]").first();
//		Log.i("table",table.toString());
		Elements trs = table.select("tr");
		Log.i("trs",trs.toString());
		for(Element tr:trs)
		{
			Elements tds = tr.select("td");
			Log.i("tds",tds.toString());
			for(Element td:tds)
			{
				int i,j ;
				String temp=td.toString();	
				temp = temp.replace("</span>", "");
				temp = temp.replace("</td>", "");
				if(temp.contains("姓名"))
				{
					i = temp.indexOf("姓");
					name = temp.substring(i+3);
					Log.i("name",name);
				}else if(temp.contains("性别"))
				{
					i = temp.indexOf("性");
					sex = temp.substring(i+3);
					Log.i("sex",sex);
				}
				else if(temp.contains("工作单位"))
				{
					i = temp.indexOf("作");
					depart = temp.substring(i+4);
					Log.i("depart",depart);
				}
				else if(temp.contains("手机号码"))
				{
					i = temp.indexOf("机");
					num = temp.substring(i+4);
					Log.i("num",num);
				}
				else if(temp.contains("验证"))
				{
					temp = temp.replace("<td>", "");
					temp = temp.replace("</span>","");
					i = temp.indexOf("f");
					j = temp.indexOf("：");
					mail = temp.substring(j+1,i-2)+" ";
					Log.i("mail",mail);
				}
				else if(temp.contains("生效日期"))
				{
					i = temp.indexOf("期");
					start = temp.substring(i+2);
					Log.i("start",start);
				}
				else if(temp.contains("失效日期"))
				{
					i = temp.indexOf("期");
					end = temp.substring(i+2);
					Log.i("end",end);
				}
				else if(temp.contains("违章状态"))
				{
					i = temp.indexOf("态");
					illegal = temp.substring(i+2);
					Log.i("illegal",illegal);
				}
				else if(temp.contains("欠款状态"))
				{
					i = temp.indexOf("态");
					fine = temp.substring(i+2);
					Log.i("fine",fine);
				}
				else if(temp.contains("累计借书"))
				{
					i = temp.indexOf("书");
					count = temp.substring(i+2);
					Log.i("count",count);
				}
				else if(temp.contains("借阅等级"))
				{
					i = temp.indexOf("级");
					per = temp.substring(i+2);
					Log.i("per",per);
				}			
			}
		}		
			right1.setText(name);
			right2.setText(sex+"    "+depart);
			right3.setText("手机："+num);
			right4.setText("邮箱："+mail);
			right6.setText("累计借书："+count);
			right7.setText("权限等级："+per);
			right8.setText("违章状态："+illegal);
			right9.setText("欠费状态："+fine);
			right10.setText("生效时间："+start);
			right11.setText("失效时间："+end);
			mainhandler = new Handler(){
				public void handleMessage(Message msg) {
					if(msg.what == 0x200){
						AlertDialog.Builder OK = new AlertDialog.Builder(Reader_Info.this)
						.setTitle("修改成功")
						.setMessage("您需要重新登录");
						setPositiveButton1(OK).create().show();
					}
					else if(msg.what == 0x300) {  
						AlertDialog.Builder FAIL = new AlertDialog.Builder(Reader_Info.this)
						.setTitle("修改失败")
						.setMessage("请检查密码格式"); 
						setNeutralButton(FAIL).create().show();
					}  
					super.handleMessage(msg);
				}
			};
			per_info.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v) {
					Intent intent = new Intent ();
					intent.setClass(Reader_Info.this,Reader_Permission.class);
					startActivity(intent);
				}			
			});
			call_info.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v) {
					Intent intent = new Intent ();
					intent.setClass(Reader_Info.this,Change_Contact.class);
					startActivity(intent);
				}			
			});
			back_to_main1.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v) {
					Reader_Info.this.finish();
					overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
				}			
			});
			change_password.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v) {
					Animation inputbox_shack = AnimationUtils.loadAnimation(
							Reader_Info.this, R.anim.loginui_inputbox_warningshack);
				     if (left5.getText().toString().equals("")
							&& right12.getText().toString().equals("")) {// 输入框不为空
						left5.startAnimation(inputbox_shack);
						right12.startAnimation(inputbox_shack);
					} else if (left5.getText().toString().equals("")) {
						left5.startAnimation(inputbox_shack);
					} else if (right12.getText().toString().equals("")) {
						right12.startAnimation(inputbox_shack);
					}else if(!left5.getText().toString().equals(right12.getText().toString()))
					{
						Toast.makeText(Reader_Info.this, "俩次输入的密码不一致",  Toast.LENGTH_SHORT).show();  
					}else
					{
						AlertDialog.Builder builder = new AlertDialog.Builder(Reader_Info.this)
													.setTitle("修改密码").setMessage("您确定要修改密码吗");
						setPositiveButton(builder);
						setNegativeButton(builder).create().show();
					}
				}
			});     
	}
	private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder){
		return builder.setPositiveButton("确定", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				new Thread(new Runnable(){
					public void run() {
						SharedPreferences sp= getSharedPreferences("login", Context.MODE_PRIVATE);	
						String cookie = sp.getString("cookie", "none");
						HttpPost httpRequest = new HttpPost(MyConstants.HTTP_JXNU_LIB_CHANGE_PASSWORD);// post连接
						List<NameValuePair> params = new ArrayList<NameValuePair>();// HTTP
						/* post参数列表 */
						params.add(new BasicNameValuePair("old_passwd", sp.getString("pwd", "")));
						params.add(new BasicNameValuePair("new_passwd", right12.getText().toString()));
						params.add(new BasicNameValuePair("chk_passwd", left5.getText().toString()));		
						params.add(new BasicNameValuePair("submit1", "确定"));
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
							Log.i("修改成功",html);
							if(html.contains("修改成功"))
							{
								Log.i("havedone","1");
								mainhandler.sendEmptyMessage(0x200);
							}
						} else {
							Log.i("havedone","1");
								mainhandler.sendEmptyMessage(0x300);
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
				new Thread(new Runnable(){
					public void run() {
						Intent intent = new Intent (Reader_Info.this,Activity_Main_unlogin.class);			
						startActivity(intent);
						Reader_Info.this.finish();
					}
					}).start();
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
	private void init() {
		back_to_main1 = (TextView)findViewById(R.id.back_to_main1);
		change_password = (TextView)findViewById(R.id.change_password);
		call_info = (RelativeLayout)findViewById(R.id.call_info);
		per_info =  (RelativeLayout)findViewById(R.id.per_info);
		time_info = (RelativeLayout)findViewById(R.id.time_info);
		borrow_info = (RelativeLayout)findViewById(R.id.borrow_info);
		right1 = (TextView)findViewById(R.id.right1);
		right2 = (TextView)findViewById(R.id.right2);
		right3 = (TextView)findViewById(R.id.right3);
		right4 = (TextView)findViewById(R.id.right4);
		right6 = (TextView)findViewById(R.id.right6);
		right7 = (TextView)findViewById(R.id.right7);
		right8 = (TextView)findViewById(R.id.right8);
		right9 = (TextView)findViewById(R.id.right9);
		right10 = (TextView)findViewById(R.id.right10);
		right11 = (TextView)findViewById(R.id.right11);
		right12 = (EditText)findViewById(R.id.right12);
		left5     = (EditText)findViewById(R.id.left5);
	}
}
