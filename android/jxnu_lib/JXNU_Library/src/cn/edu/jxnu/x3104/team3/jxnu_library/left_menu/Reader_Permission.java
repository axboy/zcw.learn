package cn.edu.jxnu.x3104.team3.jxnu_library.left_menu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import cn.edu.jxnu.x3104.team3.jxnu_library.parameters.MyConstants;
import cn.edu.jxnu.x3104.team3.jxnu_library.services.DoConnection;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class Reader_Permission extends Activity{
	TextView back;
	WebView show;
	String html=" ";
	int ScreenWIDTH;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reader_permission);
		init();
		DisplayMetrics dMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dMetrics);
	    ScreenWIDTH = dMetrics.widthPixels;
		back.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Reader_Permission.this.finish();
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}	
		});
		new Thread(new Runnable(){
			public void run() {
			html =new DoConnection().gethtmlcode(Reader_Permission.this, MyConstants.HTTP_JXNU_LIB_READ_PERMISSION);	
			show_detail(html);
			}
		}).start();
		Log.i("codes",html);
		
	}
	public void show_detail(String url){
		String div = "";
		Document doc=Jsoup.parse(url);
		Log.i("doc",doc.html());
		Element div_detail=doc.getElementById("mylib_content");
		Elements elements=div_detail.getElementsByTag("tr");
		String div_table ="<table width=\""+ (ScreenWIDTH-10)+"\">";
		String temp = "";
		for(Element element:elements)
		{
			@SuppressWarnings("unused")
			int i=1;
			Elements tds=element.getElementsByTag("td");
			div_table = div_table+"<tr>";
			for(Element td:tds)
			{					
				temp = td.toString();
						if(td.text().contains("<"))
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
		div=div+"<br/>"+div_table.toString();			//标题+内容
		show.loadDataWithBaseURL(null, div, null, "utf-8", null);
	}
	private void init() {
		back = (TextView)findViewById(R.id.per_back_to_info);
		show = (WebView)findViewById(R.id.per_web_detail);
	}
}
