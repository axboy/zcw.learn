package cn.edu.jxnu.x3104.team3.jxnu_library.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class DoSearch {	
	private HttpClient httpClient = new DefaultHttpClient();
	private HttpResponse httpResponse;
	public String post_search(String search_type,String substance,String doc_type,
								String search_mode,String search_sort) {
		String result = "";
		String validateURL = "http://219.229.249.138:8080/opac/openlink.php";
		HttpPost httpRequest = new HttpPost(validateURL);				// post连接
		List<NameValuePair> params = new ArrayList<NameValuePair>();	// HTTP
		/* post参数列表 */
		params.add(new BasicNameValuePair("strSearchType", search_type));		//顶部检索条件
		params.add(new BasicNameValuePair("historyCount", "1"));				//未知，唯一值 1
		params.add(new BasicNameValuePair("strText", substance));				//搜索内容
		params.add(new BasicNameValuePair("x", "15"));
		params.add(new BasicNameValuePair("y", "16"));
		params.add(new BasicNameValuePair("doctype", doc_type));		//检索条件
		params.add(new BasicNameValuePair("match_flag", search_mode));	//检索模式：forward 前方一致，full 完全匹配，any 任意匹配 
		params.add(new BasicNameValuePair("displaypg", "100"));			//每页显示：20，30，50，100
		params.add(new BasicNameValuePair("sort", search_sort));
		//结果排序：
		//CATA_DATE，入藏日期
		//M_TITLE，题名
		//M_AUTHOR，责任者
		//M_CALL_NO，索书号
		//M_PUBLISHER，出版社
		//M_PUB_YEAR，出版日期
		
		params.add(new BasicNameValuePair("orderby", "desc"));		//结果排序：asc 升序排列 ，desc 降序排列
		params.add(new BasicNameValuePair("showmode", "list"));		//结果显示：list 详细显示，table 表格显示
		params.add(new BasicNameValuePair("dept", "ALL"));			//选择校区，唯一值 all
	/* HTTP连接开始 */
	try {
		// 发出HTTP request
		httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
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
			result = sb.toString();
		}else {
			}
	}catch (Exception e) {
		e.printStackTrace();
	}
		return result;
	}	
}
