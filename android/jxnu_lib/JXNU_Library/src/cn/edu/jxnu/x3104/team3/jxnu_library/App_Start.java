package cn.edu.jxnu.x3104.team3.jxnu_library;

import cn.edu.jxnu.x3104.team3.jxnu_library.ui.Activity_Main_unlogin;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

public class App_Start extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.app_start);
		new Handler().postDelayed(new Runnable(){
			public void run(){
				Intent intent = new Intent (App_Start.this,Activity_Main_unlogin.class);			
				startActivity(intent);
				App_Start.this.finish();
			}
		}, 1500);
	}
}
