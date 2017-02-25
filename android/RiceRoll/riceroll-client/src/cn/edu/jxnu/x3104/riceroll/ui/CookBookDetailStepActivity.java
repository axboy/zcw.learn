package cn.edu.jxnu.x3104.riceroll.ui;

import java.util.ArrayList;

import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.bean.User;
import cn.edu.jxnu.x3104.riceroll.service.DoFeedBack;
import cn.edu.jxnu.x3104.riceroll.service.DoHttp;
import cn.edu.jxnu.x3104.riceroll.service.DoLocalPicture;
import cn.edu.jxnu.x3104.riceroll.service.DoSearch;
import cn.edu.jxnu.x3104.riceroll.service.DoUserInfo;
import cn.edu.jxnu.x3104.riceroll.ui.CookBookDetailActivity.MyAsyncTask;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CookBookDetailStepActivity extends Activity{
	private final static String TAG = "CookBookDetailStepActivity";

	ViewPager pager;
	ArrayList<View> items = new ArrayList<View>();
	Button before,next;
	TextView tvStepNum;	

	PagerAdapter pa;
	OnPageChangeListener ocl;

	int mItem=0;

	int mCurrentItem=0;

	String links,step;
	MyAsyncTask mat;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cookbook_step);
		initPagerAdapter();
		init();
		new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent=getIntent();
				links=intent.getStringExtra("cookbook_img_links");
				step=intent.getStringExtra("cookbook_step");
				mat=new MyAsyncTask();
				mat.execute();
			}			
		}).start();
	}

	private void init() {
		// TODO Auto-generated method stub
		pager=(ViewPager)findViewById(R.id.cookbook_step_viewpager);
		before=(Button)findViewById(R.id.cookbook_step_before);
		next=(Button)findViewById(R.id.cookbook_step_next);
		tvStepNum=(TextView)findViewById(R.id.cookbook_step_num);
	}

	class MyAsyncTask extends AsyncTask<Void , Void, Void>{
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub	
			if(step.equals("0")){
				items.add(initPagerItem("0","0"));
			}else{
				String []ss=links.split(";");
				String []steps=step.split(";");
				for(int i=0;i<steps.length;i++){
					items.add(initPagerItem(ss[i+2],steps[i]));
				}
			}
			mItem=items.size();
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			pager.setAdapter(pa);
			pager.setOnPageChangeListener(ocl);
		}
	}

	/**初始化适配器*/
	public void initPagerAdapter(){
		pa=new PagerAdapter()
		{
			// 创建
			public Object instantiateItem(View container, int position)
			{
				View layout = items.get(position % items.size());
				pager.addView(layout);
				return layout;
			}
			// 销毁
			public void destroyItem(View container, int position, Object object)
			{
				View layout = items.get(position % items.size());
				pager.removeView(layout);
			}

			public boolean isViewFromObject(View arg0, Object arg1)
			{
				return arg0 == arg1;
			}

			public int getCount()
			{
				return mItem;
			}
		};
	}

	/**初始化滑动监听事件*/
	public void initOnPageChangeListener(){
		ocl=new OnPageChangeListener(){
			@Override
			public void onPageSelected(final int arg0)
			{
				mCurrentItem=arg0;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2)
			{
				// TODO Auto-generated method stub
			}

			@Override
			public void onPageScrollStateChanged(int arg0)
			{
				// TODO Auto-generated method stub
			}
		};
	}

	/**加载ViewPager*/
	private View initPagerItem(String link,String step){
		View layout1 = getLayoutInflater().inflate(R.layout.cookbook_step_item, null);
		ImageView imageView1 = (ImageView) layout1.findViewById(R.id.cookbook_step_img);
		TextView tv1=(TextView) layout1.findViewById(R.id.cookbook_step_text);
		if(link.equals("0")){
			imageView1.setImageResource(R.drawable.cookbook_no_step);
			tv1.setText("没有步骤，不服？");
		}else{
			Bitmap  b=new DoLocalPicture().getServerPicture("COOKBOOKIMAGE",link);
			imageView1.setImageBitmap(b);
			tv1.setText("   "+step);
		}
		return layout1;
	}
	
	
	public void exit(View v){
		this.finish();
	}
}
