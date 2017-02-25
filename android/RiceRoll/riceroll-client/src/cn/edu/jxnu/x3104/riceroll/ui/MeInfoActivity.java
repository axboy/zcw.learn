package cn.edu.jxnu.x3104.riceroll.ui;

import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.bean.User;
import cn.edu.jxnu.x3104.riceroll.service.DoUserInfo;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MeInfoActivity extends Activity{
	private final static String TAG = "MeInfoActivity";

	User user;

	TextView tvName,tvTel,tvAddr,tvRegTime;

	RelativeLayout rlName,rlTel,rlAddr,rlRegTime,rlPwd;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_info);
		init();
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				user=new DoUserInfo().getUserInfo(MeInfoActivity.this);
				mainhandler.sendEmptyMessage(0x300);
			}			
		}).start();
	}

	private void init() {
		// TODO Auto-generated method stub
		tvName=(TextView)findViewById(R.id.me_info_tv_name);
		tvTel=(TextView)findViewById(R.id.me_info_tv_tel);
		tvAddr=(TextView)findViewById(R.id.me_info_tv_addr);
		tvRegTime=(TextView)findViewById(R.id.me_info_tv_reg_time);

		rlName=(RelativeLayout)findViewById(R.id.me_info_rl_name);
		rlTel=(RelativeLayout)findViewById(R.id.me_info_rl_tel);
		rlAddr=(RelativeLayout)findViewById(R.id.me_info_rl_addr);
		rlRegTime=(RelativeLayout)findViewById(R.id.me_info_rl_reg_time);
		rlPwd=(RelativeLayout)findViewById(R.id.me_info_rl_pwd);

		rlName.setOnTouchListener(new MyRelativeLayoutOnTouchListener());
		rlTel.setOnTouchListener(new MyRelativeLayoutOnTouchListener());
		rlAddr.setOnTouchListener(new MyRelativeLayoutOnTouchListener());
		rlRegTime.setOnTouchListener(new MyRelativeLayoutOnTouchListener());
		rlPwd.setOnTouchListener(new MyRelativeLayoutOnTouchListener());
		
		rlName.setOnClickListener(new MyRelativeLayoutOnClickListener());
		rlTel.setOnClickListener(new MyRelativeLayoutOnClickListener());
		rlAddr.setOnClickListener(new MyRelativeLayoutOnClickListener());
		rlRegTime.setOnClickListener(new MyRelativeLayoutOnClickListener());
		rlPwd.setOnClickListener(new MyRelativeLayoutOnClickListener());
	}

	/**处理消息*/
	private Handler mainhandler=new Handler(){
		public void handleMessage(Message msg){
			/**0x300加载到适配器*/
			if(msg.what==0x300){
				tvName.setText(user.getUserName());
				tvTel.setText(user.getUserTel());
				tvAddr.setText(user.getUserAddress());
				tvRegTime.setText(user.getUserRegDate());		
			}
		}
	};


	/**点击事件监听*/
	public class MyRelativeLayoutOnClickListener implements OnClickListener{

		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.me_info_rl_name :
				rlName.setBackgroundColor(Color.WHITE);
				//Intent intent = new Intent (MeInfoActivity.this,MeInfoActivity.class);
				//startActivity(intent);
				break;
			case R.id.me_info_rl_tel :
				rlTel.setBackgroundColor(Color.WHITE);
				//Intent intent = new Intent (MeInfoActivity.this,MeInfoActivity.class);
				//startActivity(intent);
				break;
			case R.id.me_info_rl_addr :
				rlAddr.setBackgroundColor(Color.WHITE);
				//Intent intent = new Intent (MeInfoActivity.this,MeInfoActivity.class);
				//startActivity(intent);
				break;
			case R.id.me_info_rl_reg_time :
				rlRegTime.setBackgroundColor(Color.WHITE);
				//Intent intent = new Intent (MeInfoActivity.this,MeInfoActivity.class);
				//startActivity(intent);
				break;
			case R.id.me_info_rl_pwd :
				rlPwd.setBackgroundColor(Color.WHITE);
				//Intent intent = new Intent (MeInfoActivity.this,MeInfoActivity.class);
				//startActivity(intent);
				break;
			}
		}
	}

	/**监听器*/
	private class MyRelativeLayoutOnTouchListener implements OnTouchListener{

		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.me_info_rl_name :
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					rlName.setBackgroundColor(Color.parseColor("#e0e0e0"));
				}
				if(event.getAction()==MotionEvent.ACTION_UP||event.getAction()==MotionEvent.ACTION_CANCEL){
					rlName.setBackgroundColor(Color.WHITE);
				}
				break;
			case R.id.me_info_rl_tel :
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					rlTel.setBackgroundColor(Color.parseColor("#e0e0e0"));
				}
				if(event.getAction()==MotionEvent.ACTION_UP||event.getAction()==MotionEvent.ACTION_CANCEL){
					rlTel.setBackgroundColor(Color.WHITE);
				}
				break;
			case R.id.me_info_rl_addr :
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					rlAddr.setBackgroundColor(Color.parseColor("#e0e0e0"));
				}
				if(event.getAction()==MotionEvent.ACTION_UP||event.getAction()==MotionEvent.ACTION_CANCEL){
					rlAddr.setBackgroundColor(Color.WHITE);
				}
				break;
			case R.id.me_info_rl_reg_time :
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					rlRegTime.setBackgroundColor(Color.parseColor("#e0e0e0"));
				}
				if(event.getAction()==MotionEvent.ACTION_UP||event.getAction()==MotionEvent.ACTION_CANCEL){
					rlRegTime.setBackgroundColor(Color.WHITE);
				}
				break;
			case R.id.me_info_rl_pwd :
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					rlPwd.setBackgroundColor(Color.parseColor("#e0e0e0"));
				}
				if(event.getAction()==MotionEvent.ACTION_UP||event.getAction()==MotionEvent.ACTION_CANCEL){
					rlPwd.setBackgroundColor(Color.WHITE);
				}
				break;
			}
			if(event.getAction() == MotionEvent.ACTION_MOVE){
				return true;
			}
			return false;
		}		
	}
	
	public void exit(View v){
		this.finish();
	}

}
