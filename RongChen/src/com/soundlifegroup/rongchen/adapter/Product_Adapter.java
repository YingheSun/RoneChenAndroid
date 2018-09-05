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
import com.soundlifegroup.rongchen.model.Product_Model.Product_Results;
import com.squareup.picasso.Picasso;

public class Product_Adapter extends BaseAdapter{
	private Context context;

	private Context mContext;
	private List<Product_Results> dataList;

	public Product_Adapter(Context context, List<Product_Results> dataList) {
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
			convertView = View.inflate(mContext, R.layout.product_adapter, null);
			holder = new Holder();
			ViewUtils.inject(holder, convertView);
			convertView.setTag(holder);

		}
		Product_Results results = dataList.get(position);
		if (results.getCoverImage().length() == 0) {
			holder.product_image.setImageResource(R.drawable.bg_list);
		} else {
			Picasso.with(parent.getContext())
					.load("http://image.soundlifeonline.com/p/"
							+ results.getCoverImage() + "@!mobile")
					.placeholder(R.drawable.bg_list).into(holder.product_image);
		}
		holder.product_name.setText(results.getName());
		holder.product_money.setText("¥"+results.getPrice());

		return convertView;
	}

	class Holder {
		@ViewInject(R.id.product_image)
		ImageView product_image;
		@ViewInject(R.id.product_name)
		TextView product_name;
		@ViewInject(R.id.product_money)
		TextView product_money;

	}
}
