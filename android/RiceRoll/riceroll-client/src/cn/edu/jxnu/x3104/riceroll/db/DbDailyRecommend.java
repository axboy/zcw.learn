package cn.edu.jxnu.x3104.riceroll.db;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import cn.edu.jxnu.x3104.riceroll.bean.Condiment;
import cn.edu.jxnu.x3104.riceroll.bean.DailyRecommend;
import cn.edu.jxnu.x3104.riceroll.service.DoLocalPicture;
import cn.edu.jxnu.x3104.riceroll.util.MyConstants;

/**每日推荐，数据库*/
public class DbDailyRecommend {
	private final static String TAG = "DbDailyRecommend";
	SQLiteDatabase db;

	/**构造函数，创建或打开数据库，存在则不创建*/
	public DbDailyRecommend(){
		/**避免文件不存在*/
		File folder=new File("/data/data/cn.edu.jxnu.x3104.riceroll/database/");
		if(!folder.exists()){
			folder.mkdir();
		}
		db=SQLiteDatabase.openOrCreateDatabase(MyConstants.DB_URL,null);
	}

	/**插入数据*/
	public void insertData(DailyRecommend dr){
		db.execSQL("insert into EveryDayRecommend values(?,?,?,?,?)",new Object[]{
				dr.getId(),dr.getCookbook_name(),dr.getRecommend_reason(),
				dr.getIntroduction_text(),dr.getImg_path() });
		this.onDestory();
		//避免文件夹不存在
		File folder=new File(new MyConstants().IMG_URL+"RECOMMEND/");
		if(!folder.exists()){
			folder.mkdir();
		}
		//分割字符串
		String []links=dr.getImg_path().split(";");
		for(String link:links){
			Log.d(TAG+"+link",link);
			new DoLocalPicture().writeToSD("RECOMMEND", link);
		}
	}
	
	/**获取每日推荐详细信息*/
	public DailyRecommend getDateDetail(String DRid){
		Cursor cursor=db.rawQuery("select * from EveryDayRecommend where id like ?",new String[]{DRid});
		cursor.moveToFirst();
		this.onDestory();
		String id=cursor.getString(0);
		String cookbook_name=cursor.getString(1);
		String recommend_reason=cursor.getString(2);
		String introduction_text=cursor.getString(3);
		String img_path=cursor.getString(4);
		return new DailyRecommend(id, cookbook_name, introduction_text, recommend_reason, img_path);
	}

	/**获取当日推荐*/
	public List<DailyRecommend> getAllData(){
		List<DailyRecommend> list=new ArrayList<DailyRecommend>();
		Cursor cursor=db.rawQuery("select * from EveryDayRecommend", null);
		for(;cursor.moveToNext();){
			String id=cursor.getString(0);
			String cookbook_name=cursor.getString(1);
			String recommend_reason=cursor.getString(2);
			String introduction_text=cursor.getString(3);
			String img_path=cursor.getString(4);
			list.add(new DailyRecommend(id, cookbook_name, introduction_text, recommend_reason, img_path));
		}
		this.onDestory();
		return list;
	}

	/**清除所有数据*/
	public void removeAll(){
		db.execSQL("delete from EveryDayRecommend"); 
		Log.d(TAG,"成功清空了每日推荐表");
		this.onDestory();
	}

	/**创建每日推荐表*/
	public void createTableDailyRecommend(){
		db.execSQL("create table EveryDayRecommend("
				+ "id char(50) primary key,"
				+ "cookbook_name char(100),"
				+ "recommend_reason char(100),"
				+ "introduction_text char(1000),"
				+ "img_path char(100)"
				+ ")");
		Log.d(TAG,"成功创建每日推荐表");
		this.onDestory();
	}	

	/**关闭数据库连接*/
	public void onDestory(){
		if(db!=null && db.isOpen()){
			db.close();
		}
	}
}
