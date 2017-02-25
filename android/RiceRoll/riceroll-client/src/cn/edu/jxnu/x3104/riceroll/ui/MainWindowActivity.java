package cn.edu.jxnu.x3104.riceroll.ui;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jxnu.x3104.riceroll.R;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainWindowActivity extends FragmentActivity{
	private final static String TAG = "MainWindowActivity";

	private ViewPager viewpager;
	private ImageView cursor;
	private TextView main,foodMaterialList,condiment,me;
	private List<Fragment> views;	//Tab页面列表
	private int offset = 0;			//动画图片偏移量
	private int currIndex = 0;		//当前页卡编号
	private int bmpW;				//动画图片宽度
	//private View viewMain,viewFoodMaterialList,viewLocal,viewMe;	//各个页卡

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_window);
		init();
	}

	/**初始化Fragment*/
	private void initView() {
		MainPageMainFragment pageMain=new MainPageMainFragment();
		MainPageCondimentFragment pageLocal=new MainPageCondimentFragment();
		MainPageMeFragment pageMe=new MainPageMeFragment();
		MainPageFoodMaterialFragment pageFoodMaterial=new MainPageFoodMaterialFragment();
		pageMain.SetContext(this);
		pageLocal.SetContext(this);
		pageMe.SetContext(this);
		pageFoodMaterial.SetContext(this);
		views.add(pageMain);
		views.add(pageFoodMaterial);
		views.add(pageLocal);
		views.add(pageMe);
		viewpager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
	}

	/**初始化控件*/
	private void init() {
		viewpager=(ViewPager) findViewById(R.id.main_pager);
		views=new ArrayList<Fragment>();
		LayoutInflater inflater=getLayoutInflater();
		this.initView();
		viewpager.setCurrentItem(0);
		viewpager.setOnPageChangeListener(new MyOnPageChangeListener());

		//初始化头标
		main=(TextView)findViewById(R.id.main_main);
		main.setOnClickListener(new MyOnClickListener(0));
		main.setSelected(true);

		foodMaterialList=(TextView)findViewById(R.id.main_food_material);
		foodMaterialList.setOnClickListener(new MyOnClickListener(1));
		foodMaterialList.setSelected(false);		

		condiment=(TextView)findViewById(R.id.main_local);
		condiment.setOnClickListener(new MyOnClickListener(2));
		condiment.setSelected(false);

		me=(TextView)findViewById(R.id.main_me);
		me.setOnClickListener(new MyOnClickListener(3));
		me.setSelected(false);

		//初始化动画
		cursor=(ImageView)findViewById(R.id.main_cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.cursor).getWidth();// 获取图片宽度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		offset = screenW/4;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate((offset-bmpW)/2, 0);
		cursor.setImageMatrix(matrix);// 设置动画初始位置
	}

	private class MyOnClickListener implements OnClickListener{
		private int index=0;
		public MyOnClickListener(int i){
			index=i;
		}
		public void onClick(View v) {
			viewpager.setCurrentItem(index);			
		}		
	}	

	public class MyViewPagerAdapter extends FragmentPagerAdapter{

		public MyViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return views.get(arg0);
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener{
		public void onPageScrollStateChanged(int arg0) {			

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {			

		}

		public void onPageSelected(int arg0) {
			Animation animation = new TranslateAnimation(offset*currIndex, offset*arg0, 0, 0);
			currIndex = arg0;
			switch(currIndex){
			case 0:main.setSelected(true);foodMaterialList.setSelected(false);condiment.setSelected(false);me.setSelected(false);break;
			case 1:main.setSelected(false);foodMaterialList.setSelected(true);condiment.setSelected(false);me.setSelected(false);break;
			case 2:main.setSelected(false);foodMaterialList.setSelected(false);condiment.setSelected(true);me.setSelected(false);break;
			case 3:main.setSelected(false);foodMaterialList.setSelected(false);condiment.setSelected(false);me.setSelected(true);break;
			}
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			cursor.startAnimation(animation);
			//Toast.makeText(MainWindowActivity.this,"", Toast.LENGTH_SHORT).show();
		}    	
	}
	
	public void openFridge(View v){
		Intent intent = new Intent (MainWindowActivity.this,UserFridgeActivity.class);						
		startActivity(intent);
	}
	
	public void openSearch(View v){
		Intent intent = new Intent (MainWindowActivity.this,MainPageSearchActivity.class);						
		startActivity(intent);
	}
	
	public void openToadyCookBook(View v){
		Log.d(TAG,"openToadyCookBook");
		Intent intent = new Intent (MainWindowActivity.this,DailyCookBookListActivity.class);						
		startActivity(intent);
	}
}
