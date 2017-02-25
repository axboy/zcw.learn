package cn.edu.jxnu.x3104.riceroll.start;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.ui.MainWindowActivity;
import cn.edu.jxnu.x3104.riceroll.ui.MainWindowActivityUnlogin;
import cn.edu.jxnu.x3104.riceroll.ui.UserLoginActivity;
import cn.edu.jxnu.x3104.riceroll.ui.UserRegisterActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

public class AppGuide extends Activity {
	private final static String TAG = "AppGuide";
	private ViewPager viewPager;	//页卡内容
	private List<View> views;		// Tab页面列表
	private View view1,view2,view3;	//各个页卡
	private RadioGroup radioGroup;
    private int[] radioButtonID = new int[]
    		{ R.id.radio0, R.id.radio1, R.id.radio2 };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_guide);
		InitViewPager();
	}

	private void InitViewPager() {
		viewPager=(ViewPager) findViewById(R.id.vPager);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		views=new ArrayList<View>();
		LayoutInflater inflater=getLayoutInflater();
		view1=inflater.inflate(R.layout.app_guide1, null);
		view2=inflater.inflate(R.layout.app_guide2, null);
		view3=inflater.inflate(R.layout.app_guide3, null);
		views.add(view1);
		views.add(view2);
		views.add(view3);
		viewPager.setAdapter(new MyViewPagerAdapter(views));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}
	
	public class MyViewPagerAdapter extends PagerAdapter{
		private List<View> mListViews;

		public MyViewPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) 	{	
			container.removeView(mListViews.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {			
			container.addView(mListViews.get(position), 0);
			return mListViews.get(position);
		}

		@Override
		public int getCount() {			
			return  mListViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {			
			return arg0==arg1;
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener{
		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageSelected(int arg0) {
            radioGroup.check(radioButtonID[arg0]);
		}

	}
	
	/**引导页的开始按钮*/
	public void startbutton(View v) {  
		Intent intent = new Intent();
		intent.setClass(AppGuide.this,MainWindowActivityUnlogin.class);
		startActivity(intent);
		this.finish();
	}  
	public void toLogin(View v){		 
		Intent intent = new Intent();
		intent.setClass(AppGuide.this,UserLoginActivity.class);
		startActivity(intent);
	}  
	public void toRegister(View v){		 
		Intent intent = new Intent();
		intent.setClass(AppGuide.this,UserRegisterActivity.class);
		startActivity(intent);
	}
}
