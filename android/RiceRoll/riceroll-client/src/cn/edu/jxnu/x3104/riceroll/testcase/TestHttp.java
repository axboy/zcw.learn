package cn.edu.jxnu.x3104.riceroll.testcase;

import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.bean.User;
import cn.edu.jxnu.x3104.riceroll.service.DoFeedBack;
import cn.edu.jxnu.x3104.riceroll.service.DoHttp;
import cn.edu.jxnu.x3104.riceroll.service.DoSearch;
import cn.edu.jxnu.x3104.riceroll.service.DoUserInfo;
import android.app.Activity;
import android.os.Bundle;

//测试网络返回的数据
public class TestHttp extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		new Thread(new Runnable(){
			@Override
			public void run() {
				//User u=new DoUserInfo().getUserInfo(TestHttp.this);
				//new DoUserInfo().updateUserInfo(u, TestHttp.this);
				new DoHttp().getCookBookByName("0000000001");
			}			
		}).start();
	}
}
