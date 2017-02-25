package cn.edu.jxnu.x3104.riceroll.ui;

import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.service.DoLogin;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserLoginActivity extends Activity{
	private final String TAG = "UserLoginActivity";
	String userName;
	String userPassWord;
	EditText userMsg,userPwd;
	Boolean loginResult;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_login_dialog);
		init();
	}

	/**初始化元素*/
	private void init() {
		userMsg=(EditText)findViewById(R.id.userLoginMsg);
		userPwd=(EditText)findViewById(R.id.userLoginPwd);
	}

	/**登录按钮*/
	public void loginToServer(View v){
		Log.d(TAG,"登录操作");
		userName=userMsg.getText().toString();
		userPassWord=userPwd.getText().toString(); 
		if(validateInputMsg(userName,userPassWord)){
			loginSync at=new loginSync();
			at.execute();
		}
	}

	/**取消按钮*/
	public void exitLogin(View v){
		this.finish();
	}

	/**验证输入信息是否完全*/
	public boolean validateInputMsg(String name,String pwd){		
		if(name==null||name.equals(null)||name==""||name.equals("")){
			//如果用户名为空
			Toast.makeText(this,"用户名不能为空!!!",Toast.LENGTH_SHORT).show();
			return false;
		}else if(pwd==null||pwd.equals(null)||pwd==""||pwd.equals("")){
			//如果密码为空
			Toast.makeText(this,"密码不能为空!!!",Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	/**异步实现登录操作*/
	class loginSync extends AsyncTask<Void, Void, Boolean>{
		
		//doInBackground方法内部执行后台任务,不可在此方法内修改UI
		protected Boolean doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			loginResult=new DoLogin().loginAction(userName,userPassWord,UserLoginActivity.this);
			return loginResult;
		}

		//onPostExecute方法用于在执行完后台任务后更新UI,显示结果
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			//super.onPostExecute(result);
			if(result){
				Toast.makeText(UserLoginActivity.this,"用户"+userName+"登录成功", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent (UserLoginActivity.this,MainWindowActivity.class);
				startActivity(intent);
				UserLoginActivity.this.finish();
			}else{
				Toast.makeText(UserLoginActivity.this,"用户名或密码错误\n请重新输入", Toast.LENGTH_SHORT).show();
				userPwd.setText("");
			}
		}
		//onPreExecute方法用于在执行后台任务前做一些UI操作 
		//onProgressUpdate方法用于更新进度信息
		//onCancelled方法用于在取消执行中的任务时更改UI
	}

}