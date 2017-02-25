package cn.edu.jxnu.x3104.riceroll.ui;

import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.service.DoFeedBack;
import cn.edu.jxnu.x3104.riceroll.service.DoSharedPreferences;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class MeFeedBackActivity extends Activity{
	private final static String TAG = "MeFeedBackActivity";
	
	EditText feedbackContent;
	RadioGroup feedbackType;
	String type;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_feedback);
		init();
		feedbackType.setOnCheckedChangeListener(new OnCheckedChangeListener() {			
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==1){
					type="suggestion";
				}else{
					type="feedback";
				}
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		feedbackContent=(EditText)findViewById(R.id.feedback_content);
		feedbackType=(RadioGroup)findViewById(R.id.feedback_rg);
		feedbackType.check(1);
	}
	
	/**反馈提交按钮*/
	public void feedbackSubmit(View view){
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int result=new DoFeedBack().feedBackToServer(
						new DoSharedPreferences().getLoginUserId(MeFeedBackActivity.this),type,
						feedbackContent.getText().toString(),MeFeedBackActivity.this);
				if(result!=5003){
					//Toast.makeText(MeFeedBackActivity.this,"反馈成功！",Toast.LENGTH_SHORT);
					Log.d(TAG,""+result);
				}
			}
			
		}).start();
		
	}
	
	public void exit(View v){
		this.finish();
	}
}
