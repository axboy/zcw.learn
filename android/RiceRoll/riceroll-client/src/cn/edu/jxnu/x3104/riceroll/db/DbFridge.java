package cn.edu.jxnu.x3104.riceroll.db;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.jxnu.x3104.riceroll.bean.FoodMaterial;
import cn.edu.jxnu.x3104.riceroll.util.MyConstants;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DbFridge {
	private final static String TAG = "DbFridge";
	SQLiteDatabase db;
	
	/**构造函数，创建或打开数据库，存在则不创建*/
	public DbFridge(){
		File folder=new File("/data/data/cn.edu.jxnu.x3104.riceroll/database/");
		if(!folder.exists()){
			folder.mkdir();
		}
		db=SQLiteDatabase.openOrCreateDatabase(MyConstants.DB_URL,null);
	}
	
	/**获取冰箱内食材*/
	public List<FoodMaterial> getAllData(){
		List<FoodMaterial> foodMaterialList=new ArrayList<FoodMaterial>();
		Cursor cursor=db.rawQuery("select * from Fridge", null);
		for(;cursor.moveToNext();){
			String FoodMaterial_id=cursor.getString(0);
			foodMaterialList.add(new DbFoodMaterial().searchDataById(FoodMaterial_id));
		}
		this.onDestory();
		return foodMaterialList;
	}
	
	/**搜索冰箱，判断某食材是否在冰箱里*/
	public boolean isInFridge(String id){
		Cursor cursor=db.rawQuery("select * from Fridge where FoodMaterial_id like ?",new String[]{id});
		if(cursor.getCount()==0){
			this.onDestory();
			return false;
		}else{
			this.onDestory();
			return true;
		}
	}
	
	/**往冰箱增加食材*/
	public void addFoodMaterialToFridge(String id){
		String time=new Date().toLocaleString();	//示例：2015-5-26 12:54:38
		db.execSQL("insert into Fridge values(?,?)",new String[]{id,time});
		Log.d(TAG,"成功往冰箱里加入了"+id+"号食材");
		this.onDestory();
	}
	
	/**移除冰箱内食材*/
	public void removeFoodMaterialById(String id){
		db.execSQL("delete from Fridge where FoodMaterial_id like ?",new String[]{id});
		Log.d(TAG,"成功从冰箱里移除了"+id+"号食材");
		this.onDestory();
	}
	
	/**清空冰箱*/
	public void delAll(){
		db.execSQL("delete from Fridge"); 
		Log.d(TAG,"成功清空了冰箱");
		this.onDestory();
	}
	
	/**创建冰箱表*/
	public void createTableFridge(){
		/**食材ID，放入时间*/
		db.execSQL("create table Fridge("
				+ "FoodMaterial_id char(10) primary key,"
				+ "put_in_time char(50)"
				+ ")");
		Log.d(TAG, "成功创建了Fridge表");
		this.onDestory();
	}
	
	/**关闭数据库连接*/
	public void onDestory(){
		if(db!=null && db.isOpen()){
			db.close();
		}
	}
}
