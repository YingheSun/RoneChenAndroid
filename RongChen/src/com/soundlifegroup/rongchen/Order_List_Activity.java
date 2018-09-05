package com.soundlifegroup.rongchen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.adapter.Order_Adapter;
import com.soundlifegroup.rongchen.adapter.Order_History_Adapter;
import com.soundlifegroup.rongchen.adapter.Recommended_Adapter;
import com.soundlifegroup.rongchen.model.Order_List;
import com.soundlifegroup.rongchen.model.Order_List.Order;
import com.soundlifegroup.rongchen.model.Order_List.Order.OrderItem;
import com.soundlifegroup.rongchen.model.Product_Model;
import com.soundlifegroup.rongchen.model.Product_Model.Product_Results;
import com.soundlifegroup.rongchen.utils.AccessServerUtil;
import com.soundlifegroup.rongchen.utils.SpUtils;
import com.soundlifegroup.rongchen.utils.AccessServerUtil.ServerResult;
import com.soundlifegroup.rongchen.utils.HttpUrl;

public class Order_List_Activity extends BaseFragmentActivity implements
		OnItemClickListener {
	@ViewInject(R.id.tv_titlebar_left)
	private TextView tv_titlebar_left;
	@ViewInject(R.id.tv_titlebar_title)
	private TextView tv_titlebar_title;
	@ViewInject(R.id.tv_titlebar_right)
	private TextView tv_titlebar_right;
	@ViewInject(R.id.order_listview)
	private PullToRefreshListView order_listview;
	// @ViewInject(R.id.et_name)
	// private EditText et_name;
	// @ViewInject(R.id.et_address)
	// private EditText et_address;
	// @ViewInject(R.id.et_phone)
	// private EditText et_phone;
	// @ViewInject(R.id.but_place_order)
	// private Button but_place_order;
	private RequestParams params;
	private Order_History_Adapter order_history_adapter;
	private List<Order> list;
	private int page = 1, size, count;
	private List<OrderItem> list_Item;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_list_activity);
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
		// case R.id.but_place_order:
		// if (et_name.getText().length() == 0
		// || et_address.getText().length() == 0
		// || et_phone.getText().length() == 0) {
		// Toast.makeText(context, "请输入收货信息", 1).show();
		// } else {
		//
		// }
		// break;
		}
	}

	@Override
	protected void init() {
		tv_titlebar_title.setText("历史订单");
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
		list = new ArrayList<Order>();
		order_history_adapter = new Order_History_Adapter(this, list);
		order_listview.setAdapter(order_history_adapter);
		// et_name.setText(CommApplication.getInstance().customizedBundle
		// .getString("name"));
		// et_address.setText(CommApplication.getInstance().customizedBundle
		// .getString("address"));
		// et_phone.setText(CommApplication.getInstance().customizedBundle
		// .getString("phone"));
		// getWindow().setSoftInputMode(
		// WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
	}

	@Override
	protected void setListner() {
		tv_titlebar_left.setOnClickListener(this);
		tv_titlebar_right.setOnClickListener(this);
		// but_place_order.setOnClickListener(this);
//		order_listview.setMode(Mode.PULL_FROM_END);
		order_listview.setOnRefreshListener(new OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
//				page = 1;
//				HttpUtils();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
//				HttpUtils();
			}
		});

		order_listview.setOnItemClickListener(this);
	}

	public void HttpUtils() {
		params = new RequestParams();
		params.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		AccessServerUtil.server_get(this, HttpUrl.orders_url + "?user="
				+ SpUtils.getStringSp(context, "userId", ""), params,
				Order_List.class, new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						// Toast.makeText(context, "成功", 1).show();
						order_listview.onRefreshComplete();
						Order_List order_list = (Order_List) object;
						// count = order_list.getCount();
						if (order_list.getResults() != null) {
							if (page == 1) {
								list.clear();
								list.addAll(order_list.getResults());
							} else {
								list.addAll(order_list.getResults());
							}
							page++;
							// Toast.makeText(context, list+"", 1).show();
						}
						order_history_adapter.notifyDataSetChanged();
						order_listview.onRefreshComplete();

					}

					@Override
					public void onFailure(String code, String info) {
						order_listview.onRefreshComplete();
					}
				});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// Intent intent=new Intent(this,Order_Details_Activity.class);
		CommApplication.getInstance().customizedBundle.putString("receiver",
				list.get(position).getReceiver());
		CommApplication.getInstance().customizedBundle.putString("phone", list
				.get(position).getReceiverPhone());
		CommApplication.getInstance().customizedBundle.putString("address",
				list.get(position).getReceiverAddress());
		list_Item = new ArrayList<Order_List.Order.OrderItem>();
		list_Item.addAll(list.get(position).getOrderItem());
		CommApplication.getInstance().customizedBundle.putSerializable("list",
				(Serializable) list_Item);
		startActivity(Order_Details_Activity.class);
	}

}
