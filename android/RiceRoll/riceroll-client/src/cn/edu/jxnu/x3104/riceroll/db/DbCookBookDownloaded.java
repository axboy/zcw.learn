package cn.edu.jxnu.x3104.riceroll.db;

import java.io.File;

import cn.edu.jxnu.x3104.riceroll.bean.CookBook;
import cn.edu.jxnu.x3104.riceroll.util.MyConstants;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**已离线菜谱*/
public class DbCookBookDownloaded {
	private final static String TAG = "DbDownLoadCookBook";
	SQLiteDatabase db;
	
	/**构造函数，创建或打开数据库，存在则不创建*/
	public DbCookBookDownloaded(){
		File folder=new File("/data/data/cn.edu.jxnu.x3104.riceroll/database/");
		if(!folder.exists()){
			folder.mkdir();
		}
		db=SQLiteDatabase.openOrCreateDatabase(MyConstants.DB_URL,null);
	}
	
	/**插入数据,离线菜谱*/
	public void insertData(CookBook cb){
		int isCollected=0;
		if(cb.isCookbook_is_collected()){
			isCollected=1;
		}
		db.execSQL("insert into cookbook values(?,?,?,?,?,?,?,?,?,?,?,?,?)",new Object[]{
				cb.getCookbook_id(),cb.getCookbook_name(),
				cb.getCookbook_keyword(),cb.getCookbook_brief_introduction(),
				cb.getCookbook_photo_link(),cb.getCookbook_tip(),
				 cb.getCookbook_step(),cb.getCookbook_food_meterial_id(),
				 cb.getCookbook_condiment_id(),cb.getCookbook_click_times(),
				 cb.getCookbook_download_times(),cb.getCookbook_collect_times(),
				 isCollected});
		this.onDestory();
	}
	
	/**删除数据*/
	public void deleteData(CookBook cb){
		db.execSQL("delete cookbook from...");
	}
	
	/**删除全部*/
	public void deleteAllData(){
		db.execSQL("delete from CookBookDownloaded"); 
		Log.d(TAG,"已成功清除离线菜谱表及图片缓存");
	}
	
	/**搜索已下载菜谱*/
	public void searchData(){
		db.execSQL("...");
	}
	
	/**创建菜谱表*/
	public void createTableCookBook(){
		db.execSQL("create table CookBookDownloaded("
				+ "cookbook_id char(10) primary key,"
				+ "cookbook_name varchar(50),"
				+ "cookbook_keyword varchar(50),"
				+ "cookbook_brief_introduction varchar(1000),"
				+ "cookbook_photo_link varchar(1000),"
				+ "cookbook_tip varchar(1000),"
				+ "cookbook_step varchaor(1000),"
				+ "cookbook_food_material_id varchar(1000),"
				+ "cookbook_condiment_id varchar(1000),"
				+ "cookbook_click_times int,"
				+ "cookbook_download_times int,"
				+ "cookbook_collect_times int,"
				+ "cookbook_is_collected int"
				+ ")");
	}
	
	/**关闭数据库连接*/
	public void onDestory(){
		if(db!=null && db.isOpen()){
			db.close();
		}
	}
}
