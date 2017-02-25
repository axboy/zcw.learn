package cn.edu.jxnu.x3104.riceroll.ui;

import java.util.HashMap;
import java.util.List;

import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.bean.FoodMaterial;
import cn.edu.jxnu.x3104.riceroll.db.DbFridge;
import cn.edu.jxnu.x3104.riceroll.service.DoLocalPicture;
import android.app.Activity;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class UserFridgeSelectActivity extends Activity{
	private final static String TAG = "UserFridgeSelectActivity";

	private HashMap<Integer,Boolean> isSelected=new HashMap<Integer, Boolean>();
	private ListView listView;
	private List<FoodMaterial> list;
	private SlideBaseAdapter sba;

	/**创建视图*/
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	

		if(isFridgeNull()){
			//不是空
			setContentView(R.layout.fridge_select_list);
			init();
		}else{
			//空
			setContentView(R.layout.fridge_null);
		}
	}

	/**判断冰箱是否为空*/
	private Boolean isFridgeNull() {
		try{
			List<FoodMaterial> list=new DbFridge().getAllData();
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
		
		listView=(ListView)findViewById(R.id.user_fridge_select_list);		
		
		sba=new SlideBaseAdapter();
		
		list=new DbFridge().getAllData();
		
		for(int i=0;i<list.size();i++){
			isSelected.put(i,false);
		}
		
		listView.setAdapter(sba);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				
				ViewHolder holder = (ViewHolder) view.getTag();
				holder.checkBox.toggle();
				Log.d(TAG, position+"_"+holder.checkBox.isChecked());
			}
		});	
		
	}

	private static class ViewHolder {
		public ImageView icon;
		public TextView name;
		public TextView text;
		public CheckBox checkBox;
	}

	public class SlideBaseAdapter extends BaseAdapter{
		private LayoutInflater mInflater;
		
		SlideBaseAdapter() {
			super();
			mInflater = getLayoutInflater();
		}

		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder=null;
			if (convertView == null) {
				holder=new ViewHolder();
				convertView = mInflater.inflate(R.layout.fridge_select_list_item, null);
				holder.icon = (ImageView) convertView.findViewById(R.id.user_fridge_select_list_item_img);
				holder.name = (TextView) convertView.findViewById(R.id.user_fridge_select_list_item_name);
				holder.text = (TextView) convertView.findViewById(R.id.user_fridge_select_list_item_time);
				holder.checkBox = (CheckBox) convertView.findViewById(R.id.user_fridge_select_list_item_checkbox);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			String []links=list.get(position).getFoodMaterial_photo_link().split(";");
			Bitmap  b=new DoLocalPicture().readFromSD("FOODMATERIALIMAGE", links[0]);	
			holder.name.setText(list.get(position).getFoodMaterial_name());		
			holder.text.setText(list.get(position).getFoodMaterial_calories()+"卡");		
			holder.icon.setImageBitmap(b);
			holder.checkBox.setChecked(isSelected.get(position));
			return convertView;
		}
	}
	
	public void desSelect(View view){
		for(int i=0;i<list.size();i++){
			if(isSelected.get(i)){
				isSelected.put(i,false);
			}else{
				isSelected.put(i,true);
			}
		}
		sba.notifyDataSetChanged();
	}


	public void exit(View v){
		this.finish();
	}
}
