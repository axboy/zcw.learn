package cn.edu.jxnu.x3104.riceroll.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.bean.Condiment;
import cn.edu.jxnu.x3104.riceroll.bean.User;
import cn.edu.jxnu.x3104.riceroll.db.DbCondiment;
import cn.edu.jxnu.x3104.riceroll.service.DoLogin;
import cn.edu.jxnu.x3104.riceroll.service.DoRegister;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegisterActivity extends Activity{
	private final String TAG = "UserRegist";

	User user;
	int registerResult;
	EditText editTextUserNickName,editTextUserTel,editTextUserAddress,editTextUserPwd1,editTextUserPwd2;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_register);
		init();
	}

	/**初始化元素*/
	private void init() {
		editTextUserNickName=(EditText)findViewById(R.id.userNickNameToRegister);
		editTextUserTel=(EditText)findViewById(R.id.userTelToRegister);
		editTextUserAddress=(EditText)findViewById(R.id.userAddressToRegister);
		editTextUserPwd1=(EditText)findViewById(R.id.userPwd1ToRegister);
		editTextUserPwd2=(EditText)findViewById(R.id.userPwd2ToRegister);
	}

	/**确认注册按钮*/
	public void userRegisterOK(View v){
		Log.d(TAG, "点击用户注册");
		String userName=editTextUserNickName.getText().toString();
		String userTel=editTextUserTel.getText().toString();
		String userAddress=editTextUserAddress.getText().toString();
		String userPwd1=editTextUserPwd2.getText().toString();
		String userPwd2=editTextUserPwd2.getText().toString();
		if(validateInputMsg(userName,userTel,userAddress,userPwd1,userPwd2)){
			user=new User(userName,userPwd1,userTel,userAddress);
			registerSync rs=new registerSync();
			rs.execute();
		}
	}

	/**取消注册按钮*/
	public void userRegisterCancel(View v){
		this.finish();
	}

	/**验证输入信息是否完全，是否匹配*/
	public boolean validateInputMsg(String name,String tel,String address,String pwd1,String pwd2){
		if(validateName(name)&&validateTel(tel)&&validatePWD(pwd1, pwd2)){
			return true;
		}
		return false;
	}

	/**检验是否输入用户名*/
	public boolean validateName(String name){
		if(name.equals(null)||name.equals(null)||name.equals("")||name.equals("")){
			Toast.makeText(UserRegisterActivity.this,"请输入用户名", Toast.LENGTH_SHORT).show();
			return false;
		}		
		return true;
	}

	/**检验是否为手机号*/
	public boolean validateTel(String tel){
		String copyTel=tel.replaceAll(" ", "");	//去除所有空格
		if(copyTel.length()==11){
			return isMobileNo(copyTel);
		}
		Toast.makeText(UserRegisterActivity.this,"请填入正确的手机号", Toast.LENGTH_SHORT).show();
		return false;
	}

	/**进一步检验是否为手机号*/
	public boolean isMobileNo(String mobile){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,2,3,5-9]))\\d{8}$");  
		Matcher m = p.matcher(mobile); 
		if(! (m.matches()) ){
			Toast.makeText(UserRegisterActivity.this,"请填入正确的手机号", Toast.LENGTH_SHORT).show();
		}
		return m.matches(); 
	}

	/**检验两次密码是否一致*/
	public boolean validatePWD(String pwd1,String pwd2){
		if(pwd1.equals(null)||pwd1.equals(null)||pwd1.equals("")||pwd1.equals("")||
				pwd2.equals(null)||pwd2.equals(null)||pwd2.equals("")||pwd2.equals("")){
			Toast.makeText(UserRegisterActivity.this,"请输入密码!!!!", Toast.LENGTH_SHORT).show();
			return false;
		}else{
			if(pwd1.endsWith(pwd2)){
				return true;
			}else{
				Toast.makeText(UserRegisterActivity.this,"两次输入密码不一致!!!!", Toast.LENGTH_SHORT).show();
				return false;
			}
		}
	}
	
	/**异步实现注册操作*/
	class registerSync extends AsyncTask<Void, Void, Integer>{
		
		//doInBackground方法内部执行后台任务,不可在此方法内修改UI
		protected Integer doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			registerResult=new DoRegister().registerAction(user,UserRegisterActivity.this);
			return registerResult;
		}

		//onPostExecute方法用于在执行完后台任务后更新UI,显示结果
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			//super.onPostExecute(result);
			switch(result){
			case 1001:
				Toast.makeText(UserRegisterActivity.this, "用户注册成功", Toast.LENGTH_SHORT).show();
				//执行登录操作
				new Thread(new Runnable(){
					public void run() {
						new DoLogin().loginAction(user.getUserNickName(),user.getUserPassWord(),UserRegisterActivity.this);
						mainHandler.sendEmptyMessage(0x300);
					}					
				}).start();
				break;
			case 1002:
				Toast.makeText(UserRegisterActivity.this, "用户注册失败", Toast.LENGTH_SHORT).show();
				break;
			case 1003:
				Toast.makeText(UserRegisterActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
				break;
			case 1004:
				Toast.makeText(UserRegisterActivity.this, "用户注册时发生未知错误", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	}
	
	/**处理消息,主线程更新UI*/
	private Handler mainHandler=new Handler(){
		public void handleMessage(Message msg){
			if(msg.what==0x300){
				Intent intent = new Intent (UserRegisterActivity.this,MainWindowActivity.class);
				startActivity(intent);
				UserRegisterActivity.this.finish();
			}
		}	
	};

}