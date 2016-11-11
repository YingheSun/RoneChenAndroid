package com.soundlifegroup.rongchen.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.R;
import com.soundlifegroup.rongchen.model.CoachComments_List.CoachComments;
import com.soundlifegroup.rongchen.model.Profile_List.Results;
import com.soundlifegroup.rongchen.view.RoundImageView;
import com.squareup.picasso.Picasso;

public class Evaluate_Adapter extends BaseAdapter {
	// private Context context;

	private Context mContext;
	private List<CoachComments> dataList;

	public Evaluate_Adapter(Context context, List<CoachComments> dataList) {
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
			convertView = View.inflate(mContext, R.layout.evaluate_adapter,
					null);
			holder = new Holder();
			ViewUtils.inject(holder, convertView);
			convertView.setTag(holder);

		}
		CoachComments results = dataList.get(position);

		if (results.getCoachAvatar().length() == 0) {
			holder.iv_student.setImageResource(R.drawable.bg_list);
		} else {
			Picasso.with(parent.getContext())
					.load("http://image.soundlifeonline.com/p/"
							+ results.getCoachAvatar() + "@!large")
					.placeholder(R.drawable.bg_list).into(holder.iv_student);
		}

		holder.tv_name.setText(results.getUserName());
		holder.tv_time.setText(results.getCreateDate());
		holder.tv_content.setText(results.getContent());

		return convertView;
	}

	class Holder {

		@ViewInject(R.id.iv_student)
		RoundImageView iv_student;
		@ViewInject(R.id.tv_name)
		TextView tv_name;
		@ViewInject(R.id.tv_time)
		TextView tv_time;
		@ViewInject(R.id.tv_content)
		TextView tv_content;

	}
}
