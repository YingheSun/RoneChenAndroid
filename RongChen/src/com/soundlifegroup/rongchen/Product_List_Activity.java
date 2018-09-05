package com.soundlifegroup.rongchen;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.adapter.Product_Adapter;
import com.soundlifegroup.rongchen.model.Product_Model;
import com.soundlifegroup.rongchen.model.Product_Model.Product_Results;
import com.soundlifegroup.rongchen.utils.AccessServerUtil;
import com.soundlifegroup.rongchen.utils.AccessServerUtil.ServerResult;
import com.soundlifegroup.rongchen.utils.HttpUrl;

public class Product_List_Activity extends BaseFragmentActivity implements
		OnItemClickListener {
	@ViewInject(R.id.tv_titlebar_left)
	private TextView tv_titlebar_left;
	@ViewInject(R.id.tv_titlebar_title)
	private TextView tv_titlebar_title;
	@ViewInject(R.id.tv_titlebar_right)
	private TextView tv_titlebar_right;
	@ViewInject(R.id.product_gridview)
	private PullToRefreshGridView product_gridview;
	private RequestParams params;
	private Product_Adapter product_adapter;
	private List<Product_Results> list;
	private int page = 1, size, count;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_list_activity);
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
		case R.id.tv_titlebar_right:
			startActivity(Shop_Car_Activity.class);
			break;
		}
	}

	@Override
	protected void init() {
		tv_titlebar_title.setText("商场");
		Drawable drawable_menu = getResources().getDrawable(R.drawable.back_left);
		drawable_menu.setBounds(0, 0, drawable_menu.getMinimumWidth(),
				drawable_menu.getMinimumHeight());
		Drawable drawable_search = getResources()
				.getDrawable(R.drawable.shop_car);
		drawable_search.setBounds(0, 0, drawable_search.getMinimumWidth(),
				drawable_search.getMinimumHeight());
		tv_titlebar_left.setCompoundDrawables(drawable_menu, null, null, null);
		tv_titlebar_right.setCompoundDrawables(null, null, drawable_search,
				null);
		list = new ArrayList<Product_Results>();
		product_adapter = new Product_Adapter(this, list);
		product_gridview.setAdapter(product_adapter);
	}

	@Override
	protected void setListner() {
		tv_titlebar_left.setOnClickListener(this);
		tv_titlebar_right.setOnClickListener(this);
		product_gridview.setMode(Mode.PULL_FROM_END);
		product_gridview
				.setOnRefreshListener(new OnRefreshListener2<GridView>() {
					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase<GridView> refreshView) {
						page = 1;
						HttpUtils();
					}

					@Override
					public void onPullUpToRefresh(
							PullToRefreshBase<GridView> refreshView) {
						HttpUtils();
					}
				});

		product_gridview.setOnItemClickListener(this);
	}

	public void HttpUtils() {
		params = new RequestParams();
		params.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		AccessServerUtil.server_get(this, HttpUrl.products_url + "?page="
				+ page + "&status=" + "published", params, Product_Model.class,
				new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						product_gridview.onRefreshComplete();
						Product_Model product_list = (Product_Model) object;
						count = product_list.getCount();
						if (product_list.getResults() != null) {
							if (page == 1) {
								list.clear();
								list.addAll(product_list.getResults());
							} else {
								list.addAll(product_list.getResults());
							}
							page++;
						}
						product_adapter.notifyDataSetChanged();
						product_gridview.onRefreshComplete();

					}

					@Override
					public void onFailure(String code, String info) {
						product_gridview.onRefreshComplete();
					}
				});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		CommApplication.getInstance().customizedBundle.putString(
				"contentImage1", list.get(position).getContentImage1());
		CommApplication.getInstance().customizedBundle.putString(
				"contentImage2", list.get(position).getContentImage2());
		CommApplication.getInstance().customizedBundle.putString(
				"contentImage3", list.get(position).getContentImage3());
		CommApplication.getInstance().customizedBundle.putString(
				"name", list.get(position).getName());
		CommApplication.getInstance().customizedBundle.putString(
				"price", list.get(position).getPrice());
		CommApplication.getInstance().customizedBundle.putString(
				"price", list.get(position).getPrice());
		CommApplication.getInstance().customizedBundle.putString(
				"content", list.get(position).getContent());
		CommApplication.getInstance().customizedBundle.putString(
				"car_id", list.get(position).getId());
		startActivity(Product_Details_Activity.class);
	}

}
