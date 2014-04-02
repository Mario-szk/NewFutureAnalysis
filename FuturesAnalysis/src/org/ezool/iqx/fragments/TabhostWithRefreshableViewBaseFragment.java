package org.ezool.iqx.fragments;

import org.ezool.iqx.R;
import org.ezool.iqx.views.PullToRefreshGeneralView;
import org.ezool.iqx.views.SwitchablePullToRefreshGeneralView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * ��һ��tabΪ����ˢ��ʽ��ͼ���ҿ����Զ����tab
 * ȱʡtabΪ3��������tab�������ھ������Զ���
 *
 */
public class TabhostWithRefreshableViewBaseFragment extends TabhostBaseFragment{
	
	private FrameLayout contentFrame;
	
	@Override
	protected View createTabView(Context ctx, int position)
	{
		if (position == 0)
		{
			LayoutInflater li = LayoutInflater.from(ctx);
			PullToRefreshGeneralView v = 
					(PullToRefreshGeneralView)li.inflate(R.layout.pull_to_refresh_view_frame, null, false);
			
			this.contentFrame = (FrameLayout)v.findViewById(R.id.pull_to_refresh_innerview);
			this.contentFrame.addView(this.getFirstChildView(ctx), 
					new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			return v;
		}
		else
		{
			return this.getFirstElseChildView(ctx, position);
		}
	}
	
	/**
	 * �õ���һ��tab��view������ˢ�µģ�
	 * �˷���Ӧ�����������б���д
	 * @param ctx
	 * @return
	 */
	protected View getFirstChildView(Context ctx)
	{
		ImageView iv = new ImageView(ctx);
		iv.setBackgroundResource(R.drawable.weng8);
		return iv;
	}
	
	/**
	 * �õ���һ��tab�����view��������ˢ�µģ�
	 * �˷���Ӧ�����������б���д
	 * @param ctx
	 * @param pos
	 * @return
	 */
	protected View getFirstElseChildView(Context ctx, int pos)
	{
		if (pos == 1)
		{
			ImageView iv = new ImageView(ctx);
			iv.setBackgroundResource(R.drawable.weng8);
			return iv;
		}
		else
		{
			return null;
		}
	}
}
