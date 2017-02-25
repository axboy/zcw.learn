package cn.edu.jxnu.x3104.riceroll.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.bean.FoodMaterial;
import cn.edu.jxnu.x3104.riceroll.db.DbFridge;
import cn.edu.jxnu.x3104.riceroll.service.DoLocalPicture;
import cn.edu.jxnu.x3104.riceroll.util.MySlideListView;
import cn.edu.jxnu.x3104.riceroll.util.MySlideView;
import cn.edu.jxnu.x3104.riceroll.util.MySlideView.OnSlideListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter.ViewBinder;

public class UserFridgeActivity extends Activity implements OnClickListener,OnSlideListener{
	private final static String TAG = "UserFridgeActivity";

	private MySlideListView listView;
	private List<FoodMaterial> list;
	private int _position;
	private SlideBaseAdapter sba;
	private MySlideView mLastSlideViewWithStatusOn;
	private List<MessageItem> items=new ArrayList<MessageItem>();

	/**创建视图*/
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	

		if(isFridgeNull()){
			//不是空
			setContentView(R.layout.fridge_list);
			init();
			getFoodMaterialFromDbFridge();
		}else{
			//空
			setContentView(R.layout.fridge_null);
		}
	}

	/**判断冰箱是否为空*/
	private Boolean isFridgeNull() {
		try{
			List<FoodMaterial> list=new DbFridge().getAllData();
			Log.d(TAG, "size="+list.size());
			if(list.size()==0){
				return false;
			}
		}catch(SQLException e){
			new DbFridge().createTableFridge();
			return false;
		}
		return true;
	}	

	/**初始化组件*/
	private void init() {
		listView=(MySlideListView)findViewById(R.id.user_fridge_list);
		
		sba=new SlideBaseAdapter();

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				_position=position;	
				MySlideView slideView =  items.get(position).mySlideView;
				if(slideView.ismIsMoveClick()){			//如果是滑动中触发的点击事件，不做处理
					return;
				}
				if (slideView.close() && slideView.getScrollX() == 0) {
					if (mLastSlideViewWithStatusOn == null || mLastSlideViewWithStatusOn.getScrollX() == 0) {
						mainhandler.sendEmptyMessage(0x400);
					}
				}
			}
		});	
		
	}


	/**获取数据库里的调料列表*/
	private void getFoodMaterialFromDbFridge() {
		list=new DbFridge().getAllData();
		items.clear();
		for(FoodMaterial fm:list){
			String []links=fm.getFoodMaterial_photo_link().split(";");
			Bitmap b=new DoLocalPicture().readFromSD("FOODMATERIALIMAGE",links[0]);
			MessageItem item=new MessageItem();
			item.iconRes=b;
			item.name=fm.getFoodMaterial_name();
			item.time=fm.getFoodMaterial_calories()+"卡";
			items.add(item);
		}
		mainhandler.sendEmptyMessage(0x300);
	}
	
	/**处理消息*/
	private Handler mainhandler=new Handler(){
		public void handleMessage(Message msg){
			/**0x300加载到适配器*/
			if(msg.what==0x300){
				listView.setAdapter(sba);
			}
			/**0x400点击事件*/
			else if(msg.what==0x400){
				Intent intent=new Intent();
				intent.putExtra("open_foodMaterial_detail",list.get(_position).getFoodMaterial_id());
				intent.setClass(UserFridgeActivity.this,MainPageFoodMaterialDetailActivity.class);
				startActivity(intent);
			}
		}			
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.my_slide_view_right_holder) {
			//Log.e(TAG, "onClick v=" + v);
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("提示").setIcon(R.drawable.ic_launcher).setMessage("确定删此条目？")  
			.setNegativeButton("取消", null);
			builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					new DbFridge().removeFoodMaterialById(list.get(listView.getPosition()).getFoodMaterial_id());
					items.remove(listView.getPosition());
					sba.notifyDataSetChanged();
				}
			});
			builder.show();
		}
	}

	@Override
	public void onSlide(View view, int status) {
		if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
			mLastSlideViewWithStatusOn.shrink();
		}

		if (status == SLIDE_STATUS_ON) {
			mLastSlideViewWithStatusOn = (MySlideView) view;
		}
	}

	/***************分割线***************/

	public class MessageItem {
		public Bitmap iconRes;
		public String name;
		public String time;
		public MySlideView mySlideView;
	}

	private static class ViewHolder {
		public ImageView icon;
		public TextView name;
		public TextView time;
		public ViewGroup rightHolder;

		ViewHolder(View view) {
			icon = (ImageView) view.findViewById(R.id.user_fridge_list_item_img);
			name = (TextView) view.findViewById(R.id.user_fridge_list_item_name);
			time = (TextView) view.findViewById(R.id.user_fridge_list_item_time);
			rightHolder = (ViewGroup)view.findViewById(R.id.my_slide_view_right_holder);
		}
	}

	public class SlideBaseAdapter extends BaseAdapter{
		private LayoutInflater mInflater;
		
		SlideBaseAdapter() {
			super();
			mInflater = getLayoutInflater();
		}

		public int getCount() {
			return items.size();
		}

		@Override
		public Object getItem(int position) {
			return items.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			MySlideView slideView = (MySlideView) convertView;
			if (slideView == null) {
				View itemView = mInflater.inflate(R.layout.fridge_list_item, null);

				slideView = new MySlideView(UserFridgeActivity.this);
				slideView.setContentView(itemView);

				holder = new ViewHolder(slideView);
				slideView.setOnSlideListener(UserFridgeActivity.this);
				slideView.setTag(holder);
			} else {
				holder = (ViewHolder) slideView.getTag();
			}
			MessageItem item = items.get(position);
			item.mySlideView = slideView;
			item.mySlideView.reset();
			holder.icon.setImageBitmap(item.iconRes);
			holder.name.setText(item.name);
			holder.time.setText(item.time);
			holder.rightHolder.setOnClickListener(UserFridgeActivity.this);
			return slideView;
		}
	}

	/**选择按钮*/
	public void openSelectOpinion(View view){
		Intent intent=new Intent(UserFridgeActivity.this,UserFridgeSelectActivity.class);
		startActivity(intent);
	}
	public void exit(View v){
		this.finish();
	}

}
