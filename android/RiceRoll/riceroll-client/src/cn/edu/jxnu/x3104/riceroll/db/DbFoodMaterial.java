package cn.edu.jxnu.x3104.riceroll.db;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import cn.edu.jxnu.x3104.riceroll.bean.Condiment;
import cn.edu.jxnu.x3104.riceroll.bean.FoodMaterial;
import cn.edu.jxnu.x3104.riceroll.service.DoLocalPicture;
import cn.edu.jxnu.x3104.riceroll.util.MyConstants;

/**食材*/
public class DbFoodMaterial {
	private final static String TAG = "DbFoodMaterial";
	SQLiteDatabase db;

	/**构造函数，创建或打开数据库，存在则不创建*/
	public DbFoodMaterial(){
		File folder=new File("/data/data/cn.edu.jxnu.x3104.riceroll/database/");
		if(!folder.exists()){
			folder.mkdir();
		}
		db=SQLiteDatabase.openOrCreateDatabase(MyConstants.DB_URL,null);
	}

	/**插入数据,添加食材*/
	public void insertData(FoodMaterial fm){
		db.execSQL("insert into FoodMaterial values(?,?,?,?,?,?,?,?,?,?,?,?,?)",new Object[]{
				fm.getFoodMaterial_id(),fm.getFoodMaterial_name(),
				fm.getFoodMaterial_photo_link(),fm.getFoodMaterial_protein(),
				fm.getFoodMaterial_sugar(),fm.getFoodMaterial_fat(),
				fm.getFoodMaterial_dietary_fiber(),fm.getFoodMaterial_vc(),
				fm.getFoodMaterial_calcium(),fm.getFoodMaterial_muriate(),
				fm.getFoodMaterial_natrium(),fm.getFoodMaterial_phosphorus(),
				fm.getFoodMaterial_calories() });
		this.onDestory();
		//避免文件夹不存在
		File folder=new File(new MyConstants().IMG_URL+"FOODMATERIALIMAGE/");
		if(!folder.exists()){
			folder.mkdir();
		}
		//分割字符串
		String []links=fm.getFoodMaterial_photo_link().split(";");
		for(String link:links){
			new DoLocalPicture().writeToSD("FOODMATERIALIMAGE",link);
		}
	}

	/**删除全部*/
	public void deleteAllData(){
		db.execSQL("delete from FoodMaterial"); 
		Log.d(TAG, "成功清空了食材表");
		this.onDestory();
	}

	/**搜索食材*/
	public FoodMaterial searchDataById(String id){
		Cursor cursor=db.rawQuery("select * from FoodMaterial where FoodMaterial_id = ?",new String[]{id});
		cursor.moveToFirst();
		String foodMaterial_id=cursor.getString(0);
		String foodMaterial_name=cursor.getString(1);
		String foodMaterial_photo_link=cursor.getString(2);
		int foodMaterial_protein=cursor.getInt(3);
		int foodMaterial_sugar=cursor.getInt(4);
		int foodMaterial_fat=cursor.getInt(5);
		int foodMaterial_dietary_fiber=cursor.getInt(6);
		int foodMaterial_vc=cursor.getInt(7);
		int foodMaterial_calcium=cursor.getInt(8);
		int foodMaterial_muriate=cursor.getInt(9);
		int foodMaterial_natrium=cursor.getInt(10);
		int foodMaterial_phosphorus=cursor.getInt(11);
		int foodMaterial_calories=cursor.getInt(12);
		return new FoodMaterial(foodMaterial_id, foodMaterial_name, 
				foodMaterial_photo_link, foodMaterial_protein,
				foodMaterial_sugar, foodMaterial_fat, 
				foodMaterial_dietary_fiber, foodMaterial_vc,
				foodMaterial_calcium, foodMaterial_muriate, 
				foodMaterial_natrium, foodMaterial_phosphorus, 
				foodMaterial_calories);
	}

	/**搜索食材*/
	public List<FoodMaterial> searchDataByKeyWord(String keyWord){
		List<FoodMaterial> items=new ArrayList<FoodMaterial>();
		Cursor cursor=db.rawQuery("select * from FoodMaterial where FoodMaterial_name like ?",new String[]{"%"+keyWord+"%"});
		for(;cursor.moveToNext();){
			String foodMaterial_id=cursor.getString(0);
			String foodMaterial_name=cursor.getString(1);
			String foodMaterial_photo_link=cursor.getString(2);
			int foodMaterial_protein=cursor.getInt(3);
			int foodMaterial_sugar=cursor.getInt(4);
			int foodMaterial_fat=cursor.getInt(5);
			int foodMaterial_dietary_fiber=cursor.getInt(6);
			int foodMaterial_vc=cursor.getInt(7);
			int foodMaterial_calcium=cursor.getInt(8);
			int foodMaterial_muriate=cursor.getInt(9);
			int foodMaterial_natrium=cursor.getInt(10);
			int foodMaterial_phosphorus=cursor.getInt(11);
			int foodMaterial_calories=cursor.getInt(12);
			items.add(new FoodMaterial(foodMaterial_id, foodMaterial_name, 
					foodMaterial_photo_link, foodMaterial_protein,
					foodMaterial_sugar, foodMaterial_fat, 
					foodMaterial_dietary_fiber, foodMaterial_vc,
					foodMaterial_calcium, foodMaterial_muriate, 
					foodMaterial_natrium, foodMaterial_phosphorus, 
					foodMaterial_calories));
		}
		this.onDestory();
		return items;
	}

	/**获取所有食材*/
	public List<FoodMaterial> getAllData(){
		List<FoodMaterial> foodMaterialList=new ArrayList<FoodMaterial>();
		Cursor cursor=db.rawQuery("select * from FoodMaterial", null);
		for(;cursor.moveToNext();){
			String foodMaterial_id=cursor.getString(0);
			String foodMaterial_name=cursor.getString(1);
			String foodMaterial_photo_link=cursor.getString(2);
			int foodMaterial_protein=cursor.getInt(3);
			int foodMaterial_sugar=cursor.getInt(4);
			int foodMaterial_fat=cursor.getInt(5);
			int foodMaterial_dietary_fiber=cursor.getInt(6);
			int foodMaterial_vc=cursor.getInt(7);
			int foodMaterial_calcium=cursor.getInt(8);
			int foodMaterial_muriate=cursor.getInt(9);
			int foodMaterial_natrium=cursor.getInt(10);
			int foodMaterial_phosphorus=cursor.getInt(11);
			int foodMaterial_calories=cursor.getInt(12);			
			foodMaterialList.add(new FoodMaterial(foodMaterial_id, foodMaterial_name, 
					foodMaterial_photo_link, foodMaterial_protein,
					foodMaterial_sugar, foodMaterial_fat, 
					foodMaterial_dietary_fiber, foodMaterial_vc,
					foodMaterial_calcium, foodMaterial_muriate, 
					foodMaterial_natrium, foodMaterial_phosphorus, 
					foodMaterial_calories));
		}
		this.onDestory();
		return foodMaterialList;
	}

	/**获取食材详细信息*/
	public FoodMaterial getFoodMaterialDetail(String id){
		Cursor cursor=db.rawQuery("select * from FoodMaterial where foodMaterial_id like ?",new String[]{id});
		cursor.moveToFirst();
		String foodMaterial_id=cursor.getString(0);
		String foodMaterial_name=cursor.getString(1);
		String foodMaterial_photo_link=cursor.getString(2);
		int foodMaterial_protein=cursor.getInt(3);
		int foodMaterial_sugar=cursor.getInt(4);
		int foodMaterial_fat=cursor.getInt(5);
		int foodMaterial_dietary_fiber=cursor.getInt(6);
		int foodMaterial_vc=cursor.getInt(7);
		int foodMaterial_calcium=cursor.getInt(8);
		int foodMaterial_muriate=cursor.getInt(9);
		int foodMaterial_natrium=cursor.getInt(10);
		int foodMaterial_phosphorus=cursor.getInt(11);
		int foodMaterial_calories=cursor.getInt(12);
		FoodMaterial fm=new FoodMaterial(foodMaterial_id, foodMaterial_name, 
				foodMaterial_photo_link, foodMaterial_protein,
				foodMaterial_sugar, foodMaterial_fat, 
				foodMaterial_dietary_fiber, foodMaterial_vc,
				foodMaterial_calcium, foodMaterial_muriate, 
				foodMaterial_natrium, foodMaterial_phosphorus, 
				foodMaterial_calories);
		this.onDestory();
		Log.d(TAG,"成功从本地数据库获取"+id+"号食材详细信息");
		return fm;
	}	

	/**创建食材表*/
	public void createTableFoodMaterial(){
		db.execSQL("create table FoodMaterial("
				+ "FoodMaterial_id char(10) primary key,"
				+ "FoodMaterial_name varchar(50),"
				+ "FoodMaterial_photo_link varchar(1000),"
				+ "FoodMaterial_protein int,"
				+ "FoodMaterial_sugar int,"
				+ "FoodMaterial_fat int,"
				+ "FoodMaterial_dietary_fiber int,"
				+ "FoodMaterial_vc int,"
				+ "FoodMaterial_calcium int,"
				+ "FoodMaterial_muriate int,"
				+ "FoodMaterial_natrium int,"
				+ "FoodMaterial_phosphorus int,"
				+ "FoodMaterial_calories int"
				+ ")");
		Log.d(TAG,"成功创建食材表");
		this.onDestory();
	}

	/**关闭数据库连接*/
	public void onDestory(){
		if(db!=null && db.isOpen()){
			db.close();
		}
	}
}