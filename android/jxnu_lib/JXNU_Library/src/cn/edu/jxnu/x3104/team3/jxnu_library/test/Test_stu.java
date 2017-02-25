package cn.edu.jxnu.x3104.team3.jxnu_library.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Test_stu extends Activity{
	private static String URL="http://10.3.129.79:8080/4_17_student_DB/getStudents.action";
	private ListView lv;
	private List<HashMap<String,Object>> videos;
	private HashMap<String,Object> video;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_list);
		lv=(ListView)this.findViewById(R.id.list_gnxw);

		getVideosFromServer(this.URL);

	}

	private void getVideosFromServer(String url) {
		// TODO Auto-generated method stub
		new AsyncTask<String, Void, List<HashMap<String,Object>>>() {

			@Override
			protected List<HashMap<String, Object>> doInBackground(
					String... params) {
				// TODO Auto-generated method stub
				try{
					HttpClient hc=new DefaultHttpClient();
					HttpPost request=new HttpPost(params[0]);
					HttpResponse response=hc.execute(request);
					if(response.getStatusLine().getStatusCode()==200){
						HttpEntity entity=response.getEntity();
						if(entity!=null){
							String strJson=EntityUtils.toString(entity,"utf-8");
							if(strJson!=null){
								JSONArray jsonArray=new JSONArray(strJson);
								videos=new ArrayList<HashMap<String,Object>>();
								for(int i=0;i<jsonArray.length();i++){
									JSONObject jsonObject=(JSONObject)jsonArray.get(i);
									video=new HashMap<String,Object>();
									video.put("studentId", jsonObject.get("studentId"));
									video.put("studentName", jsonObject.get("studentName"));
									video.put("studentSex", jsonObject.get("studentSex"));
									video.put("studentHometown", jsonObject.get("studentHometown"));
									videos.add(video);
								}
							}
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				return videos;
			}
			protected void onPostExecute(List<HashMap<String,Object>> videos){
				SimpleAdapter adapter=new SimpleAdapter(getApplicationContext(),videos,
						R.layout.test_stu_option,new String[]{"studentName","studentId"},
						new int[]{R.id.stu_name,R.id.stu_id});
				lv.setAdapter(adapter);
			}
		}.execute(url);
	}
}