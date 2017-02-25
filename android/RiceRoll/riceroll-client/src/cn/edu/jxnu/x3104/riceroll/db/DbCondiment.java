package cn.edu.jxnu.x3104.riceroll.db;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.edu.jxnu.x3104.riceroll.bean.Condiment;
import cn.edu.jxnu.x3104.riceroll.bean.FoodMaterial;
import cn.edu.jxnu.x3104.riceroll.service.DoLocalPicture;
import cn.edu.jxnu.x3104.riceroll.util.MyConstants;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**调料*/
public class DbCondiment {
	private final static String TAG = "DbCondiment";
	SQLiteDatabase db;

	/**构造函数，创建或打开数据库，存在则不创建*/
	public DbCondiment(){
		/**避免文件不存在*/
		File folder=new File("/data/data/cn.edu.jxnu.x3104.riceroll/database/");
		if(!folder.exists()){
			folder.mkdir();
		}
		db=SQLiteDatabase.openOrCreateDatabase(MyConstants.DB_URL,null);
	}
	
	/**获取调料详细信息*/
	public Condiment getCondimentDetail(String id){
		Cursor cursor=db.rawQuery("select * from Condiment where condiment_id like ?",new String[]{id});
		cursor.moveToFirst();
		String condiment_id=cursor.getString(0);
		String condiment_name=cursor.getString(1);
		String condiment_photo_link=cursor.getString(2);
		int condiment_protein=cursor.getInt(3);
		int condiment_sugar=cursor.getInt(4);
		int condiment_fat=cursor.getInt(5);
		int condiment_dietary_fiber=cursor.getInt(6);
		int condiment_vc=cursor.getInt(7);
		int condiment_calcium=cursor.getInt(8);
		int condiment_muriate=cursor.getInt(9);
		int condiment_natrium=cursor.getInt(10);
		int condiment_phosphorus=cursor.getInt(11);
		int condiment_calories=cursor.getInt(12);			
		Condiment c=new Condiment(condiment_id, condiment_name, 
				condiment_photo_link, condiment_protein,
				condiment_sugar, condiment_fat, 
				condiment_dietary_fiber, condiment_vc,
				condiment_calcium, condiment_muriate, 
				condiment_natrium, condiment_phosphorus, 
				condiment_calories);
		this.onDestory();
		Log.d(TAG,"成功从本地数据库获取"+id+"号调料详细信息");
		return c;
	}
	
	/**插入数据*/
	public void insertData(Condiment c){
		db.execSQL("insert into Condiment values(?,?,?,?,?,?,?,?,?,?,?,?,?)",new Object[]{
				c.getCondiment_id(),c.getCondiment_name(),
				c.getCondiment_photo_link(),c.getCondiment_protein(),
				c.getCondiment_sugar(),c.getCondiment_fat(),
				c.getCondiment_dietary_fiber(),c.getCondiment_vc(),
				c.getCondiment_calcium(),c.getCondiment_muriate(),
				c.getCondiment_natrium(),c.getcondiment_phosphorus(),
				c.getCondiment_calories() });
		this.onDestory();
		//避免文件夹不存在
		File folder=new File(new MyConstants().IMG_URL+"CONDIMENTIMAGE/");
		if(!folder.exists()){
			folder.mkdir();
		}
		//分割字符串
		String []links=c.getCondiment_photo_link().split(";");
		for(String link:links){
			Log.d(TAG+"+link",link);
			new DoLocalPicture().writeToSD("CONDIMENTIMAGE", link);
		}
	}
	
	/**置空表*/
	public void removeAllData(){
		db.execSQL("delete from Condiment"); 
		Log.d(TAG,"成功清空了调料表");
		this.onDestory();
	}
	
	/**获取所有调料*/
	public List<Condiment> getAllData(){
		List<Condiment> condimentList=new ArrayList<Condiment>();
		Cursor cursor=db.rawQuery("select * from Condiment", null);
		for(;cursor.moveToNext();){
			String condiment_id=cursor.getString(0);
			String condiment_name=cursor.getString(1);
			String condiment_photo_link=cursor.getString(2);
			int condiment_protein=cursor.getInt(3);
			int condiment_sugar=cursor.getInt(4);
			int condiment_fat=cursor.getInt(5);
			int condiment_dietary_fiber=cursor.getInt(6);
			int condiment_vc=cursor.getInt(7);
			int condiment_calcium=cursor.getInt(8);
			int condiment_muriate=cursor.getInt(9);
			int condiment_natrium=cursor.getInt(10);
			int condiment_phosphorus=cursor.getInt(11);
			int condiment_calories=cursor.getInt(12);			
			condimentList.add(new Condiment(condiment_id, condiment_name, 
					condiment_photo_link, condiment_protein,
					condiment_sugar, condiment_fat, 
					condiment_dietary_fiber, condiment_vc,
					condiment_calcium, condiment_muriate, 
					condiment_natrium, condiment_phosphorus, 
					condiment_calories));
		}
		this.onDestory();
		return condimentList;
	}
	
	public List<Condiment> searchCondimentByKeyWord(String keyWord){
		List<Condiment> items=new ArrayList<Condiment>();
		Cursor cursor=db.rawQuery("select * from Condiment where Condiment_name like ?",new String[]{"%"+keyWord+"%"});
		for(;cursor.moveToNext();){
			String condiment_id=cursor.getString(0);
			String condiment_name=cursor.getString(1);
			String condiment_photo_link=cursor.getString(2);
			int condiment_protein=cursor.getInt(3);
			int condiment_sugar=cursor.getInt(4);
			int condiment_fat=cursor.getInt(5);
			int condiment_dietary_fiber=cursor.getInt(6);
			int condiment_vc=cursor.getInt(7);
			int condiment_calcium=cursor.getInt(8);
			int condiment_muriate=cursor.getInt(9);
			int condiment_natrium=cursor.getInt(10);
			int condiment_phosphorus=cursor.getInt(11);
			int condiment_calories=cursor.getInt(12);
			items.add(new Condiment(condiment_id, condiment_name, 
					condiment_photo_link, condiment_protein,
					condiment_sugar, condiment_fat, 
					condiment_dietary_fiber, condiment_vc,
					condiment_calcium, condiment_muriate, 
					condiment_natrium, condiment_phosphorus, 
					condiment_calories));
		}
		return items;
	}
	
	/**创建调料表*/
	public void createTableCondiment(){
		db.execSQL("create table Condiment("
				+ "condiment_id char(10) primary key,"
				+ "condiment_name varchar(50),"
				+ "condiment_photo_link varchar(1000),"
				+ "condiment_protein int,"
				+ "condiment_sugar int,"
				+ "condiment_fat int,"
				+ "condiment_dietary_fiber int,"
				+ "condiment_vc int,"
				+ "condiment_calcium int,"
				+ "condiment_muriate int,"
				+ "condiment_natrium int,"
				+ "condiment_phosphorus int,"
				+ "condiment_calories int"
				+ ")");
		Log.d(TAG,"成功创建调料表");
		this.onDestory();
	}
	
	/**关闭数据库连接*/
	public void onDestory(){
		if(db!=null && db.isOpen()){
			db.close();
		}
	}
	
}