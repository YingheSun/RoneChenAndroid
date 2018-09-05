package com.soundlifegroup.rongchen.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.R;
import com.soundlifegroup.rongchen.model.Coach_Model.Coach_Results;
import com.soundlifegroup.rongchen.model.Profile_List.Results;
import com.squareup.picasso.Picasso;

public class Coach_Adapter extends BaseAdapter {
	private Context context;

	private Context mContext;
	private List<Coach_Results> dataList;

	public Coach_Adapter(Context context, List<Coach_Results> dataList) {
		mContext = context;
		this.dataList = dataList;
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if (convertView != null) {
			holder = (Holder) convertView.getTag();
		} else {
			convertView = View.inflate(mContext, R.layout.coach_adapter, null);
			holder = new Holder();
			ViewUtils.inject(holder, convertView);
			convertView.setTag(holder);

		}
		Coach_Results results = dataList.get(position);
		if (results.getAvatar().length() == 0) {
			holder.coach_image.setImageResource(R.drawable.bg_list);
		} else {
			Picasso.with(parent.getContext())
					.load("http://image.soundlifeonline.com/p/"
							+ results.getAvatar() + "@!mobile")
					.placeholder(R.drawable.bg_list).into(holder.coach_image);
		}
		holder.coach_name.setText(results.getName());
		holder.coach_years.setText(results.getDrivingYears() + "年教练经验");

		return convertView;
	}

	class Holder {
		@ViewInject(R.id.coach_image)
		ImageView coach_image;
		@ViewInject(R.id.coach_name)
		TextView coach_name;
		@ViewInject(R.id.coach_years)
		TextView coach_years;

	}
}
