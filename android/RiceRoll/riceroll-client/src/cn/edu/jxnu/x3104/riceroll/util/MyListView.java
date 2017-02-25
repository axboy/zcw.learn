package cn.edu.jxnu.x3104.riceroll.util;

import cn.edu.jxnu.x3104.riceroll.R;

import java.util.Date;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**自定义ListView，增加下拉刷新*/
public class MyListView extends ListView  implements OnScrollListener{
	private final static String TAG = "MyListView";	

	private View mHeadView;

	/**箭头*/
	private ImageView mHeadArrow;

	/**进度条*/
	private ProgressBar mHeadProgressBar;

	/**提示，eg：下拉获取更多*/
	private TextView mHeadTips;

	/**上次更新时间*/
	private TextView mHeadLastUpdateTime;

	/**当前头标高度*/
	private int mHeadContentHeight;

	/**用于保证startY的值在一个完整的touch事件中只被记录一次*/
	private boolean mIsRecord = false;

	/**标记的Y坐标值*/
	private int mStartY = 0;

	/**当前视图能看到的第一个项的索引*/
	private int mFirstItemIndex = -1;

	/**MOVE时保存的Y坐标值*/
	private int mMoveY = 0;

	/**ListView状态*/
	private int mViewState = IListViewState.LVS_NORMAL;;

	private final static int RATIO = 2;

	/**头部刷新监听器*/
	private IOnRefreshListener mOnRefreshListener;

	/**正向动画*/
	private RotateAnimation animation;

	/**反向动画*/
	private RotateAnimation reverseAnimation;

	private boolean mBack = false;

	/**构造函数*/
	public MyListView(Context context) {
		super(context);
		init(context);
	}

	/**构造函数*/
	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**初始化*/
	private void init(Context context)
	{
		initHeadView(context);
		setOnScrollListener(this);
	}

	/**初始化顶栏刷新按钮*/
	private void initHeadView(Context context) {
		mHeadView = LayoutInflater.from(context).inflate(R.layout.my_listview_head, null);
		mHeadArrow = (ImageView) mHeadView.findViewById(R.id.my_listview_head_arrowImageView);
		mHeadArrow.setMinimumWidth(60);
		mHeadProgressBar= (ProgressBar) mHeadView.findViewById(R.id.my_listview_head_progressBar);
		mHeadTips = (TextView) mHeadView.findViewById(R.id.my_listview_head_tipsTextView);
		mHeadLastUpdateTime = (TextView) mHeadView.findViewById(R.id.my_listview_head_lastUpdatedTextView);

		this.measureView(mHeadView);
		mHeadContentHeight = mHeadView.getMeasuredHeight();
		mHeadView.setPadding(0, -1 * mHeadContentHeight, 0, 0);
		mHeadView.invalidate();

		addHeaderView(mHeadView, null, false);

		//旋转的开始角度，旋转的结束角度，x轴的伸缩模式，x坐标的伸缩值，y轴的伸缩模式，y坐标的伸缩值
		animation = new RotateAnimation(0, -180,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		animation.setInterpolator(new LinearInterpolator());
		animation.setDuration(250);
		animation.setFillAfter(true);

		reverseAnimation = new RotateAnimation(-180, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		reverseAnimation.setInterpolator(new LinearInterpolator());
		reverseAnimation.setDuration(200);
		reverseAnimation.setFillAfter(true);
	}


	/**计算headView的width以及height*/
	private void measureView(View child) {
		//完全复制的，并不理解,以后再加注释
		ViewGroup.LayoutParams params = child.getLayoutParams();
		if (params == null) {
			params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, params.width);
		int lpHeight = params.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		child.measure(childWidthSpec, childHeightSpec);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		//暂不需要
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		mFirstItemIndex = firstVisibleItem;
	}

	
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
	
	/*****************************************
	 * 以下方法全部抄袭，与本人无关,暂时未去细看，注释以后再加   *
	 *****************************************/

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		if (mOnRefreshListener != null)
		{
			switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:		
				doActionDown(ev);
				break;
			case MotionEvent.ACTION_MOVE:
				doActionMove(ev);
				break;
			case MotionEvent.ACTION_UP:
				doActionUp(ev);
				break;
			default:
				break;
			}	
		}
		return super.onTouchEvent(ev);
	}

	private void doActionDown(MotionEvent ev)
	{
		if(mIsRecord == false && mFirstItemIndex == 0)
		{
			mStartY = (int) ev.getY();
			mIsRecord = true;
		}
	}

	private void doActionMove(MotionEvent ev)
	{
		mMoveY = (int) ev.getY();

		if(mIsRecord == false && mFirstItemIndex == 0)
		{
			mStartY = (int) ev.getY();
			mIsRecord = true;
		}

		if (mIsRecord == false || mViewState == IListViewState.LVS_LOADING)
		{
			return ;
		}	

		int offset = (mMoveY - mStartY) / RATIO;	

		switch(mViewState)
		{
		case IListViewState.LVS_NORMAL:
		{
			if (offset > 0)
			{		
				mHeadView.setPadding(0, offset - mHeadContentHeight, 0, 0);
				switchViewState(IListViewState.LVS_PULL_REFRESH);
			}
		}
		break;
		case IListViewState.LVS_PULL_REFRESH:
		{
			setSelection(0);
			mHeadView.setPadding(0, offset - mHeadContentHeight, 0, 0);
			if (offset < 0)
			{
				switchViewState(IListViewState.LVS_NORMAL);
			}else if (offset > mHeadContentHeight)
			{
				switchViewState(IListViewState.LVS_RELEASE_REFRESH);
			}
		}
		break;
		case IListViewState.LVS_RELEASE_REFRESH:
		{
			setSelection(0);
			mHeadView.setPadding(0, offset - mHeadContentHeight, 0, 0);
			if (offset >= 0 && offset <= mHeadContentHeight)
			{
				mBack = true;
				switchViewState(IListViewState.LVS_PULL_REFRESH);
			}else if (offset < 0)
			{
				switchViewState(IListViewState.LVS_NORMAL);
			}else{

			}
		}
		break;
		default:
			return;
		};		

	}

	private void doActionUp(MotionEvent ev)
	{
		mIsRecord = false;
		mBack = false;

		if (mViewState == IListViewState.LVS_LOADING)
		{
			return ;
		}

		switch(mViewState)
		{
		case IListViewState.LVS_NORMAL:	
			break;
		case IListViewState.LVS_PULL_REFRESH:
			mHeadView.setPadding(0, -1 * mHeadContentHeight, 0, 0);
			switchViewState(IListViewState.LVS_NORMAL);
			break;
		case IListViewState.LVS_RELEASE_REFRESH:
			mHeadView.setPadding(0, 0, 0, 0);
			switchViewState(IListViewState.LVS_LOADING);
			onRefresh();
			break;
		}	

	}

	public void setOnRefreshListener(IOnRefreshListener listener)
	{
		mOnRefreshListener = listener;
	}
	
	private void onRefresh()
	{
		if (mOnRefreshListener != null)
		{
			mOnRefreshListener.OnRefresh();
		}
	}

	public void onRefreshComplete()
	{
		mHeadView.setPadding(0,  -1 * mHeadContentHeight, 0, 0);
		mHeadLastUpdateTime.setText("最近更新:" + new Date().toLocaleString());
		switchViewState(IListViewState.LVS_NORMAL);
	}

	/**切换HeadView视图*/
	private void switchViewState(int state)
	{	
		switch(state)
		{
		case IListViewState.LVS_NORMAL:
		{
			Log.e("TAG", "convert to IListViewState.LVS_NORMAL");
			mHeadArrow.clearAnimation();
			mHeadArrow.setImageResource(R.drawable.arrow);
		}
		break;
		case IListViewState.LVS_PULL_REFRESH:
		{
			Log.e("TAG", "convert to IListViewState.LVS_PULL_REFRESH");
			mHeadProgressBar.setVisibility(View.GONE);
			mHeadArrow.setVisibility(View.VISIBLE);
			mHeadTips.setText("下拉可以刷新");
			mHeadArrow.clearAnimation();

			// 是由RELEASE_To_REFRESH状态转变来的
			if (mBack)
			{
				mBack = false;
				mHeadArrow.clearAnimation();
				mHeadArrow.startAnimation(reverseAnimation);
			}
		}
		break;
		case IListViewState.LVS_RELEASE_REFRESH:
		{
			mHeadProgressBar.setVisibility(View.GONE);
			mHeadArrow.setVisibility(View.VISIBLE);
			mHeadTips.setText("松开获取更多");
			mHeadArrow.clearAnimation();
			mHeadArrow.startAnimation(animation);
		}
		break;
		case IListViewState.LVS_LOADING:
		{
			Log.e("TAG", "convert to IListViewState.LVS_LOADING");
			mHeadProgressBar.setVisibility(View.VISIBLE);
			mHeadArrow.clearAnimation();
			mHeadArrow.setVisibility(View.GONE);
			mHeadTips.setText("载入中...");
		}
		break;
		default:
			return;
		}
		mViewState = state;
	}

	/****************************************
	 * 				此处应有分割线				*
	 ****************************************/

	/**ListView状态*/
	public interface IListViewState{
		/**普通状态*/
		int LVS_NORMAL = 0;

		/**下拉刷新状态*/
		int LVS_PULL_REFRESH = 1;

		/**松开刷新状态*/
		int LVS_RELEASE_REFRESH = 2;

		/**加载状态*/
		int LVS_LOADING = 3;
	}

	/**头部刷新监听器*/
	public interface IOnRefreshListener
	{
		/**刷新事件，主界面实现*/
		void OnRefresh();
	}
}
