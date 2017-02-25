package cn.edu.jxnu.x3104.riceroll.util;

import cn.edu.jxnu.x3104.riceroll.ui.UserFridgeActivity.MessageItem;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

//增加左划删除
public class MySlideListView extends ListView {
	//private static final String TAG = "MySlideListView";

	private MySlideView mFocusedItemView;

	private int position;

	public MySlideListView(Context context) {
		super(context);
	}

	public MySlideListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MySlideListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void shrinkListItem(int position) {
		View item = getChildAt(position);

		if (item != null) {
			try {
				((MySlideView) item).shrink();
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		position = pointToPosition(x, y);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			//Log.d(TAG, "49_postion=" + position);
			//如果位置不是无效的
			if (position != INVALID_POSITION) {
				MessageItem data = (MessageItem) getItemAtPosition(position);
				mFocusedItemView = data.mySlideView;
			}
		}
		}

		if (mFocusedItemView != null) {
			if(position == INVALID_POSITION){
				mFocusedItemView.shrink();
				return super.onTouchEvent(event);
			}
			mFocusedItemView.onRequireTouchEvent(event);
		}

		return super.onTouchEvent(event);
	}

	public int getPosition() {
		return position;
	}

	@Override
	public ListAdapter getAdapter() {
		// TODO Auto-generated method stub
		return super.getAdapter();
	}

	/*
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		//int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		//上一行有问题，调用之后listview的touch监听失效
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//super.onMeasure(widthMeasureSpec, 50);
	} 
	 */
}
