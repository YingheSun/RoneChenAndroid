package com.soundlifegroup.rongchen.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.R;
import com.soundlifegroup.rongchen.model.Profile_List.Results;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class Drive_Adapter extends BaseAdapter {
	// private Context context;

	private Context mContext;
	private List<Results> dataList;

	public Drive_Adapter(Context context, List<Results> dataList) {
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
			convertView = View.inflate(mContext, R.layout.drive_adapter, null);
			holder = new Holder();
			ViewUtils.inject(holder, convertView);
			convertView.setTag(holder);

		}
		Results results = dataList.get(position);
		if (results.getCoverImage().length() == 0) {
			holder.cover_image.setImageResource(R.drawable.bg_list);
		} else {
			Picasso.with(parent.getContext())
					.load("http://image.soundlifeonline.com/p/"
							+ results.getCoverImage() + "@!large")
					.placeholder(R.drawable.bg_list).into(holder.cover_image);
		}

		holder.tv_description.setText(results.getTitle());
		holder.tv_content.setText(results.getComment());
		// holder.tv_create_data.setText(results.getCreateDate());
//		String date_str = results.getCreateDate();

		
		String date = results.getCreateDate();
		date = date.replace("Z", " UTC");//注意是空格+UTC
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
		
		try {
			Date d = format.parse(date );
			holder.tv_create_data.setText(d+"");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return convertView;
	}

	class Holder {
		@ViewInject(R.id.cover_image)
		ImageView cover_image;
		@ViewInject(R.id.tv_description)
		TextView tv_description;
		@ViewInject(R.id.tv_content)
		TextView tv_content;
		@ViewInject(R.id.tv_create_data)
		TextView tv_create_data;

	}

}
