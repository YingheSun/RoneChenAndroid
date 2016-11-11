package com.soundlifegroup.rongchen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.adapter.Shop_Adapter;
import com.soundlifegroup.rongchen.model.Order_List;
import com.soundlifegroup.rongchen.model.Order_List.Order.OrderItem;
import com.soundlifegroup.rongchen.model.Shop_Car_List;
import com.soundlifegroup.rongchen.model.Shop_Car_List.Shop;
import com.soundlifegroup.rongchen.model.Submit_Order_Model;
import com.soundlifegroup.rongchen.utils.AccessServerUtil;
import com.soundlifegroup.rongchen.utils.AccessServerUtil.ServerResult;
import com.soundlifegroup.rongchen.utils.HttpUrl;
import com.soundlifegroup.rongchen.utils.SpUtils;

public class Shop_Car_Activity extends BaseFragmentActivity implements
		OnItemClickListener {
	@ViewInject(R.id.tv_titlebar_left)
	private TextView tv_titlebar_left;
	@ViewInject(R.id.tv_titlebar_title)
	private TextView tv_titlebar_title;
	@ViewInject(R.id.tv_titlebar_right)
	private TextView tv_titlebar_right;
	@ViewInject(R.id.shop_list)
	private ListView shop_list;
	@ViewInject(R.id.tv_total)
	public static TextView tv_total;
	@ViewInject(R.id.et_name)
	private EditText et_name;
	@ViewInject(R.id.et_address)
	private EditText et_address;
	@ViewInject(R.id.et_phone)
	private EditText et_phone;
	@ViewInject(R.id.but_place_order)
	private Button but_place_order;
	private RequestParams params, params_submit;
	private Shop_Adapter shop_adapter;
	private List<Shop> list;
	private float num;
	private List<Map> list_Item;
	private int[][] item;
	private Map map = new LinkedHashMap();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop_car_activity);
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
		case R.id.but_place_order:
			HttpUtils_Submit();
			// if (et_name.getText().length()==0 ||
			// et_address.getText().length()==0
			// || et_phone.getText().length()==0) {
			// Toast.makeText(context, "请输入收货信息", 1).show();
			// } else {
			// CommApplication.getInstance().customizedBundle.putString(
			// "name", et_name.getText().toString());
			// CommApplication.getInstance().customizedBundle.putString(
			// "address", et_address.getText().toString());
			// CommApplication.getInstance().customizedBundle.putString(
			// "phone", et_phone.getText().toString());
			// HttpUtils_Submit();
			// startActivity(Order_List_Activity.class);
			// }
			break;

		}

	}

	@Override
	protected void init() {
		tv_titlebar_title.setText("购物车");
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
		list = new ArrayList<Shop>();
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
	}

	@Override
	protected void setListner() {
		tv_titlebar_left.setOnClickListener(this);
		but_place_order.setOnClickListener(this);
	}

	public void HttpUtils() {
		params = new RequestParams();
		params.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		AccessServerUtil.server_get(this, HttpUrl.shopcars_url + "?user="
				+ SpUtils.getStringSp(context, "userId", ""), params,
				Shop_Car_List.class, new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						Shop_Car_List shop_car_list = (Shop_Car_List) object;
						if (shop_car_list.getResults() != null) {
							list.addAll(shop_car_list.getResults());
							list_Item = new ArrayList<Map>();
							item = new int[shop_car_list.getResults().size()][2];
							for (int i = 0; i < list.size(); i++) {
								num = num + list.get(i).getProductPrice();
								// item[i][0] = list.get(i).getAmount();
								// item[i][1] = list.get(i).getId();
								// list_Item.add(item[i][0]);
								// list_Item.add(item[i][1]);

								// map.put("productID",
								// shop_car_list.getResults().get(i).getId(),"amount",
								// shop_car_list.getResults().get(i).getAmount());
								// map.put("amount",
								// shop_car_list.getResults().get(i).getAmount());
							}

							// Toast.makeText(context,
							// map+"", 1).show();
							tv_total.setText(num + "");
							shop_adapter = new Shop_Adapter(context, list);
							shop_list.setAdapter(shop_adapter);

						}

					}

					@Override
					public void onFailure(String code, String info) {
					}
				});
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

	}

	public void HttpUtils_Submit() {
		params_submit = new RequestParams();
		params_submit.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		params_submit.addBodyParameter("totalPrice", tv_total.getText()
				.toString());
		params_submit.addBodyParameter("comment", "");
		params_submit
				.addBodyParameter("receiver", et_name.getText().toString());
		params_submit.addBodyParameter("receiverAddress", et_address.getText()
				.toString());
		params_submit.addBodyParameter("receiverPhone", et_phone.getText()
				.toString());
		params_submit.addBodyParameter("orderItem", list_Item + "");
		AccessServerUtil.server_post(this, HttpUrl.orders_url, params_submit,
				Submit_Order_Model.class, new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						Toast.makeText(context, "成功", 1).show();
						// Shop_Car_List shop_car_list = (Shop_Car_List) object;
						// if (shop_car_list.getResults() != null) {
						// list.addAll(shop_car_list.getResults());
						// for (int i = 0; i < list.size(); i++) {
						// num = num + list.get(i).getProductPrice();
						// }
						// tv_total.setText(num + "");
						// shop_adapter = new Shop_Adapter(context, list);
						// shop_list.setAdapter(shop_adapter);
						//
						// }

					}

					@Override
					public void onFailure(String code, String info) {
						Toast.makeText(context, "失败", 1).show();
					}
				});
	}
}
