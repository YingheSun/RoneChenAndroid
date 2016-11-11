package com.soundlifegroup.rongchen.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.R;
import com.soundlifegroup.rongchen.Shop_Car_Activity;
import com.soundlifegroup.rongchen.model.Shop_Car_List.Shop;
import com.squareup.picasso.Picasso;

public class Shop_Adapter extends BaseAdapter {
	// private Context context;
	private Context mContext;
	private List<Shop> dataList;
	private int number;
	private float univalent;
	private float zero = 0;
	private float num;

	public Shop_Adapter(Context context, List<Shop> dataList) {
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
			convertView = View.inflate(mContext, R.layout.shop_adapter, null);
			holder = new Holder();
			ViewUtils.inject(holder, convertView);
			convertView.setTag(holder);

		}
		final Shop results = dataList.get(position);
		if (results.getProductCoverImage().length() == 0) {
			holder.shop_image.setImageResource(R.drawable.bg_list);
		} else {
			Picasso.with(parent.getContext())
					.load("http://image.soundlifeonline.com/p/"
							+ results.getProductCoverImage() + "@!large")
					.placeholder(R.drawable.bg_list).into(holder.shop_image);
		}

		holder.tv_name.setText(results.getProductName());
		holder.total_price.setText(results.getProductPrice() + "");
		univalent = results.getProductPrice();
		holder.tv_number.setText(results.getAmount() + "");
		// number = Integer.parseInt(results.getAmount());
		holder.shop_plus.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				results.setAmount(results.getAmount() + 1);
				Shop_Adapter.this.notifyDataSetChanged();
				results.setProductPrice(results.getProductPrice()
						+ results.getProductPrice() / (results.getAmount() - 1));
				Shop_Adapter.this.notifyDataSetChanged();
				num=0;
				for (int i = 0; i < dataList.size(); i++) {
					num = num + dataList.get(i).getProductPrice();				
				}
				Shop_Car_Activity.tv_total.setText(num + "");
			}
		});

		holder.shop_minus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (results.getAmount() == 1) {
					Toast.makeText(mContext, "已是最小数量", 1).show();
				} else {
					results.setAmount(results.getAmount() - 1);
					Shop_Adapter.this.notifyDataSetChanged();
					results.setProductPrice(results.getProductPrice()
							- results.getProductPrice()
							/ (results.getAmount() + 1));
					Shop_Adapter.this.notifyDataSetChanged();
					num=0;
					for (int i = 0; i < dataList.size(); i++) {
						num = num + dataList.get(i).getProductPrice();
					}
					Shop_Car_Activity.tv_total.setText(num + "");
				}

			}
		});

		return convertView;

	}

	class Holder {
		@ViewInject(R.id.shop_image)
		ImageView shop_image;
		@ViewInject(R.id.tv_name)
		TextView tv_name;
		@ViewInject(R.id.total_price)
		TextView total_price;
		@ViewInject(R.id.shop_plus)
		ImageView shop_plus;
		@ViewInject(R.id.shop_minus)
		ImageView shop_minus;
		@ViewInject(R.id.tv_number)
		TextView tv_number;
	}

}
