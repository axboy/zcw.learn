package cn.edu.jxnu.x3104.team3.jxnu_library.left_menu;
//借阅历史、暂时只能显示前20条记录
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import cn.edu.jxnu.x3104.team3.jxnu_library.parameters.MyConstants;
import cn.edu.jxnu.x3104.team3.jxnu_library.services.DoConnection;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class Borrow_History extends Activity{
	WebView show;
	DoConnection doconnection;
	String html;
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.left_borrow_history);
		show=(WebView) findViewById(R.id.left_borrow_history);
		new Thread(new Runnable(){
			public void run() {			
				SharedPreferences sp= getSharedPreferences("login", Context.MODE_PRIVATE);	
				String cookie = sp.getString("cookie", "none");
				HttpPost httpRequest = new HttpPost(MyConstants.HTTP_JXNU_LIB_BORROW_HIST);// post连接
				List<NameValuePair> params = new ArrayList<NameValuePair>();// HTTP
				/* post参数列表 */
				params.add(new BasicNameValuePair("para_string", "all"));
			/* HTTP连接开始 */
			try {
				// 发出HTTP request
				httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
				String sd = params.toString();
				Log.i("sd",sd);
				httpRequest.setHeader("Cookie", cookie);
				// 取得HTTP response
				HttpResponse httpResponse = new DefaultHttpClient()
				.execute(httpRequest); // 执行
				// 若状态码为200 OK
				if (httpResponse.getStatusLine().getStatusCode() == 200) { // 返回值正常
					// 获取返回的cookie
					StringBuffer sb = new StringBuffer();
					HttpEntity entity = httpResponse.getEntity();
					InputStream is = entity.getContent();
					BufferedReader br = new BufferedReader(
							new InputStreamReader(is, "UTF-8"));
					// 是读取要改编码的源，源的格式是UTF-8的，按源格式读进来，然后再对源码转换成想要的编码就行
					String data = "";
					while ((data = br.readLine()) != null) {
						sb.append(data);
					}
					html = sb.toString();
					}
			}
				catch (Exception e) {
				e.printStackTrace();
			}
				Document div = Jsoup.parse(html);				
				Element table = div.select("[cellpadding").first();
				show.loadDataWithBaseURL(null, table.toString().replace("d8d8d8", "18b4e0"), null, "utf-8", null);
			}
		}).start();
	}
}