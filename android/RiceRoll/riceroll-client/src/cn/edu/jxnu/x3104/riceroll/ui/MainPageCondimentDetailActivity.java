package cn.edu.jxnu.x3104.riceroll.ui;

import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.bean.Condiment;
import cn.edu.jxnu.x3104.riceroll.db.DbCondiment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
public class MainPageCondimentDetailActivity extends Activity{
	
	private final static String TAG = "MainPageCondimentDetailActivity";
	TextView text,name,protein,sugar,fat,fiber,vc,ca,cl,na,p,calories;
	TextView tvAdd;
	String id;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fm_c_detail);
		init();
		new Thread(new Runnable(){
			public void run(){
				Intent intent=getIntent();
				id=intent.getStringExtra("open_condiment_detail");
				mainHandler.sendEmptyMessage(0x300);
			}
		}).start();
	}

	private void init() {
		// TODO Auto-generated method stub
		text=(TextView)findViewById(R.id.fm_c_text);
		name=(TextView)findViewById(R.id.fm_c_name);
		protein=(TextView)findViewById(R.id.fm_c_protein);
		sugar=(TextView)findViewById(R.id.fm_c_sugar);
		fat=(TextView)findViewById(R.id.fm_c_fat);
		fiber=(TextView)findViewById(R.id.fm_c_fiber);
		vc=(TextView)findViewById(R.id.fm_c_vc);
		ca=(TextView)findViewById(R.id.fm_c_ca);
		cl=(TextView)findViewById(R.id.fm_c_cl);
		na=(TextView)findViewById(R.id.fm_c_na);
		p=(TextView)findViewById(R.id.fm_c_p);
		calories=(TextView)findViewById(R.id.fm_c_calories);
		tvAdd=(TextView)findViewById(R.id.addtofridge);
		tvAdd.setText(null);
	}


	/**处理消息，主线程更新UI*/
	private Handler mainHandler=new Handler(){
		public void handleMessage(Message msg){
			if(msg.what==0x300){
				Condiment c=new DbCondiment().getCondimentDetail(id);
				text.setText("调料名");
				name.setText(c.getCondiment_name()+"");
				protein.setText(c.getCondiment_protein()+"");
				sugar.setText(c.getCondiment_sugar()+"");
				fat.setText(c.getCondiment_fat()+"");
				ca.setText(c.getCondiment_calcium()+"");
				na.setText(c.getCondiment_natrium()+"");
				p.setText(c.getcondiment_phosphorus()+"");
				vc.setText(c.getCondiment_vc()+"");
				fiber.setText(c.getCondiment_dietary_fiber()+"");
				cl.setText(c.getCondiment_muriate()+"");
				calories.setText(c.getCondiment_calories()+"");
			}
		}	
	};
	public void exit(View v){
		this.finish();
	}
}