package cn.edu.jxnu.x3104.riceroll.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.bean.Condiment;
import cn.edu.jxnu.x3104.riceroll.bean.CookBook;
import cn.edu.jxnu.x3104.riceroll.bean.FoodMaterial;
import cn.edu.jxnu.x3104.riceroll.db.DbCondiment;
import cn.edu.jxnu.x3104.riceroll.db.DbFoodMaterial;
import cn.edu.jxnu.x3104.riceroll.service.DoLocalPicture;
import cn.edu.jxnu.x3104.riceroll.service.DoSearch;
import cn.edu.jxnu.x3104.riceroll.util.MySlideListView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SimpleAdapter.ViewBinder;

public class MainPageSearchActivity extends Activity implements OnQueryTextListener{
	private final static String TAG = "MainPageSearchActivity";

	//SearchView svSearch;
	int _position;
	EditText etSearch;
	ListView listView;
	ImageView cursor;
	TextView tvFM,tvCB,tvCondiment;
	List<CookBook> listCB;
	List<FoodMaterial> listFM;
	List<Condiment> listCondiment;
	SimpleAdapter sa;
	List<Map<String,Object>> items=new ArrayList<Map<String,Object>>();

	int offset = 0;			//动画图片偏移量
	int currIndex = 0;		//当前页卡编号
	int bmpW;				//动画图片宽度
	int currentIndex=0;		//当前搜索条件

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.mainpage_search);
		init();
		editTextListener();
	}

	/**控件初始化*/
	private void init() {
		// TODO Auto-generated method stub
		listView=(ListView)findViewById(R.id.main_search_listview);

		sa=new SimpleAdapter(
				this,
				items,
				R.layout.mainpage_search_list_item,
				new String[]{"img","text","name"},
				new int[]{R.id.mainpage_search_list_item_img,R.id.mainpage_search_list_item_text,R.id.mainpage_search_list_item_name});

		sa.setViewBinder(new ViewBinder() {
			public boolean setViewValue(View view, Object data, String textRepresentation) { 
				if(view instanceof ImageView  && data instanceof Bitmap){  
					ImageView iv = (ImageView) view; 
					iv.setImageBitmap((Bitmap) data);  
					return true;  
				}else  
					return false;  
			}  
		}); 

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				_position=position;
				mainhandler.sendEmptyMessage(0x400);
			}
		});

		etSearch=(EditText)findViewById(R.id.main_search_et);

		tvCB=(TextView)findViewById(R.id.cookbook_tv);
		tvCB.setSelected(true);
		tvCB.setOnClickListener(new MyOnClickListener(0));

		tvFM=(TextView)findViewById(R.id.fm_tv);
		tvFM.setSelected(false);
		tvFM.setOnClickListener(new MyOnClickListener(1));

		tvCondiment=(TextView)findViewById(R.id.condiment_tv);
		tvCondiment.setSelected(false);
		tvCondiment.setOnClickListener(new MyOnClickListener(2));

		//初始化动画
		cursor=(ImageView)findViewById(R.id.main_search_cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.cursor).getWidth();// 获取图片宽度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		offset = screenW/3;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate((offset-bmpW)/3, 0);
		cursor.setImageMatrix(matrix);// 设置动画初始位置
	}

	/**搜索框输入监听*/
	private void editTextListener() {

		/**搜索框输入内容监听*/
		etSearch.addTextChangedListener(new TextWatcher() {

			//内容刚改变后
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub	
				if(etSearch.getText().toString().equals("")){
					items.clear();
					mainhandler.sendEmptyMessage(0x300);
					return ;
				}
				switch(currentIndex){
				case 0:break;
				case 1:
					//搜食材
					items.clear();
					listFM=new DbFoodMaterial().searchDataByKeyWord(etSearch.getText().toString());
					for(FoodMaterial fm:listFM){
						HashMap<String,Object> map=new HashMap<String,Object>();
						map.put("name",fm.getFoodMaterial_name());
						map.put("text",fm.getFoodMaterial_calories()+"卡");
						String []links=fm.getFoodMaterial_photo_link().split(";");
						Bitmap  b=new DoLocalPicture().readFromSD("FOODMATERIALIMAGE", links[0]);
						map.put("img",b);
						items.add(map);
					}
					mainhandler.sendEmptyMessage(0x300);
					break;

				case 2:
					//搜调料
					items.clear();
					listCondiment=new DbCondiment().searchCondimentByKeyWord(etSearch.getText().toString());
					for(Condiment c:listCondiment){
						HashMap<String,Object> map=new HashMap<String,Object>();
						map.put("name",c.getCondiment_name());
						map.put("text",c.getCondiment_calories()+"卡");
						String []links=c.getCondiment_photo_link().split(";");
						Bitmap  b=new DoLocalPicture().readFromSD("CONDIMENTIMAGE", links[0]);
						map.put("img",b);
						items.add(map);
					}
					mainhandler.sendEmptyMessage(0x300);
					break;
				}
			}

			//编辑之前文本框的内容
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			//文字输入完后，比如提示字数超限制
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		/**回车键监听*/
		etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				if(actionId==EditorInfo.IME_ACTION_SEARCH){
					new Thread(new Runnable(){
						public void run() {
							// TODO Auto-generated method stub
							items.clear();
							if(etSearch.getText().toString().equals("")){
								return ;
							}
							switch(currentIndex){
							case 0:
								//搜菜谱
								listCB=new DoSearch().searchCookBookByKeyWord(etSearch.getText().toString(),MainPageSearchActivity.this);
								for(CookBook cb:listCB){
									HashMap<String,Object> map=new HashMap<String,Object>();
									map.put("name",cb.getCookbook_name());
									map.put("text",cb.getCookbook_brief_introduction());
									String []links=cb.getCookbook_photo_link().split(";");
									Bitmap  b=new DoLocalPicture().getServerPicture("COOKBOOKIMAGE", links[0]);
									map.put("img",b);
									items.add(map);
								}
								break;
							case 1:
								//搜食材
								listFM=new DbFoodMaterial().searchDataByKeyWord(etSearch.getText().toString());
								for(FoodMaterial fm:listFM){
									HashMap<String,Object> map=new HashMap<String,Object>();
									map.put("name",fm.getFoodMaterial_name());
									map.put("text",fm.getFoodMaterial_calories()+"卡");
									String []links=fm.getFoodMaterial_photo_link().split(";");
									Bitmap  b=new DoLocalPicture().readFromSD("FOODMATERIALIMAGE", links[0]);
									map.put("img",b);
									items.add(map);
								}
								break;
							case 2:
								//搜调料
								listCondiment=new DbCondiment().searchCondimentByKeyWord(etSearch.getText().toString());
								for(Condiment c:listCondiment){
									HashMap<String,Object> map=new HashMap<String,Object>();
									map.put("name",c.getCondiment_name());
									map.put("text",c.getCondiment_calories()+"卡");
									String []links=c.getCondiment_photo_link().split(";");
									Bitmap  b=new DoLocalPicture().readFromSD("CONDIMENTIMAGE", links[0]);
									map.put("img",b);
									items.add(map);
								}
								break;
							}
							mainhandler.sendEmptyMessage(0x300);
						}						
					}).start();					
				}
				return false;
			}
		});
	}


	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub

		return false;
	}

	/**游标监听*/
	private class MyOnClickListener implements OnClickListener{
		private int index=0;
		public MyOnClickListener(int i){
			index=i;
		}
		public void onClick(View v) {
			//viewpager.setCurrentItem(index);
			Animation animation = new TranslateAnimation(offset*currentIndex, offset*index, 0, 0);
			currentIndex = index;
			animation.setFillAfter(true);			// True:图片停在动画结束位置
			animation.setDuration(300);
			cursor.startAnimation(animation);
			switch(index){
			case 0:
				tvCB.setSelected(true);
				tvFM.setSelected(false);
				tvCondiment.setSelected(false);
				items.clear();
				sa.notifyDataSetChanged();
				break;
			case 1:
				tvCB.setSelected(false);
				tvFM.setSelected(true);
				tvCondiment.setSelected(false);
				items.clear();
				sa.notifyDataSetChanged();
				break;
			case 2:
				tvCB.setSelected(false);
				tvFM.setSelected(false);
				tvCondiment.setSelected(true);
				items.clear();
				sa.notifyDataSetChanged();
				break;
			}
		}		
	}

	/**处理消息*/
	private Handler mainhandler=new Handler(){
		public void handleMessage(Message msg){
			/**0x300加载到适配器*/
			if(msg.what==0x300){
				listView.setAdapter(sa);
			}
			/**0x400点击事件*/
			else if(msg.what==0x400){
				Intent intent=new Intent();
				switch(currentIndex){
				case 0:
					intent.putExtra("cookbook_detail",listCB.get(_position).getCookbook_name());
					intent.setClass(MainPageSearchActivity.this,CookBookDetailActivity.class);
					startActivity(intent);
					break;
				case 1:
					intent.putExtra("open_foodMaterial_detail",listFM.get(_position).getFoodMaterial_id());
					intent.setClass(MainPageSearchActivity.this,MainPageFoodMaterialDetailActivity.class);
					startActivity(intent);
					break;
				case 2:
					intent.putExtra("open_condiment_detail",listCondiment.get(_position).getCondiment_id());
					intent.setClass(MainPageSearchActivity.this,MainPageCondimentDetailActivity.class);
					startActivity(intent);
					break;
				}
			}
		}
	};
}
