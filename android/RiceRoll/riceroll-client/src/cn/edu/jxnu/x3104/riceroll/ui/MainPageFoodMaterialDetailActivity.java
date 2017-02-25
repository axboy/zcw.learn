package cn.edu.jxnu.x3104.riceroll.ui;

import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.bean.Condiment;
import cn.edu.jxnu.x3104.riceroll.bean.FoodMaterial;
import cn.edu.jxnu.x3104.riceroll.db.DbCondiment;
import cn.edu.jxnu.x3104.riceroll.db.DbFoodMaterial;
import cn.edu.jxnu.x3104.riceroll.db.DbFridge;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainPageFoodMaterialDetailActivity extends Activity{
	private final static String TAG = "MainPageFoodMaterialDetailActivity";
	TextView text,name,protein,sugar,fat,fiber,vc,ca,cl,na,p,calories;
	TextView tvAdd;
	String id;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fm_c_detail);
		init();
		tvAdd.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean exist;
				try{
					exist=new DbFridge().isInFridge(id);
				}catch(SQLException e){
					new DbFridge().createTableFridge();
					exist=false;
				}
				if(exist){
					Log.e(TAG,"加入冰箱失败，"+id+"号食材已加入冰箱");
				}else{
					new DbFridge().addFoodMaterialToFridge(id);
				}
			}
		});
		new Thread(new Runnable(){
			public void run(){
				Intent intent=getIntent();
				id=intent.getStringExtra("open_foodMaterial_detail");
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
	}


	/**处理消息，主线程更新UI*/
	private Handler mainHandler=new Handler(){
		public void handleMessage(Message msg){
			if(msg.what==0x300){
				FoodMaterial fm=new DbFoodMaterial().getFoodMaterialDetail(id);
				text.setText("食材名");
				name.setText(fm.getFoodMaterial_name()+"");
				protein.setText(fm.getFoodMaterial_protein()+"");
				sugar.setText(fm.getFoodMaterial_sugar()+"");
				fat.setText(fm.getFoodMaterial_fat()+"");
				ca.setText(fm.getFoodMaterial_calcium()+"");
				na.setText(fm.getFoodMaterial_natrium()+"");
				p.setText(fm.getFoodMaterial_phosphorus()+"");
				vc.setText(fm.getFoodMaterial_vc()+"");
				fiber.setText(fm.getFoodMaterial_dietary_fiber()+"");
				cl.setText(fm.getFoodMaterial_muriate()+"");
				calories.setText(fm.getFoodMaterial_calories()+"");
			}
		}	
	};

	public void exit(View v){
		this.finish();
	}
}