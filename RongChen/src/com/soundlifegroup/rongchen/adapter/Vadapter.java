package com.soundlifegroup.rongchen.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class Vadapter extends PagerAdapter {
	private List<View> views;

	public Vadapter(List<View> views) {
		this.views = views;
	}
	
	public void destroyItem(View container, int position, Object object) {

		((ViewPager) container).removeView(views.get(position));
	}

	@Override
	public Object instantiateItem(View container, int position){
		

			// ((ViewPager) arg0).addView(list.get(arg1),0);
			((ViewPager) container).addView(views.get(position));
			// TODO: handle exception

		return views.get(position);

	}

	@Override
	public int getCount() {
		return views.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}

}
