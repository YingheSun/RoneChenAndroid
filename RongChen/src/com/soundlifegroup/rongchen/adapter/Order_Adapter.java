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
import com.soundlifegroup.rongchen.model.Order_List.Order;
import com.soundlifegroup.rongchen.model.Order_List.Order.OrderItem;
import com.soundlifegroup.rongchen.model.Profile_List.Results;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class Order_Adapter extends BaseAdapter {
	// private Context context;

	private Context mContext;
	private List<OrderItem> dataList;

	public Order_Adapter(Context context, List<OrderItem> dataList) {
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
			convertView = View.inflate(mContext, R.layout.order_adapter, null);
			holder = new Holder();
			ViewUtils.inject(holder, convertView);
			convertView.setTag(holder);

		}
		OrderItem results = dataList.get(position);
		if (results.getCoverImage().equals("")) {
			holder.order_image.setImageResource(R.drawable.bg_list);
		} else {
			Picasso.with(parent.getContext())
					.load("http://image.soundlifeonline.com/p/"
							+ results.getCoverImage() + "@!large")
					.placeholder(R.drawable.bg_list).into(holder.order_image);
		}

		holder.tv_name.setText(results.getName());
		holder.tv_price.setText("￥"+results.getPrice());
		holder.tv_number.setText("x"+results.getAmount());
		holder.total_price.setText("￥"+results.getPrice()
				* results.getAmount());

		return convertView;
	}

	class Holder {
		@ViewInject(R.id.order_image)
		ImageView order_image;
		@ViewInject(R.id.tv_name)
		TextView tv_name;
		@ViewInject(R.id.tv_price)
		TextView tv_price;
		@ViewInject(R.id.tv_number)
		TextView tv_number;
		@ViewInject(R.id.total_price)
		TextView total_price;
	}

}
