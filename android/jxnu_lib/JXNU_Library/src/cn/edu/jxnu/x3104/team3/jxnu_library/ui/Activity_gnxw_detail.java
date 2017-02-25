package cn.edu.jxnu.x3104.team3.jxnu_library.ui;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.TextView;

@SuppressWarnings("unused")
public class Activity_gnxw_detail extends Activity{

	WebView show;
	TextView back;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_detail);
		show=(WebView) findViewById(R.id.gnxw_web_detail);
		back=(TextView)findViewById(R.id.news_detail_back_to_main);
		new Thread(new Runnable(){
			public void run() {
				Intent intent=getIntent();
				String news_detail_url=intent.getStringExtra("qwe");
				show_detail(news_detail_url);
			}
		}).start();
		//返回键点击事件
		back.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Activity_gnxw_detail.this.finish();
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}			
		});
	}
	@SuppressWarnings("deprecation")
	public void show_detail(String url){
		String div = "";
		int screenWidth;	//屏幕宽度
		WindowManager window = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		screenWidth = window.getDefaultDisplay().getWidth()-20;  	//获得屏幕宽度-20
		try{
			String content;
			Document doc=Jsoup.connect(url).get();
//			Log.i("doc",doc.html());
			Elements div_detail=doc.select("[class=detailhead]");
//			Log.i("标题源码toString",div_detail.toString());
//			Log.d("标题源码toText",div_detail.text());
//			div=div_detail.toString();						//标题源码
			Elements div_element=doc.select("[class=detailtext]");
//			Log.i("内容源码toString",div_element.toString());
//			Log.d("内容源码toText",div_element.text());
			content=div_element.toString().replace("img","img width=\""+screenWidth+"\"");//修改图片宽度，ie可用
//			Log.w("content", content);
			div=div+"<br/>"+content;			//标题+内容
			show.loadDataWithBaseURL(null, div, null, "utf-8", null);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}