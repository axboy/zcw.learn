package cn.edu.jxnu.x3104.riceroll.db;

import java.io.File;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import cn.edu.jxnu.x3104.riceroll.util.MyConstants;

/**搜索历史，未使用*/
public class DbSearchHistory {
	private final static String TAG = "DbSearchHistory";
	SQLiteDatabase db;

	/**构造函数，创建或打开数据库，存在则不创建*/
	public DbSearchHistory(){
		/**避免文件不存在*/
		File folder=new File("/data/data/cn.edu.jxnu.x3104.riceroll/database/");
		if(!folder.exists()){
			folder.mkdir();
		}
		db=SQLiteDatabase.openOrCreateDatabase(MyConstants.DB_URL,null);
	}
	
	/**创建搜索历史表*/
	public void createTableSearchHistory(){
		db.execSQL("create table SearchHistory("
				+ "content char(50) primary key,"		//搜索内容
				+ "type char(50),"						//搜索类型
				+ "search_times int"					//搜索次数
				+ ")");
		this.onDestory();
	}
	
	/**获取搜索记录*/
	public List<String> getSearchHistory(String type,String content){
		
		return null;
	}	

	/**关闭数据库连接*/
	public void onDestory(){
		if(db!=null && db.isOpen()){
			db.close();
		}
	}
}
