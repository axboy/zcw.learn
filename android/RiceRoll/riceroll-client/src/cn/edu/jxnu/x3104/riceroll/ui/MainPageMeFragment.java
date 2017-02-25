package cn.edu.jxnu.x3104.riceroll.ui;

import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.service.DoSharedPreferences;
import cn.edu.jxnu.x3104.riceroll.start.AppStart;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.app.AlertDialog.*;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class MainPageMeFragment extends Fragment{
	private final static String TAG = "MainPageMeFragment";

	private Builder alertBulider;
	private AlertDialog alert;
	private Context context;
	private LinearLayout meData,meDownLoaded,meModeSelect,meHelp,meShare,meFeedBack,meLogout;
	TextView name,tel;

	public void SetContext(Context c){
		context=c;
		alertBulider=new Builder(context);
		alert=alertBulider.create();
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View  view;
		view=  inflater.inflate(R.layout.mainpage_me, null);
		initLayout(view);
		return view;
	}

	/**元素初始化*/
	private void initLayout(View v) {
		name=(TextView)v.findViewById(R.id.me_name);
		tel=(TextView)v.findViewById(R.id.me_tel);
		name.setText(new DoSharedPreferences().getLoginUserName(context));
		tel.setText(new DoSharedPreferences().getLoginUserTel(context));
		
		meData=(LinearLayout)v.findViewById(R.id.me_my_data);
		meDownLoaded=(LinearLayout)v.findViewById(R.id.me_downloaded_cookbook);
		meModeSelect=(LinearLayout)v.findViewById(R.id.me_mode_select);
		meHelp=(LinearLayout)v.findViewById(R.id.me_help);
		meShare=(LinearLayout)v.findViewById(R.id.me_share);
		meFeedBack=(LinearLayout)v.findViewById(R.id.me_feedback);
		meLogout=(LinearLayout)v.findViewById(R.id.me_logout);

		meData.setOnTouchListener(new MyLinearLayoutOnTouchListener());
		meDownLoaded.setOnTouchListener(new MyLinearLayoutOnTouchListener());
		meModeSelect.setOnTouchListener(new MyLinearLayoutOnTouchListener());
		meHelp.setOnTouchListener(new MyLinearLayoutOnTouchListener());
		meShare.setOnTouchListener(new MyLinearLayoutOnTouchListener());
		meFeedBack.setOnTouchListener(new MyLinearLayoutOnTouchListener());
		meLogout.setOnTouchListener(new MyLinearLayoutOnTouchListener());

		meData.setOnClickListener(new MyLinearLayoutOnClickListener());
		meDownLoaded.setOnClickListener(new MyLinearLayoutOnClickListener());
		meModeSelect.setOnClickListener(new MyLinearLayoutOnClickListener());
		meHelp.setOnClickListener(new MyLinearLayoutOnClickListener());
		meShare.setOnClickListener(new MyLinearLayoutOnClickListener());
		meFeedBack.setOnClickListener(new MyLinearLayoutOnClickListener());
		meLogout.setOnClickListener(new MyLinearLayoutOnClickListener());
	}

	/**点击事件监听*/
	public class MyLinearLayoutOnClickListener implements OnClickListener{

		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.me_my_data :{
				Log.d(TAG, "我的资料");
				meData.setBackgroundColor(Color.WHITE);
				Intent intent = new Intent (context,MeInfoActivity.class);
				startActivity(intent);
				break;
			}
			case R.id.me_downloaded_cookbook :{
				Log.d(TAG, "离线菜谱");
				meDownLoaded.setBackgroundColor(Color.WHITE);
				break;
			}
			case R.id.me_mode_select :{
				Log.d(TAG, "模式选择");
				meModeSelect.setBackgroundColor(Color.WHITE);
				break;
			}
			case R.id.me_help :{
				Log.d(TAG, "帮助");
				meHelp.setBackgroundColor(Color.WHITE);
				break;
			}
			case R.id.me_share :{
				Log.d(TAG, "分享");
				meShare.setBackgroundColor(Color.WHITE);
				break;
			}
			case R.id.me_feedback :{
				Log.d(TAG, "反馈");
				meFeedBack.setBackgroundColor(Color.WHITE);
				Intent intent = new Intent (context,MeFeedBackActivity.class);
				startActivity(intent);
				break;
			}
			case R.id.me_logout :{
				Log.d(TAG, "注销");
				meLogout.setBackgroundColor(Color.parseColor("#ee0f0f"));
				//intent = new Intent (context,MeSettingActivity.class);
				//startActivity(intent);
				break;
			}
			}
		}
	}

	/**监听器*/
	private class MyLinearLayoutOnTouchListener implements OnTouchListener{

		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.me_my_data :{
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					meData.setBackgroundColor(Color.parseColor("#e0e0e0"));
				}
				if(event.getAction()==MotionEvent.ACTION_UP||event.getAction()==MotionEvent.ACTION_CANCEL){
					meData.setBackgroundColor(Color.WHITE);
				}
				break;
			}
			case R.id.me_downloaded_cookbook :{
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					meDownLoaded.setBackgroundColor(Color.parseColor("#e0e0e0"));
				}
				if(event.getAction()==MotionEvent.ACTION_UP||event.getAction()==MotionEvent.ACTION_CANCEL){
					meDownLoaded.setBackgroundColor(Color.WHITE);
				}
				break;
			}
			case R.id.me_mode_select :{
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					meModeSelect.setBackgroundColor(Color.parseColor("#e0e0e0"));
				}
				if(event.getAction()==MotionEvent.ACTION_UP||event.getAction()==MotionEvent.ACTION_CANCEL){
					meModeSelect.setBackgroundColor(Color.WHITE);
				}
				break;
			}
			case R.id.me_help :{
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					meHelp.setBackgroundColor(Color.parseColor("#e0e0e0"));
				}
				if(event.getAction()==MotionEvent.ACTION_UP||event.getAction()==MotionEvent.ACTION_CANCEL){
					meHelp.setBackgroundColor(Color.WHITE);
				}
				break;
			}
			case R.id.me_share :{
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					meShare.setBackgroundColor(Color.parseColor("#e0e0e0"));
				}
				if(event.getAction()==MotionEvent.ACTION_UP||event.getAction()==MotionEvent.ACTION_CANCEL){
					meShare.setBackgroundColor(Color.WHITE);
				}
				break;
			}
			case R.id.me_feedback :{
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					meFeedBack.setBackgroundColor(Color.parseColor("#e0e0e0"));
				}
				if(event.getAction()==MotionEvent.ACTION_UP||event.getAction()==MotionEvent.ACTION_CANCEL){
					meFeedBack.setBackgroundColor(Color.WHITE);
				}
				break;
			}
			case R.id.me_logout :{
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					meLogout.setBackgroundColor(Color.parseColor("#aa0a0a"));
				}
				if(event.getAction()==MotionEvent.ACTION_UP||event.getAction()==MotionEvent.ACTION_CANCEL){
					meLogout.setBackgroundColor(Color.parseColor("#ee0f0f"));
				}
				break;
			}
			}
			if(event.getAction() == MotionEvent.ACTION_MOVE){
				return true;
			}
			return false;
		}		
	}
}