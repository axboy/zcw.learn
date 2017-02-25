package cn.edu.jxnu.x3104.team3.jxnu_library.ui;

import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import cn.edu.jxnu.x3104.team3.jxnu_library.parameters.HttpPost_LoginAttr;
import cn.edu.jxnu.x3104.team3.jxnu_library.services.Dologin;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
@SuppressLint("HandlerLeak")
public class Login extends Activity {
	HttpPost_LoginAttr post_LoginAttr;
	String check;
	String check_Auto;
	private EditText userNameEdit,passwordEdit;
	private CheckBox checkPwd;			//记录密码
	private CheckBox checkAuto;			//自动登录
	private Button login;				//登陆按钮
	private   SharedPreferences sharedPreferences ;//保存用户名和密码的文件
	private Editor editor;				//编辑
	private ProgressDialog proDialog; 	//进度条
	private Handler loginHandler;		//信息处理机
	private Handler mainHandler;			//信息处理机
	private String userName;
	private String password;
	TextView back;				//返回键

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		back=(TextView)findViewById(R.id.login_back_to_main);
		initView();
		remberMe();
		login.setOnClickListener(submitListener);
		mainHandler = new Handler() {
			public void handleMessage(Message msg) {//
				if (msg.what == 0x200) { 
					Intent intent = new Intent(Login.this,Activity_Main.class);
					Bundle bun = new Bundle();
					bun.putString("userName",userName);
					bun.putString("password",password);
					intent.putExtras(bun);
					startActivity(intent);
					proDialog.dismiss(); 
					Intent intent1 = new Intent();    
					intent1.setAction("android.intent.action.MY_RECEIVER");    
					Login.this.sendBroadcast(intent1);    
					Login.this.finish();				
					overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
				}
			}
		};
		loginHandler = new Handler(){
			public void handleMessage(Message msg) {
				Bundle bundle = msg.getData();
				boolean loginResult = (Boolean)bundle.get("isNetError");//只获取登陆失败
				if(proDialog != null){  
					proDialog.dismiss();  
				}
				boolean isNet =  isNetWorkAvailable(Login.this);
				if(!isNet){
					Toast.makeText(Login.this, "当前网络不可用",  Toast.LENGTH_SHORT).show(); 
				}
				if(!loginResult) {  
					Toast.makeText(Login.this, "错误的用户名或密码",  Toast.LENGTH_SHORT).show();  
				}  
				super.handleMessage(msg);
			}
		};
		//返回键点击事件
				back.setOnClickListener(new View.OnClickListener(){
					public void onClick(View v) {
						Login.this.finish();
						overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
					}			
				});
	}
	//查测网络是否连接 需要添加连网权限 返回true 表示联网 返回false 表示没有联网 &nbsp;<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
	public static boolean isNetWorkAvailable(Context context) {  
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
		NetworkInfo info = cm.getActiveNetworkInfo();  
		return info != null && info.isConnected();
	} 
	//初始化UI组件
	private void initView(){
		userNameEdit = (EditText)this.findViewById(R.id.Study_Number);
		passwordEdit = (EditText)this.findViewById(R.id.Password);
		checkPwd = (CheckBox)this.findViewById(R.id.remb_password);
		checkAuto = (CheckBox)this.findViewById(R.id.login_auto);
		login = (Button)this.findViewById(R.id.Login_Button);
	}
	//记录密码
	private void remberMe(){

		sharedPreferences= getSharedPreferences("login",Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();//获取编辑器  		
		String userName = 	sharedPreferences.getString("name","");
		String pwd = 	sharedPreferences.getString("pwd","");
		String check_state = sharedPreferences.getString("state","");
		String check_state_login = sharedPreferences.getString("login_state","");
		if(check_state.equals("111")){
			userNameEdit.setText(userName);
			passwordEdit.setText(pwd);
			
			if(userName!=null && !userName.equals("")){
				checkPwd.setChecked(true);
			} 
		}
		if(check_state_login.equals("111")){
			userNameEdit.setText(userName);
			passwordEdit.setText(pwd);
			post_LoginAttr = new HttpPost_LoginAttr(pwd,userName);
			if(userName!=null && !userName.equals("")){
				checkAuto.setChecked(true);
				proDialog = ProgressDialog.show(Login.this,"请稍候","正在登陆...", true, true); 
				Thread loginThread = new Thread(new LoginFailureHandler());  
				loginThread.start(); 
			} 						
		}
	}
	//登陆事件
	private OnClickListener submitListener = new OnClickListener() {  

		public void onClick(View v) {
			Animation inputbox_shack = AnimationUtils.loadAnimation(
					Login.this, R.anim.loginui_inputbox_warningshack);
			if (userNameEdit.getText().toString().equals("")
					&& passwordEdit.getText().toString().equals("")) {// 输入框不为空
				userNameEdit.startAnimation(inputbox_shack);
				passwordEdit.startAnimation(inputbox_shack);
			} else if (userNameEdit.getText().toString().equals("")) {
				userNameEdit.startAnimation(inputbox_shack);
			} else if (passwordEdit.getText().toString().equals("")) {
				passwordEdit.startAnimation(inputbox_shack);
			} else {
				userName = userNameEdit.getText().toString();
				password=passwordEdit.getText().toString();
				// 编辑配置  
				post_LoginAttr = new HttpPost_LoginAttr(password,userName);
				editor.putString("name",userName);  
				editor.putString("pwd",password);  
				if(checkPwd.isChecked())
				{check = "111";}
				else check = "000";
				if(checkAuto.isChecked())
				{check_Auto = "111";}
				else check_Auto = "000";
				editor.putString("state", check);
				editor.putString("login_state", check_Auto);
				editor.commit();//提交修改  
				proDialog = ProgressDialog.show(Login.this,"请稍候","正在登陆...", true, true); 
				Thread loginThread = new Thread(new LoginFailureHandler());  
				loginThread.start();  

			}  
		}
	};

	// 验证是否登陆成功
	private class LoginFailureHandler implements Runnable{

		public void run() {
			sharedPreferences= getSharedPreferences("login",Context.MODE_PRIVATE);
			String useid=sharedPreferences.getString("name", "");
			useid=useid.substring(2);
//			Log.d("useid",useid);
			Dologin dologin = new Dologin(post_LoginAttr);
			boolean loginState = dologin.loginActionMethodPost(Login.this);
			if(loginState){
				dologin.saveUserPhoto(useid,Login.this);
//				Log.d("havedown","havedown");
				mainHandler.sendEmptyMessage(0x200);
			}else{
				Message message = new Message();
				Bundle bundle = new Bundle();  
				bundle.putBoolean("isNetError",loginState);  
				message.setData(bundle);  
				loginHandler.sendMessage(message);  					 
			}
		}
	}
}