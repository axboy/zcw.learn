package cn.edu.jxnu.x3104.team3.jxnu_library.left_menu;
//缴费记录
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import cn.edu.jxnu.x3104.team3.jxnu_library.parameters.MyConstants;
import cn.edu.jxnu.x3104.team3.jxnu_library.services.DoConnection;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class Borrow_Cost extends Activity{
	WebView show;
	DoConnection doconnection;
	String html = " ";
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.left_borrow_cost);
		show=(WebView) findViewById(R.id.left_borrow_cost);
		new Thread(new Runnable(){
			public void run() {
				html = new DoConnection().gethtmlcode(Borrow_Cost.this, 
						MyConstants.HTTP_JXNU_LIB_BORROW_COST);	
				Document doc = Jsoup.parse(html);
				Elements div_element=doc.getElementsByTag("table");
				Elements elements=doc.getElementsByTag("tr");
				String div_table ="<table width=\""+ (480-10)+"\">";
				String temp = "";
				for(Element element:elements){
					int i=1;
					Elements tds=element.getElementsByTag("td");
					div_table = div_table+"<tr>";
					for(Element td:tds)
					{					
						temp = td.toString();
								if(i==2 || i==7|| i==9)
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
				show.loadDataWithBaseURL(null, div_table.replace("d8d8d8","18b4e0"), null, "utf-8", null);
			}
		}).start();
	}
}
