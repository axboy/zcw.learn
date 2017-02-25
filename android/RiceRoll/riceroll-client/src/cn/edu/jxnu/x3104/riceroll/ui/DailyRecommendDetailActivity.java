package cn.edu.jxnu.x3104.riceroll.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SimpleAdapter.ViewBinder;
import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.bean.DailyRecommend;
import cn.edu.jxnu.x3104.riceroll.db.DbDailyRecommend;
import cn.edu.jxnu.x3104.riceroll.service.DoLocalPicture;

public class DailyRecommendDetailActivity extends Activity{
	private final static String TAG = "DailyRecommendDetailActivity";

	private ViewPager pager;
	//private int mCurrentItem = 0;
	private ArrayList<View> items = new ArrayList<View>();
	
	TextView tvName,tvIntroduction,tvReason;
	
	/**总图片数*/
	private int mItem;
	
	String cookBookId;

	PagerAdapter pa;
	OnPageChangeListener ocl;
	DailyRecommend dr;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.daily_recommend_detail);
		initPagerAdapter();
		init();
	}

	private void init() {
		tvName=(TextView)findViewById(R.id.daily_name);
		tvIntroduction=(TextView)findViewById(R.id.daily_introduction_text);
		tvReason=(TextView)findViewById(R.id.daily_recommend_reason);
		pager = (ViewPager) findViewById(R.id.daily_viewpager_img);
		new Thread(new Runnable(){
			public void run(){
				Intent intent=getIntent();
				cookBookId=intent.getStringExtra("open_daily_recommend_detail");
				dr=new DbDailyRecommend().getDateDetail(cookBookId);
				String []img_links=dr.getImg_path().split(";");
				int temp_num=0;
				for(String link:img_links){
					items.add(initPagerItem(link,++temp_num));
				}
				mItem = items.size();
				
				mainhandler.sendEmptyMessage(0x300);
			}
		}).start();
	}

	/**初始化滑动监听事件，没有使用*/
	/*
	public void initOnPageChangeListener(){
		ocl=new OnPageChangeListener(){
			@Override
			public void onPageSelected(final int arg0)
			{
				mCurrentItem = arg0 % items.size();
				pager.setCurrentItem(mCurrentItem);
				items.get(arg0).findViewById(R.id.tuijian_header_img).setOnClickListener(new OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						Toast.makeText(DailyRecommendDetailActivity.this, arg0 + "", 1000).show();
					}
				});
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
	*/

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

	/**把数据库图片加载进ViewPager*/
	private View initPagerItem(String link,int num){
		View layout1 = getLayoutInflater().inflate(R.layout.daily_recommend_detail_item, null);
		ImageView imageView1 = (ImageView) layout1.findViewById(R.id.daily_recommend_detail_header_img);
		TextView tv1=(TextView) layout1.findViewById(R.id.daily_recommend_detail_header_num);
		Bitmap  b=new DoLocalPicture().readFromSD("RECOMMEND",link);
		imageView1.setImageBitmap(b);
		tv1.setText(""+num);
		return layout1;
	}
	
	/**处理消息*/
	private Handler mainhandler=new Handler(){
		public void handleMessage(Message msg){
			/**0x300加载到适配器*/
			if(msg.what==0x300){
				pager.setAdapter(pa);
				tvName.setText(dr.getCookbook_name());
				tvIntroduction.setText(dr.getIntroduction_text());
				tvReason.setText(dr.getRecommend_reason());
			}
		}
	};
	
	/**查看菜谱详情*/
	public void openCookBookDetail(View v){
		Intent intent=new Intent(DailyRecommendDetailActivity.this,CookBookDetailActivity.class);
		intent.putExtra("cookbook_detail",dr.getCookbook_name());
		startActivity(intent);
	}
	
	public void exit(View v){
		this.finish();
	}
}

