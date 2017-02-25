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
import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import cn.edu.jxnu.x3104.team3.jxnu_library.parameters.MyConstants;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class Change_Contact extends Activity{
	private Handler mainhandler;
	TextView back_to_main2;
	EditText  c_email;
	EditText  c_number;
	EditText  c_phone;
	EditText  c_post_code;
	EditText  c_address;
	Button change_ok;
	Button change_cancel;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_contact);
		init();
		mainhandler = new Handler(){
			public void handleMessage(Message msg) {
				if(msg.what == 0x200){
					AlertDialog.Builder OK = new AlertDialog.Builder(Change_Contact.this)
					.setTitle("修改成功")
					.setMessage("即将返回个人信息");
					setPositiveButton1(OK).create().show();
				}
				else if(msg.what == 0x300) {  
					AlertDialog.Builder FAIL = new AlertDialog.Builder(Change_Contact.this)
					.setTitle("修改失败")
					.setMessage("请检查输入格式"); 
					setNeutralButton(FAIL).create().show();
				}  
				super.handleMessage(msg);
			}
		};
		back_to_main2.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Change_Contact.this.finish();
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}			
		});
		change_cancel.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Change_Contact.this.finish();
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}			
		});
		change_ok.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				new Thread(new Runnable(){
					public void run() {
						SharedPreferences sp= getSharedPreferences("login", Context.MODE_PRIVATE);	
						String cookie = sp.getString("cookie", "none");
						HttpPost httpRequest = new HttpPost(MyConstants.HTTP_JXNU_LIB_CHANGE_EMAIL);// post连接
						List<NameValuePair> params = new ArrayList<NameValuePair>();// HTTP
						/* post参数列表 */
						params.add(new BasicNameValuePair("address", c_address.getText().toString()));
						params.add(new BasicNameValuePair("email_cont",c_email.getText().toString()));
						params.add(new BasicNameValuePair("mobile",c_phone.getText().toString()));		
						params.add(new BasicNameValuePair("submit1", "确定"));
						params.add(new BasicNameValuePair("tele", c_number.getText().toString()));
						params.add(new BasicNameValuePair("postcode", c_post_code.getText().toString()));
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
	private void init() {
		back_to_main2 = (TextView)findViewById(R.id.back_to_main2);
		c_email = (EditText)findViewById(R.id.c_email);
		  c_number= (EditText)findViewById(R.id.c_number);
		  c_phone= (EditText)findViewById(R.id.c_phone);
		  c_post_code= (EditText)findViewById(R.id.c_post_code);
		  c_address= (EditText)findViewById(R.id.c_address);
		 change_ok = (Button)findViewById(R.id.change_ok);
		 change_cancel = (Button)findViewById(R.id.change_cancel);
		
	}
	private AlertDialog.Builder setPositiveButton1(AlertDialog.Builder OK){
		return OK.setPositiveButton("确定",new OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which) {
				new Thread(new Runnable(){
					public void run() {
						Change_Contact.this.finish();
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
}
