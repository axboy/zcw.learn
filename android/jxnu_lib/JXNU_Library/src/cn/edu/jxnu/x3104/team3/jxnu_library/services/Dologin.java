package cn.edu.jxnu.x3104.team3.jxnu_library.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import cn.edu.jxnu.x3104.team3.jxnu_library.parameters.HttpPost_LoginAttr;
import cn.edu.jxnu.x3104.team3.jxnu_library.parameters.MyConstants;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

@SuppressWarnings("unused")
public class Dologin {
	private HttpPost_LoginAttr post_LoginAttr;// 需要传送的参数
	 private List<Cookie> cookies;// 保存从服务器返回的cookies列表
	private HttpClient httpClient = new DefaultHttpClient();
	private HttpResponse httpResponse;
	public  Dologin(HttpPost_LoginAttr post_LoginAttr) {
		this.post_LoginAttr = post_LoginAttr;
	}
	// 登陆的 post 方式
		public boolean loginActionMethodPost(Context context) {
			boolean login = false;
			// Post方式请求
			String newURL = "http://219.229.249.138:8080/reader/redr_info.php";
			String flag;
				setCookies(context);
				flag = getHttpInputStreamString(newURL);
				if (flag.contains("注销")) {// 判断是否登陆成功
					login = true;
				}
			return login;
			}
		
			public void setCookies(Context context) {
			String validateURL = "http://219.229.249.138:8080/reader/redr_verify.php";
			String coookie = "";
			HttpPost httpRequest = new HttpPost(validateURL);// post连接
			List<NameValuePair> params = new ArrayList<NameValuePair>();// HTTP
			/* post参数列表 */
			params.add(new BasicNameValuePair("select", "cert_no"));
			params.add(new BasicNameValuePair("number", post_LoginAttr.getStuNum()));
			params.add(new BasicNameValuePair("passwd", post_LoginAttr.getPassword()));		
			params.add(new BasicNameValuePair("returnUrl", ""));
		/* HTTP连接开始 */
		try {
			// 发出HTTP request
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			String sd = params.toString();
//			Log.i("sd",sd);
			// 取得HTTP response
			httpResponse = httpClient.execute(httpRequest); // 执行
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
				String html = sb.toString();
				setUsernameToSP(html,context);
				cookies = ((AbstractHttpClient) httpClient).getCookieStore()
						.getCookies();
				for (Cookie cookie : cookies) {
					coookie = (cookie.getName() + "=" + cookie.getValue() + ";");		
//					Log.i("cookies_for",coookie);
				}
//				Log.i("cookies",coookie);
				SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);
				Editor editor = sp.edit();//获取编辑器  	
				editor.putString("cookie", coookie);
			
				editor.commit();
//				Log.i("cookies_save",sp.getString("cookie", "none"));
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/* HTTP连接结束 */
	}
			/* 获取cookies */
			public List<Cookie> getCookies() {
				return cookies;
			}
			/* 建立HTTP post连接并返回HTTP信息流 */
			public String getHttpInputStreamString(String URL) {
				String result = "FAILED";// 用于存放的得到的http流字符串
				if (true) {// 通过cookies判断是否登陆成功，若成功则cookies有两到三个项目
					try {
						HttpPost httpRequest = new HttpPost(URL);// post连接
						String cookieString = "";
						for (Cookie cookie : cookies) {
							cookieString = cookieString + cookie.getName() + "="
									+ cookie.getValue() + ";";

						}
						/* 设置HTTP头部信息结束 */
						httpRequest.setHeader("Cookie", cookieString);

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
							result = sb.toString();
				//			Log.i("result1",result);
						}
						/* 获取信息流结束 */

					} catch (ClientProtocolException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					;
				}
				return result;
			}

			public void saveUserPhoto(String username, Context context) {
				Bitmap userphoto = null;
				File imagefile = context.getFileStreamPath(username
						+ ".jpg");// 获取文件路径
//				Log.d("photo0","pass");
				FileOutputStream outputStream;
				if (!imagefile.exists()) {
//					Log.d("photo1","不存在");
					try {
						URL url = new URL(MyConstants.HTTP_JWC_STUDENT_PHOTO + username
								+ ".jpg");
						HttpURLConnection connection = (HttpURLConnection) url
								.openConnection();
						connection.setConnectTimeout(5 * 1000);
						connection.setDoInput(true);
						connection.connect();
						InputStream is = connection.getInputStream();
						userphoto = BitmapFactory.decodeStream(is);
						outputStream = new FileOutputStream(imagefile);
						userphoto.compress(Bitmap.CompressFormat.JPEG, 100,
								outputStream);// 设置图片文件流
						outputStream.flush();
						outputStream.close();
						File imagefile2 = context.getFileStreamPath(username
								+ ".jpg");// 获取文件路径
						if (!imagefile2.exists()) {
//							Log.d("photo2","不存在");
							}
						else{
//							Log.d("photo2","存在");
							}
					} catch (ClientProtocolException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else{
//					Log.d("photo2","存在");
					}
			}
			/*放入用户姓名*/
			private void  setUsernameToSP(String html,Context context) {
				String username="";
				Document document=Jsoup.parse(html);
				Element element =document.select("div[style=color:#FFF; float:right;padding:5px 20px 0 0px]").first();
				username=element.text();
				username=username.replace("注销", "");
//				Log.d("username",username);
				SharedPreferences sp=context.getSharedPreferences("login", Context.MODE_PRIVATE);
				Editor editor=sp.edit();
				editor.putString("username", username);
				editor.commit();
			}
}