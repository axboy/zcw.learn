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
import cn.edu.jxnu.x3104.team3.jxnu_library.ui.Activity_Main_unlogin;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class Reader_Lost extends Activity{
	EditText password;
	Button enter;
	TextView back;
	private Handler mainhandler;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reader_lost);
		init();
		mainhandler = new Handler(){
			public void handleMessage(Message msg) {
				if(msg.what == 0x200){
					AlertDialog.Builder OK = new AlertDialog.Builder(Reader_Lost.this)
					.setTitle("挂失失败")
					.setMessage("您此前已挂失或您输入的密码有误，您需要重新登录");
					setPositiveButton1(OK).create().show();
				}
				else if(msg.what == 0x300) {  
					AlertDialog.Builder FAIL = new AlertDialog.Builder(Reader_Lost.this)
					.setTitle("挂失成功")
					.setMessage("您需要重新登录"); 
					setNeutralButton(FAIL).create().show();
				}  
				super.handleMessage(msg);
			}
		};
		back.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Reader_Lost.this.finish();
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}			
		});
		enter.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Animation inputbox_shack = AnimationUtils.loadAnimation(
						Reader_Lost.this, R.anim.loginui_inputbox_warningshack);
					if (password.getText().toString().equals("")) {
					password.startAnimation(inputbox_shack);
					}else
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(Reader_Lost.this)
												.setTitle("读者挂失").setMessage("您确定要挂失吗？");
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
						HttpPost httpRequest = new HttpPost(MyConstants.HTTP_JXNU_LIB_LOST);// post连接
						List<NameValuePair> params = new ArrayList<NameValuePair>();// HTTP
						/* post参数列表 */
						params.add(new BasicNameValuePair("loss_pwd", password.getText().toString()));	
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
							if(html.contains("重新登陆")||html.contains("挂失失败"))
							{
								mainhandler.sendEmptyMessage(0x200);
							}
						} else {
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
						Intent intent = new Intent (Reader_Lost.this,Activity_Main_unlogin.class);
						SharedPreferences sp= getSharedPreferences("login", Context.MODE_PRIVATE);
						Editor editor = sp.edit();
						editor.putString("login_state", "000");
						editor.commit();//提交修改  
						startActivity(intent);
						Reader_Lost.this.finish();
					}
					}).start();
			}
		});
	}
	private AlertDialog.Builder setNeutralButton(AlertDialog.Builder FAIL){
		return FAIL.setNeutralButton("确定",new OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which) {
				new Thread(new Runnable(){
					public void run() {
						Intent intent = new Intent (Reader_Lost.this,Activity_Main_unlogin.class);
						SharedPreferences sp= getSharedPreferences("login", Context.MODE_PRIVATE);
						Editor editor = sp.edit();
						editor.putString("login_state", "000");
						editor.commit();//提交修改  
						startActivity(intent);
						Reader_Lost.this.finish();
					}
					}).start();
			}
		});
	}
	private void init() {
		password = (EditText)findViewById(R.id.reader_card_lost_password);
		enter = (Button)findViewById(R.id.reader_card_lost_enter);
		back = (TextView)findViewById(R.id.reader_lost_back_to_main);
	}
}
