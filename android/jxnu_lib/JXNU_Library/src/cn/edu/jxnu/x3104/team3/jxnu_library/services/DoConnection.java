package cn.edu.jxnu.x3104.team3.jxnu_library.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import android.content.Context;
import android.content.SharedPreferences;

public class DoConnection {
	public String gethtmlcode(Context context,String URL){
		String html="";
		String cookie="";
		try {
			SharedPreferences sp= context.getSharedPreferences("login", Context.MODE_PRIVATE);	
			cookie = sp.getString("cookie", "none");
			HttpPost httpRequest = new HttpPost(URL);// post连接
			httpRequest.setHeader("Cookie", cookie);
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpRequest);
			/* 获取信息流开始 */
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
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
				html = sb.toString(); // 此时result中就是我们的HTML的源代码了
			}} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return html;
	}
}
