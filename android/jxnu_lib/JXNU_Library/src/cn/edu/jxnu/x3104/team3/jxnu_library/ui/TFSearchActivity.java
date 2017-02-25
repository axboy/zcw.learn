package cn.edu.jxnu.x3104.team3.jxnu_library.ui;

import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class TFSearchActivity extends Activity {
	String search_type;			//顶部搜索类型
	String search_content;		//搜索内容
	String search_option="ALL";		//检索条件
	String search_mode="forward";	//检索模式
	String search_sort="CATA_DATE";	//结果排序

	private Button mSearchButton;
	private TextView mAutoEdit;
	private RadioGroup rg1,rg2,rg3;

	private ImageView iv_cursor;				//动画图片
	private int offset;							//动画偏移量
	private int currentIndex=0;					//当前搜索条件
	private int bmpW;							//动画图片宽度
	private TextView tv1,tv2,tv3,tv4,tv5,tv6;	//搜索条件
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_page);
		init();
		init_search();
		//搜索按钮
		mSearchButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				search_content=mAutoEdit.getText().toString().trim();
				switch (currentIndex){
				case 0:search_type="title";break;
				case 1:search_type="author";break;
				case 2:search_type="publisher";break;
				case 3:search_type="series";break;
				case 4:search_type="callno";break;
				case 5:search_type="coden";break;
				}
				Animation inputbox_shack = AnimationUtils.loadAnimation(
						TFSearchActivity.this, R.anim.loginui_inputbox_warningshack);
				if (mAutoEdit.getText().toString().equals("")) {
					mAutoEdit.startAnimation(inputbox_shack);
				}else{
					new Thread(new Runnable(){
						public void run() {
							Intent intent = new Intent (TFSearchActivity.this,Search_result.class);
							intent.putExtra("type", search_type);			//顶部搜索条件
							intent.putExtra("cont", search_content);		//搜索内容
							intent.putExtra("option", search_option);		//搜索条件
							intent.putExtra("mode", search_mode);			//检索模式
							intent.putExtra("sort", search_sort);	//结果排序
							startActivity(intent);
						}			
					}).start();
				}
			}
		});
		//检索条件
		rg1.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId){
				case 1:search_option="ALL";break;	//所有书刊
				case 2:search_option="01";break;	//中文图书
				case 3:search_option="02";break;	//西文图书
				}
				//System.out.println("rg1");
				//System.out.println(checkedId);
			}
		});
		//检索模式
		rg2.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId){
				case 4:search_mode="forward";break;		//前方一致
				case 5:search_mode="full";break;		//完全匹配
				case 6:search_mode="any";break;			//任意匹配
				}
				//System.out.println("rg2");
				//System.out.println(checkedId);
			}
		});
		//结果排序
		rg3.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId){
				case 7:search_sort="CATA_DATE";break;	//入藏日期
				case 8:search_sort="M_TITLE";break;		//题名
				case 9:search_sort="M_AUTHOR";break;	//责任者
				}
				System.out.println("rg3");
				System.out.println(checkedId);
			}
		});
	}

	private void init_search() {
		mSearchButton = (Button) findViewById(R.id.search_btn);
		mAutoEdit = (TextView) findViewById(R.id.search_et);
		rg1=(RadioGroup)findViewById(R.id.rg1);
		rg2=(RadioGroup)findViewById(R.id.rg2);
		rg3=(RadioGroup)findViewById(R.id.rg3);
		rg1.check(1);
		rg2.check(4);
		rg3.check(7);
	}

	private void init() {
		//初始化头标
		tv1=(TextView) findViewById(R.id.s1);
		tv2=(TextView) findViewById(R.id.s2);
		tv3=(TextView) findViewById(R.id.s3);
		tv4=(TextView) findViewById(R.id.s4);
		tv5=(TextView) findViewById(R.id.s5);
		tv6=(TextView) findViewById(R.id.s6);
		tv1.setOnClickListener(new MyOnClickListener(0));
		tv2.setOnClickListener(new MyOnClickListener(1));
		tv3.setOnClickListener(new MyOnClickListener(2));
		tv4.setOnClickListener(new MyOnClickListener(3));
		tv5.setOnClickListener(new MyOnClickListener(4));
		tv6.setOnClickListener(new MyOnClickListener(5));
		//初始化动画
		iv_cursor=(ImageView)findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a).getWidth();// 获取图片宽度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;					// 获取分辨率宽度
		offset = (screenW-40) / 6;		// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(20+(offset-bmpW)/2, 0);
		iv_cursor.setImageMatrix(matrix);				// 设置动画初始位置
	}

	//头标点击监听
	private class MyOnClickListener implements OnClickListener{
		private int index=0;
		public MyOnClickListener(int i){
			index=i;
		}
		public void onClick(View v) {
			Animation animation = new TranslateAnimation(offset*currentIndex, offset*index, 0, 0);
			currentIndex = index;
			animation.setFillAfter(true);			// True:图片停在动画结束位置
			animation.setDuration(300);
			iv_cursor.startAnimation(animation);
		}
	}
}
