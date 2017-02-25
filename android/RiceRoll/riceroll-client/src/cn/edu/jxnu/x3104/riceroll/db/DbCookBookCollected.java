package cn.edu.jxnu.x3104.riceroll.db;

import java.io.File;

import cn.edu.jxnu.x3104.riceroll.util.MyConstants;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**已收藏的菜谱*/
public class DbCookBookCollected {
	private final static String TAG = "DbCookBookCollected";
	SQLiteDatabase db;
	
	/**构造函数，创建或打开数据库，存在则不创建*/
	public DbCookBookCollected(){
		File folder=new File("/data/data/cn.edu.jxnu.x3104.riceroll/database/");
		if(!folder.exists()){
			folder.mkdir();
		}
		db=SQLiteDatabase.openOrCreateDatabase(MyConstants.DB_URL,null);
	}
	
	public void createTableCookBookCollected(){
		db.execSQL("create table CookBookCollected("
				+ "cookbook_id char(10) primary key,"
				+ "cookbook_name varchar(50),"
				+ ")");
	}
	
	/***/
	public void delAllData(){
		db.execSQL("delete from CookBookCollected");
		Log.d(TAG,"已清空已收藏菜谱表");
		this.onDestory();
	}
	
	/**关闭数据库连接*/
	public void onDestory(){
		if(db!=null && db.isOpen()){
			db.close();
		}
	}
}
