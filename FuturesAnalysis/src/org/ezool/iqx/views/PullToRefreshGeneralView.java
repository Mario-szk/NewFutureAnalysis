package org.ezool.iqx.views;

import com.handmark.pulltorefresh.library.OverscrollHelper;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.handmark.pulltorefresh.library.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * һ������ͼ���Գ�����Ļ������ˢ�½���ؼ�
 */
public class PullToRefreshGeneralView extends PullToRefreshScrollView{
	
	public PullToRefreshGeneralView(Context context) {
		super(context);
	}

	public PullToRefreshGeneralView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PullToRefreshGeneralView(Context context, Mode mode) {
		super(context, mode);
	}

	public PullToRefreshGeneralView(Context context, Mode mode, AnimationStyle style) {
		super(context, mode, style);
	}
	
	@Override
	protected ScrollView createRefreshableView(Context context, AttributeSet attrs) {
		ScrollView scrollView;
		if (VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD) {
			scrollView = new InternalGeneralViewSDK9(context, attrs);
		} else {
			scrollView = new ScrollView(context, attrs);
		}

		scrollView.setId(R.id.scrollview);
		return scrollView;
	}
	
	@TargetApi(9)
	final class InternalGeneralViewSDK9 extends ScrollView {

		public InternalGeneralViewSDK9(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		@Override
		protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX,
				int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

			final boolean returnValue = super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
					scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);

			// Does all of the hard work...
			OverscrollHelper.overScrollBy(PullToRefreshGeneralView.this, deltaX, scrollX, deltaY, scrollY,
					getScrollRange(), isTouchEvent);

			return returnValue;
		}

		/**
		 * ��scrollview��������ҪΨһ�����
		 * scrollview��������ͼ��Ϊ������ͼ��ϵ
		 * header + scrollview + tailer
		 *            ����
		 *           frameview
		 * scrollviewռ������������ͼ�Ĵ�С
		 * Ϊ�˽�frameview������������ͼ����Ҫ��ԭʼ��onmeasure�������ٴ���scrollview�Ŀ�Ⱥ͸߶�Ϊ��׼����
		 * frameview��measure
		 * @param widthMeasureSpec
		 * @param heightMeasureSpec
		 */
		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			if (getChildCount() > 0) {
				View child = getChildAt(0);
				child.measure(MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), MeasureSpec.EXACTLY), 
						MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), MeasureSpec.EXACTLY));
			}
		}
		
		/**
		 * Taken from the AOSP ScrollView source
		 */
		private int getScrollRange() {
			int scrollRange = 0;
			if (getChildCount() > 0) {
				View child = getChildAt(0);
				scrollRange = Math.max(0, child.getHeight() - (getHeight() - getPaddingBottom() - getPaddingTop()));
			}
			return scrollRange;
		}
	}
}
