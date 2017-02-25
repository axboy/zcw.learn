package cn.edu.jxnu.x3104.team3.jxnu_library.ui;

import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("CutPasteId")
public class MainTopRightDialog extends Activity {
	//private MyDialog dialog;
	@SuppressWarnings("unused")
	private TextView toLogout;
	@SuppressWarnings("unused")
	private TextView toExit;
	@SuppressWarnings("unused")
	private LinearLayout layout;
	private int screenWidth;	//屏幕宽度
	private int screenHight;	//屏幕高度
	private LinearLayout.LayoutParams params;
	private View view;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_top_right);
//		toLogout=(TextView)findViewById(R.id.to_Logout);
//		toExit=(TextView)findViewById(R.id.to_Exit);

		WindowManager window = (WindowManager) getSystemService(Context.WINDOW_SERVICE);  
		screenWidth = window.getDefaultDisplay().getWidth();  	//获得屏幕宽度
		screenHight	= window.getDefaultDisplay().getHeight();
		layout=(LinearLayout)findViewById(R.id.main_dialog_layout);

		view=findViewById(R.id.main_dialog_layout);
		params = (LinearLayout.LayoutParams) view.getLayoutParams(); 
		params.setMargins(screenWidth-150, 46, 0, screenHight-146);
	}
	public boolean onTouchEvent(MotionEvent event){
		finish();
		return true;
	}
}
