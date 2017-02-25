//借阅记录
package cn.edu.jxnu.x3104.team3.jxnu_library.left_menu;

import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import cn.edu.jxnu.x3104.team3.jxnu_library.ui.Activity_gnxw_detail;
import cn.edu.jxnu.x3104.team3.jxnu_library.ui.TFSearchActivity;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class Reader_Borrow extends TabActivity{
	TabHost tabHost;
	TextView back;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.left_book_borrow);
		tabHost=getTabHost();
		back=(TextView)findViewById(R.id.reader_borrow_back_to_main);

		//返回键点击事件
		back.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Reader_Borrow.this.finish();
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}			
		});

		Intent tab01=new Intent(this,Borrow_History.class);
		TabSpec tab1=tabHost.newTabSpec("tab1");
		View V_1=getView("借阅历史");
		tab1.setIndicator(V_1);
		tab1.setContent(tab01);			//设置内容
		tabHost.addTab(tab1);			//添加标签页
		//添加对象
		Intent tab02=new Intent(this,Borrow_Cost.class);
		TabSpec tab2=tabHost.newTabSpec("tab2");
		View V_2=getView("缴费记录");
		tab2.setIndicator(V_2);
		tab2.setContent(tab02);			//设置内容
		tabHost.addTab(tab2);			//添加标签页
	}

	private View getView(String title) {
		// TODO Auto-generated method stub
		View view=View.inflate(getApplicationContext(),R.layout.tab_nav_top, null);
		//ImageView iv1=(ImageView)view.findViewById(R.id.ivIcon);
		//iv1.setBackgroundResource(imId);
		TextView tv=(TextView)view.findViewById(R.id.tvTitle_top);
		tv.setText(title);
		return view;
	}
}
