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
import com.soundlifegroup.rongchen.model.Order_List.Order;


public class Order_History_Adapter extends BaseAdapter {
//	private Context context;

	private Context mContext;
	private List<Order> dataList;

	public Order_History_Adapter(Context context, List<Order> dataList) {
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
			convertView = View.inflate(mContext, R.layout.order_history_adapter,
					null);
			holder = new Holder();
			ViewUtils.inject(holder, convertView);
			convertView.setTag(holder);

		}
		Order results = dataList.get(position);
		String Date=results.getCreateDate().substring(0, 10);
		holder.tv_time.setText(Date);
		holder.tv_price.setText(results.getTotalPrice());
		holder.tv_name.setText(results.getOrderItem().get(0).getName());
		
		return convertView;
	}

	class Holder {

		@ViewInject(R.id.tv_time)
		TextView tv_time;
		@ViewInject(R.id.tv_price)
		TextView tv_price;
		@ViewInject(R.id.tv_name)
		TextView tv_name;
		

	}
}
