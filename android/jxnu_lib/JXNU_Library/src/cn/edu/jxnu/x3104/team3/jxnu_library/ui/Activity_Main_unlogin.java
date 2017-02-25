package cn.edu.jxnu.x3104.team3.jxnu_library.ui;
import android.view.View;
import android.view.View.OnClickListener;
import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class Activity_Main_unlogin extends TabActivity{
	TabHost tabhost;
	TextView toLogin;
	MyReceiver receiver = new MyReceiver();  
	public static final int SNAP_SPEED=150;		//滑动速度

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page_unlogin);
		
		tabhost=getTabHost();
		toLogin=(TextView) findViewById(R.id.to_login);
		//添加对象
		Intent tab01=new Intent(this,TFSearchActivity.class);
		TabSpec tab1=tabhost.newTabSpec("tab1");
		View V_1=getView(R.drawable.search_selector,"搜索");	
		tab1.setIndicator(V_1);			//设置标题
		tab1.setContent(tab01);			//设置内容
		tabhost.addTab(tab1);			//添加标签页
		//添加对象
		Intent tab03=new Intent(this,Activity_gnxw.class);
		TabSpec tab3=tabhost.newTabSpec("tab3");
		View V_3=getView(R.drawable.news_selector,"新闻");	
		tab3.setIndicator(V_3);			//设置标题
		tab3.setContent(tab03);			//设置内容
		tabhost.addTab(tab3);			//添加标签页
		
	    IntentFilter filter = new IntentFilter();  
	    filter.addAction("android.intent.action.MY_RECEIVER");  
	    //注册监听器  
	    registerReceiver(receiver, filter);  
		//添加点击事件
		toLogin.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent (Activity_Main_unlogin.this,Login.class);			  
				startActivity(intent);				
				//Activity_Main_unlogin.this.finish();
			}
		});
	}
	private View getView(int imId,String title) {
		View view=View.inflate(getApplicationContext(),R.layout.tab_nav, null);
		ImageView iv1=(ImageView)view.findViewById(R.id.ivIcon);
		iv1.setBackgroundResource(imId);
		TextView tv=(TextView)view.findViewById(R.id.tvTitle);
		tv.setText(title);
		return view;
	}
	 class MyReceiver extends BroadcastReceiver {  
		  
        public void onReceive(Context context, Intent intent) {  
            Activity_Main_unlogin.this.finish();  
        }
    }  
	 protected void onDestroy(){  
	        //注销监听器  
	        unregisterReceiver(receiver);  
	        super.onDestroy();  
	    }  

}