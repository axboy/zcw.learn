package cn.edu.jxnu.x3104.team3.jxnu_library.ui;
import java.util.Timer;
import java.util.TimerTask;
import android.view.View;
import android.widget.Button;
import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import cn.edu.jxnu.x3104.team3.jxnu_library.left_menu.Borrow_Now;
import cn.edu.jxnu.x3104.team3.jxnu_library.left_menu.Letter_Server;
import cn.edu.jxnu.x3104.team3.jxnu_library.left_menu.Reader_Borrow;
import cn.edu.jxnu.x3104.team3.jxnu_library.left_menu.Reader_Info;
import cn.edu.jxnu.x3104.team3.jxnu_library.left_menu.Reader_Lost;
import cn.edu.jxnu.x3104.team3.jxnu_library.left_menu.Search_History;
import  cn.edu.jxnu.x3104.team3.jxnu_library.parameters.CircularImage;
import cn.edu.jxnu.x3104.team3.jxnu_library.parameters.MyConstants;
import cn.edu.jxnu.x3104.team3.jxnu_library.services.DoConnection;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

@SuppressLint({ "ClickableViewAccessibility", "InflateParams" })
@SuppressWarnings("deprecation")
public class Activity_Main extends TabActivity implements OnTouchListener{
	TabHost tabhost;
	DoConnection doconnection;
	private CircularImage userheadimageview;//用户头像框*
	private Button infoButton;// 个人信息按钮
	private Button borrowButton;// 书刊借阅按钮
	private Button moreButton;// 违章缴费按钮
	private Button Reader_Lost_Button;// 我的书架按钮
	private Button historyButton;// 搜索历史历史按钮
	private Button letterButton;// 短信服务按钮
	private TextView toLogout;
	public static final int SNAP_SPEED=150;		//滑动速度
	private int screenWidth;	//屏幕宽度
	private int leftEdge;		//向左滑动的最大位移，marginLeft到达此值之后，不能再减少
	private int rightEdge=0;	//menu最多可以滑动到的右边缘。值恒为0，即marginLeft到达0之后，不能增加。
	private int menuPadding=150;	//menu完全显示时，留给content的宽度值
	private View content;		//主内容的布局
	private View menu;			//menu的布局
	private LinearLayout.LayoutParams menuParams;	//menu布局的参数，通过此参数来更改leftMargin的值。
	private float xDown;		//记录手指按下时的横坐标
	private float xMove;		//记录手指一动时的横坐标
	private float xUp;			//记录手指抬起时的横坐标
	private boolean isMenuVisible;	//menu当前是显示还是隐藏。只有完全显示或隐藏menu时才会更改此值，滑动过程中此值无效。
	private VelocityTracker vt;	//用于计算手指的滑动速度

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
		init();
		content.setOnTouchListener(this);	//滑动监听
		//添加对象
		Intent tab01=new Intent(this,TFSearchActivity.class);
		TabSpec tab1=tabhost.newTabSpec("tab1");
		View V_1=getView(R.drawable.search_selector,"搜索");	
		tab1.setIndicator(V_1);	//设置标题
		tab1.setContent(tab01);			//设置内容
		tabhost.addTab(tab1);			//添加标签页
		//添加对象
		Intent tab03=new Intent(this,Activity_gnxw.class);
		TabSpec tab3=tabhost.newTabSpec("tab3");
		View V_3=getView(R.drawable.news_selector,"新闻");	
		tab3.setIndicator(V_3);	//设置标题
		tab3.setContent(tab03);			//设置内容
		tabhost.addTab(tab3);			//添加标签页
		//点击事件
		infoButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				new Thread(new Runnable(){
					public void run() {
						String html =new DoConnection().gethtmlcode(Activity_Main.this, MyConstants.HTTP_JXNU_LIB_INFO);
//						Log.i("info",html);
						Intent intent = new Intent (Activity_Main.this,Reader_Info.class);	
						intent.putExtra("codes", html);
						startActivity(intent);

					}
				}).start();
			}
		});
		borrowButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				new Thread(new Runnable(){
					public void run() {
						Intent intent = new Intent (Activity_Main.this,Reader_Borrow.class);	
						startActivity(intent);
					}
				}).start();
			}
		});
		//图书续借按钮
		moreButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				new Thread(new Runnable(){
					public void run() {
						String html =new DoConnection().gethtmlcode(Activity_Main.this, MyConstants.HTTP_JXNU_LIB_BORROW);
//						Log.w("info",html);
						if(html.contains("您的该项记录为空")){
							new Thread(new Runnable(){
								public void run(){
									Intent intent=new Intent();
									intent.putExtra("title", "图书续借");
									intent.setClass(Activity_Main.this,Search_result_no_content.class);
									startActivity(intent);
								}
							}).start();
						}else {
							Intent intent = new Intent (Activity_Main.this,Borrow_Now.class);	
							intent.putExtra("codes", html);
							startActivity(intent);
						}
					}
				}).start();
			}
		});
		Reader_Lost_Button.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				new Thread(new Runnable(){
					public void run() {
						Intent intent=new Intent();
						intent.setClass(Activity_Main.this,Reader_Lost.class);
						startActivity(intent);
			}
		}).start();
			}
		});
		historyButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {

				new Thread(new Runnable(){
					public void run() {
						String html =new DoConnection().gethtmlcode(Activity_Main.this, MyConstants.HTTP_JXNU_LIB_HISTORY);
						if(html.contains("您的该项记录为空")){
							new Thread(new Runnable(){
								public void run(){
									Intent intent=new Intent();
									intent.putExtra("title", "搜索历史");
									intent.setClass(Activity_Main.this,Search_result_no_content.class);
									startActivity(intent);
								}
							}).start();
						}else{
							Intent intent = new Intent (Activity_Main.this,Search_History.class);	
							intent.putExtra("codes", html);
							startActivity(intent);
						}
					}
				}).start();
			}
		});
		letterButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				new Thread(new Runnable(){
					public void run() {
						Intent intent = new Intent (Activity_Main.this,Letter_Server.class);	
						startActivity(intent);
					}
				}).start();
			}
		});
		toLogout.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				new AlertDialog.Builder(Activity_Main.this).setTitle("确定注销吗？")
				.setPositiveButton("确定", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						SharedPreferences logout= getSharedPreferences("login", Context.MODE_PRIVATE);
						Editor editor = logout.edit();//获取编辑器  	
						editor.putString("login_state", "000");
						editor.commit();//提交修改  
						Intent intent = new Intent(getApplicationContext(),Activity_Main_unlogin.class);
						startActivity(intent);
						overridePendingTransition(
								android.R.anim.slide_in_left,
								android.R.anim.slide_out_right);// 回到登录界面
						Activity_Main.this.finish();
					}
				}).setNegativeButton("取消", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				}).show();
			}
		});
	}
	private void init() {
		tabhost=getTabHost();
		WindowManager window = (WindowManager) getSystemService(Context.WINDOW_SERVICE);  
		infoButton = (Button)findViewById(R.id.infobutton);
		borrowButton = (Button)findViewById(R.id.finebutton);
		moreButton = (Button)findViewById(R.id.borrowbutton);
		Reader_Lost_Button = (Button)findViewById(R.id.reader_lost_button);
		historyButton = (Button)findViewById(R.id.historybutton);
		letterButton = (Button)findViewById(R.id.letterbutton);
		toLogout=(TextView)findViewById(R.id.toLogout);
		SharedPreferences sp= getSharedPreferences("login", Context.MODE_PRIVATE);			
		userheadimageview = (CircularImage) (findViewById(R.id.userheadimageview));
		userheadimageview.setImageBitmap(getUserPhoto(sp.getString("name",""),Activity_Main.this));
		((TextView)findViewById(R.id.usernamebox))
		.setText(sp.getString("name",""));
		((TextView)findViewById(R.id.useridbox)).setText(sp.getString("username", ""));
		screenWidth = window.getDefaultDisplay().getWidth();  	//获得屏幕宽度
		content = findViewById(R.id.content);  
		menu = findViewById(R.id.menu);  
		menuParams = (LinearLayout.LayoutParams) menu.getLayoutParams();  
		menuParams.width = screenWidth - menuPadding;		// 将menu的宽度设置为屏幕宽度减去menuPadding 
		leftEdge = -menuParams.width;  			// 左边缘的值赋值为menu宽度的负数
		menuParams.leftMargin = leftEdge;  		// menu的leftMargin设置为左边缘的值，这样初始化时menu就变为不可见
		content.getLayoutParams().width = screenWidth; 		// 将content的宽度设置为屏幕宽度  
	}
	private View getView(int imId,String title) {
		View view=View.inflate(getApplicationContext(),R.layout.tab_nav, null);
		ImageView iv1=(ImageView)view.findViewById(R.id.ivIcon);
		iv1.setBackgroundResource(imId);
		TextView tv=(TextView)view.findViewById(R.id.tvTitle);
		tv.setText(title);
		return view;
	}

	public boolean onTouch(View v,MotionEvent event){
		createVelocityTracker(event);
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN :
			xDown=event.getRawX();	//记录手指按下时的横坐标
			break;
		case MotionEvent.ACTION_MOVE :
			xMove=event.getRawX();
			int distanceX=(int)(xMove-xDown);
			if(isMenuVisible){
				menuParams.leftMargin=distanceX;						
			}
			else{
				menuParams.leftMargin=leftEdge+distanceX;
			}
			if(menuParams.leftMargin<leftEdge){
				menuParams.leftMargin=leftEdge;				//leftEdge为负,设置最大位移
			}else if(menuParams.leftMargin>rightEdge){		//rightEdge=0
				menuParams.leftMargin=rightEdge;
			}
			menu.setLayoutParams(menuParams);
			break;
		case MotionEvent.ACTION_UP :
			// 手指抬起时，进行判断当前手势的意图，从而决定是滚动到menu界面，还是滚动到content界面
			xUp=event.getRawX();
			if(wantToShowMenu()){
				if(shouldScrollToMenu()){
					scrollToMenu();
				}else{
					scrollToContent();
				}
			}else if(wantToShowContent()){
				if(shouldScrollToContent()){
					scrollToContent();
				}else{
					scrollToMenu();
				}
			}
			recycleVelocityTracker();
			break;
		}
		return true;
	}
	private boolean wantToShowContent(){
		return xUp-xDown<0&&isMenuVisible;		//正显示menu且左划了
	}
	private boolean wantToShowMenu(){
		return xUp-xDown>0&& !isMenuVisible;	//正显示content且右划了
	}
	private boolean shouldScrollToMenu(){
		//滑动距离大于一半屏幕宽度或者滑动速度足够
		return xUp - xDown > screenWidth / 2 || getScrollVelocity() > SNAP_SPEED;
	}
	private boolean shouldScrollToContent(){
		//如果手指移动距离加上menuPadding大于屏幕的1/2， 
		//或者手指移动速度大于SNAP_VELOCITY， 就认为应该滚动将content展示出来。
		return xDown - xUp + menuPadding > screenWidth / 2 || getScrollVelocity() > SNAP_SPEED; 
	}
	//将屏幕滚动到menu界面，滚动速度设定为30
	private void scrollToMenu(){
		new ScrollTask().execute(30);
	}
	//将屏幕滚动到content界面，滚动速度设定为-30
	private void scrollToContent(){
		new ScrollTask().execute(-30);
	}
	//创建VelocityTracker对象，并将触摸content界面的滑动事件加入到VelocityTracker当中
	private void createVelocityTracker(MotionEvent event) {  
		if (vt == null) {  
			vt = VelocityTracker.obtain();  
		}  
		vt.addMovement(event);  
	}  
	//获取手指在content页面的滑动速度
	private int getScrollVelocity() {
		vt.computeCurrentVelocity(1000);	//以1000毫秒滑动多少像素为单位
		int velocity=(int)vt.getXVelocity();
		return Math.abs(velocity);
	}
	//回收VelocityTracker对象
	private void recycleVelocityTracker() {  
		vt.recycle();  
		vt = null;  
	} 
	class ScrollTask extends AsyncTask<Integer, Integer, Integer> {  

		protected Integer doInBackground(Integer... speed) {  
			int leftMargin = menuParams.leftMargin;  
			// 根据传入的速度来滚动界面，当滚动到达左边界或右边界时，跳出循环。  
			while (true) {  
				leftMargin = leftMargin + speed[0];  
				if (leftMargin > rightEdge) {  
					leftMargin = rightEdge;  
					break;  
				}  
				if (leftMargin < leftEdge) {  
					leftMargin = leftEdge;  
					break;  
				}  
				publishProgress(leftMargin);  
				// 为了要有滚动效果产生，每次循环使线程睡眠20毫秒，这样肉眼才能够看到滚动动画。  
				sleep(20);  
			}  
			if (speed[0] > 0) {  
				isMenuVisible = true;  
			} else {  
				isMenuVisible = false;  
			}  
			return leftMargin;  
		}  

		protected void onProgressUpdate(Integer... leftMargin) {  
			menuParams.leftMargin = leftMargin[0];  
			menu.setLayoutParams(menuParams);  
		}  

		protected void onPostExecute(Integer leftMargin) {  
			menuParams.leftMargin = leftMargin;  
			menu.setLayoutParams(menuParams);  
		}  
	} 
	//指定当前线程睡眠多久，以毫秒为单位
	private void sleep(long millis) {  
		try {  
			Thread.sleep(millis);  
		} catch (InterruptedException e) {  
			e.printStackTrace();  
		}  
	} 
	public Bitmap getUserPhoto(String username,Context context) {
		Log.d("test",username);
		Bitmap userphoto = null;
		String userid=username.substring(2);
		String imagefilepath = context.getFileStreamPath(userid
				+ ".jpg").getPath();// 获取文件路径
		Log.d("dofile", imagefilepath);
		userphoto=BitmapFactory.decodeFile(imagefilepath);
		return userphoto;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click(); // 调用双击退出函数
		}
		return false;
	}

	/* 双击退出函数 */
	private static Boolean isExit = false;

	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; // 准备退出
			Toast toast=new Toast(Activity_Main.this);
			View toastStyle=Activity_Main.this.getLayoutInflater().inflate(R.layout.layout_toast, null);
			toast.setView(toastStyle);
			toast.show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				public void run() {
					isExit = false; // 取消退出
				}
			}, 3000); // 如果3秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

		} else {
			Activity_Main.this.finish();
			overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		}
	}
}
