package cn.edu.jxnu.x3104.riceroll.db;

import java.io.File;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import cn.edu.jxnu.x3104.riceroll.bean.CookBook;
import cn.edu.jxnu.x3104.riceroll.service.DoHttp;
import cn.edu.jxnu.x3104.riceroll.util.MyConstants;

/**每日菜单*/
public class DbDailyCookBook {
	private final static String TAG = "DbSearchHistory";
	SQLiteDatabase db;

	/**构造函数，创建或打开数据库，存在则不创建*/
	public DbDailyCookBook(){
		/**避免文件不存在*/
		File folder=new File("/data/data/cn.edu.jxnu.x3104.riceroll/database/");
		if(!folder.exists()){
			folder.mkdir();
		}
		db=SQLiteDatabase.openOrCreateDatabase(MyConstants.DB_URL,null);
	}
	
	public void createTableDailyCookBook(){
		db.execSQL("create table DailyCookBook("
				+ "time char(50) primary key,"		//时间
				+ "name char(50)"					//菜谱名字，以分号隔开
				+ ")");
		this.onDestory();
		Log.d(TAG,"创建每日菜谱表成功");
	}
	
	/**插入或更新数据*/
	public void addCookBookToNow(String name){
		Date date=new Date();
		DateFormat df = DateFormat.getDateInstance();
		String time=df.format(date);	//2015-6-28
		//如果插入数据失败，则更新数据
		try{
			db.execSQL("insert into DailyCookBook values(?,?)",new Object[]{time,name});
			this.onDestory();
		}catch(android.database.SQLException e){
			String newName=getCookBookName(time);
			if(newName.contains(name)){
				this.onDestory();
				Log.d(time,newName);
				return ;
			}
			newName+=":#:"+name;
			db.execSQL("update DailyCookBook set name = ? where time = ?",new Object[]{newName,time});
			Log.d(time,newName);
			this.onDestory();
		}
	}
	
	/**获得某日所有菜谱名字*/
	//不能关闭数据库，后面还要用，仅供内部插入时调用
	private String getCookBookName(String time){
		Cursor cursor=db.rawQuery("select * from DailyCookBook where time like ?",new String[]{time});
		if(cursor.getCount()==0){
			return "null";
		}
		cursor.moveToFirst();
		String name=cursor.getString(1);
		//this.onDestory();
		return name;
	}
	
	/**
	 * 获取今日菜谱列表，有联网
	 * 调用请开线程
	 * 调用请开线程
	 * 调用请开线程
	 * */
	public List<CookBook> getTodayCookBook(){
		List<CookBook> items=new ArrayList<CookBook>();
		Date date=new Date();
		DateFormat df = DateFormat.getDateInstance();
		String time=df.format(date);	//2015-6-28
		if(getCookBookName(time).equals("null")){
			this.onDestory();
			return null;
		}
		String []books=getCookBookName(time).split(":#:");
		this.onDestory();
		for(int i=0;i<books.length;i++){
			Log.e("books",books[i]);
			CookBook c=new DoHttp().getCookBookByName(books[i]);
			items.add(c);
		}
		return items;
	}
	
	public Boolean isTodayNull(){
		Date date=new Date();
		DateFormat df = DateFormat.getDateInstance();
		String time=df.format(date);	//2015-6-28
		Cursor cursor=db.rawQuery("select * from DailyCookBook where time like ?",new String[]{time});
		if(cursor.getCount()==0){
			this.onDestory();
			return false;
		}
		this.onDestory();
		return true;
	}
	
	/**关闭数据库连接*/
	public void onDestory(){
		if(db!=null && db.isOpen()){
			db.close();
		}
	}
}
