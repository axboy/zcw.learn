package cn.edu.jxnu.x3104.riceroll.start;

import cn.edu.jxnu.x3104.riceroll.R;
import cn.edu.jxnu.x3104.riceroll.db.DbCookBookDownloaded;
import cn.edu.jxnu.x3104.riceroll.service.DoSharedPreferences;
import cn.edu.jxnu.x3104.riceroll.testcase.TestHttp;
import cn.edu.jxnu.x3104.riceroll.ui.MainPageSearchActivity;
import cn.edu.jxnu.x3104.riceroll.ui.MainWindowActivity;
import cn.edu.jxnu.x3104.riceroll.ui.UserFridgeActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class AppStart extends Activity{
	private final static String TAG = "AppStart";
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.app_start);
		if(new DoSharedPreferences().getLoginState(this).equals("login")){
			//如果已登录
			new Handler().postDelayed(new Runnable(){
				public void run(){
					Intent intent = new Intent (AppStart.this,MainWindowActivity.class);
					//Intent intent = new Intent (AppStart.this,TestHttp.class);	
					startActivity(intent);
					AppStart.this.finish();
				}
			}, 2000);
		}
		else{
			//如果未登录
			new Handler().postDelayed(new Runnable(){
				public void run(){
					//Intent intent = new Intent (AppStart.this,UserFridgeActivity.class);
					Intent intent = new Intent (AppStart.this,AppGuide.class);
					//Intent intent = new Intent (AppStart.this,MainPageSearchActivity.class);
					startActivity(intent);
					AppStart.this.finish();
				}
			}, 2000);
		}	
	}
}













//****************以下代码请忽略******************/















/**
|	|	|	|	|

	┏┓	┏┓  
//┏━━┛┻━━━━┛┻━━━━┓   
//┃	      ━━		┃ 
//┃　┳━┛　┗━┳	┃  
//┃			┃ 
//┃　　 ━┻━		┃ 
//┗━━┓	┏━━━━━┛  		神兽保佑　　　　　　　　  
//	┃	┃			代码无BUG！  
//	┃	┗━━━━━┓  
//	┃		┣┓  
//	┃　　　　    ┏━┛  
//	┗┓┓┏━━━┳┓┏━┛  
//	  ┃┫┫　  ┃┫┫  
//	  ┗┻┛　  ┗┻┛  

*/

/**
|	|	|	|	|	|	|	|

 ━━━━━━神兽出没━━━━━━
 
            ┏━┓                  ┏━┓
      ┏━━┛  ┻━━━━━━━━━┛  ┻━━┓
      ┃			          ┃
      ┃               ━━━━               ┃
      ┃      ━┳━━┛      ┗━━┳━      ┃
      ┃			          ┃
      ┃                ━┻━                ┃
      ┃			          ┃
      ┗━━━━┓                ┏━━━━━┛
                ┃                ┃		Code is far away from bug with the animal protecting
                ┃                ┃		神兽保佑,代码无bug
                ┃                ┗━━━━━━━━━┓
                ┃                                    ┣━┓
                ┃                                    ┏━┛
                ┗━┓  ┓  ┏━━━━┳━┓  ┏━━━┛
                    ┃━┫━┫　    ┃━┫━┫
                    ┗━┻━┛　    ┗━┻━┛

*/
 
/*
|	|	|	|	|	|	|	|

             ┏━┓                    ┏━┓
         ┏━┛  ┻━━━━━━━━━━┛  ┻━┓
         ┃　　　        　　　    ┃
         ┃　　　   ━━━　 　  　┃
         ┃　   ＞　　　    ＜　 ┃
         ┃　                                ┃
         ┃   ...　⌒　... ┃
         ┃　　　        　　　　┃
         ┗━━━┓　　　        ┏━━━┛
	     ┃　　        　┃
	     ┃　　        　┃
	     ┃　　        　┃
	     ┃　　        　┃
	     ┃　　        　┃		Code is far away from bug with the animal protecting
	     ┃　　        　┃		神兽保佑,代码无bug
	     ┃　　        　┗━━━━━━━┓
	     ┃                                    ┣┓
	     ┃　　　　　　　        ┏┛
	     ┗━━┓━┓  ┏━━━━┳━┓  ┏━━┛
　　　　　   ┃━┫━┫　    ┃━┫━┫
                       ┗━┻━┛        ┗━┻━┛

*/
 
/**
|	|	|	|	|	|	|	|

             ┏━┓                    ┏━┓+ +
         ┏━┛━┻━━━━━━━━━━┛━┻━┓ + +
         ┃		   	           ┃ 
         ┃　　　    ━━　　　    ┃ ++ + + +
  ██━██	┃+
         ┃　　　　　　　        ┃ +
         ┃　　　  ━┻━　            ┃
         ┃　　　　　　　        ┃ + +
         ┗━━━┓　　　        ┏━━━┛
　　　     ┃　　　        ┃　　　　　　　　　　　
　　　     ┃　　　        ┃ + + + +
　　　     ┃　　　        ┃				Code is far away from bug with the animal protecting　　　　　　　
　　　     ┃　　　        ┃ +				神兽保佑,代码无bug
　　　     ┃　　　        ┃
　　　     ┃　　　        ┃　　+　　　　　　　　　
　　　     ┃　　　        ┗━━━━━━━━━┓ + +
　　　     ┃	　　　　	           ┣━┓
　　　     ┃			           ┏━┛
　　　     ┗━┓━┓  ┏━━━━━━┳━┓  ┏━━━┛    + + + +
 	         ┃━┫━┫	       ┃━┫━┫
	         ┗━┻━┛            ┗━┻━┛ + + + +

*/

/*

　      へ　　　　　／|
　　/\7　　　 ∠＿/
　 /　│　　  ／　／
　│  Z ＿,＜　    　 /`
　│　　　　　  \　　 /  \
　 Y　　　　　   `　/　　/
　ｲ●　､　●　　⊂⊃ 〈      /
　()　 へ　　　　| ＼〈
　　>ｰ ､_　 ィ　 │ ／／
　 / へ　　 /　ﾉ＜| ＼＼
　 ヽ_ﾉ　　(_／　 │／／
　　 7　　　　　　  |／
　　＞―r￣￣`ｰ―＿/

*/

/**

 へ　　　　　／|
　　/＼7　　　 ∠＿/
　 /　│　　 ／　／
　│　Z ＿,＜　／　　 /`ヽ
　│　　　　　ヽ　　 /　　〉
　 Y　　　　　`　 /　　/
　ｲ●　､　●　　⊂⊃〈　　/
　()　 へ　　　　|　＼〈
　　>ｰ ､_　 ィ　 │ ／／
　 / へ　　 /　ﾉ＜| ＼＼
　 ヽ_ﾉ　　(_／　 │／／
　　7　　　　　　　|／
　　＞―r￣￣`ｰ―＿

*/
