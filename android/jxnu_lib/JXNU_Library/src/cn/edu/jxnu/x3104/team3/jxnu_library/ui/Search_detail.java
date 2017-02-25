package cn.edu.jxnu.x3104.team3.jxnu_library.ui;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class Search_detail extends Activity{
	WebView show;
	TextView back;
	int ScreenWIDTH;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_result_detail);
		show=(WebView) findViewById(R.id.book_web_detail);
		back=(TextView)findViewById(R.id.back_to_main6);
		back.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Search_detail.this.finish();
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}			
		});
		DisplayMetrics dMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dMetrics);
	    ScreenWIDTH = dMetrics.widthPixels;
		new Thread(new Runnable(){
			public void run() {
				Intent intent=getIntent();
				String news_detail_url=intent.getStringExtra("qwe");
				show_detail(news_detail_url);
			}
		}).start();
	}
	public void show_detail(String url){
		String div = "";
		try{
			Document doc=Jsoup.connect(url).get();
//			Log.i("doc",doc.html());
			Elements div_detail=doc.select("div[style=float:left;]");
			div=div_detail.toString();				//标题源码
			Elements elements=doc.getElementsByTag("tr");
			String div_table ="<table width=\""+ (ScreenWIDTH-10)+"\">";
			String temp = "";
			for(Element element:elements)
			{
				int i=1;
				Elements tds=element.getElementsByTag("td");
				div_table = div_table+"<tr>";
				for(Element td:tds)
				{					
					temp = td.toString();
							if(i==5 || i==1)
							{
								temp = "";
							}
					div_table = div_table+temp;
					Log.i("div_table",div_table);
					i++;
				}				
				div_table = div_table+"</tr>";
			}
			div_table=div_table +"</table>";
//			Log.i("div_table",div_table);
			div=div+"<br/>"+div_table.toString();			//标题+内容
//		    Log.i("div", div);
			show.loadDataWithBaseURL(null, div, null, "utf-8", null);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
