package com.soundlifegroup.rongchen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.adapter.Order_Adapter;
import com.soundlifegroup.rongchen.model.Order_List.Order;
import com.soundlifegroup.rongchen.model.Order_List.Order.OrderItem;

public class Order_Details_Activity extends BaseFragmentActivity {
	@ViewInject(R.id.tv_titlebar_left)
	private TextView tv_titlebar_left;
	@ViewInject(R.id.tv_titlebar_title)
	private TextView tv_titlebar_title;
	@ViewInject(R.id.tv_titlebar_right)
	private TextView tv_titlebar_right;
	@ViewInject(R.id.order_listview)
	private PullToRefreshListView order_listview;
	@ViewInject(R.id.tv_total)
	private TextView tv_total;
	@ViewInject(R.id.et_name)
	private TextView et_name;
	@ViewInject(R.id.et_address)
	private TextView et_address;
	@ViewInject(R.id.et_phone)
	private TextView et_phone;
	private Order_Adapter order_adapter;
	private List<OrderItem> list;
	private int page = 1, size, count;
	private float num;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_details_activity);
		ViewUtils.inject(this);
		init();
		HttpUtils();
		setListner();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_left:
			finish();
			break;
		}
	}

	@Override
	protected void init() {
		tv_titlebar_title.setText("订单");
		Drawable drawable_menu = getResources().getDrawable(
				R.drawable.back_left);
		drawable_menu.setBounds(0, 0, drawable_menu.getMinimumWidth(),
				drawable_menu.getMinimumHeight());
		// Drawable drawable_search = getResources()
		// .getDrawable(R.drawable.search);
		// drawable_search.setBounds(0, 0, drawable_search.getMinimumWidth(),
		// drawable_search.getMinimumHeight());
		tv_titlebar_left.setCompoundDrawables(drawable_menu, null, null, null);
		// tv_titlebar_right.setCompoundDrawables(null, null, drawable_search,
		// null);
		list = new ArrayList<OrderItem>();
		list = (List<OrderItem>) CommApplication.getInstance().customizedBundle
				.getSerializable("list");
		order_adapter = new Order_Adapter(this, list);
		order_listview.setAdapter(order_adapter);
		for (int i = 0; i < list.size(); i++) {
			num=num+list.get(i).getPrice()*list.get(i).getAmount();
		}
		tv_total.setText(num+"");
		et_name.setText("收货人:"
				+ CommApplication.getInstance().customizedBundle
						.getString("name"));
		et_phone.setText("收货电话:"
				+ CommApplication.getInstance().customizedBundle
						.getString("phone"));
		et_address.setText("收货地址:"
				+ CommApplication.getInstance().customizedBundle
						.getString("address"));

		// getWindow().setSoftInputMode(
		// WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
	}

	@Override
	protected void setListner() {
		tv_titlebar_left.setOnClickListener(this);
		tv_titlebar_right.setOnClickListener(this);
		order_listview.setMode(Mode.PULL_FROM_END);
		order_listview.setOnRefreshListener(new OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// page = 1;
				// HttpUtils();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// HttpUtils();
			}
		});

		// order_listview.setOnItemClickListener(this);
	}

	public void HttpUtils() {

	}

}
