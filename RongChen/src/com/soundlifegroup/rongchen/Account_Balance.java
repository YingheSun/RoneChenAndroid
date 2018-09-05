package com.soundlifegroup.rongchen;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.adapter.Account_Adapter;
import com.soundlifegroup.rongchen.model.Recommends_List;
import com.soundlifegroup.rongchen.model.Transaction_List;
import com.soundlifegroup.rongchen.model.Transaction_List.Results;
import com.soundlifegroup.rongchen.utils.AccessServerUtil;
import com.soundlifegroup.rongchen.utils.SpUtils;
import com.soundlifegroup.rongchen.utils.AccessServerUtil.ServerResult;
import com.soundlifegroup.rongchen.utils.HttpUrl;

public class Account_Balance extends BaseFragmentActivity {
	@ViewInject(R.id.tv_titlebar_left)
	private TextView tv_titlebar_left;
	@ViewInject(R.id.tv_titlebar_title)
	private TextView tv_titlebar_title;
	@ViewInject(R.id.tv_titlebar_right)
	private TextView tv_titlebar_right;
	@ViewInject(R.id.tv_money)
	private TextView tv_money;
	@ViewInject(R.id.tv_alipay)
	private TextView tv_alipay;
	@ViewInject(R.id.account_balance_list)
	private PullToRefreshListView account_balance_list;
	private RequestParams params;
	private Account_Adapter account_adapter;
	private List<Results> list;

	private int page = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_balance);
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
		tv_titlebar_title.setText("账户列表");
		Drawable drawable_menu = getResources().getDrawable(
				R.drawable.back_left);
		drawable_menu.setBounds(0, 0, drawable_menu.getMinimumWidth(),
				drawable_menu.getMinimumHeight());
		tv_titlebar_left.setCompoundDrawables(drawable_menu, null, null, null);
		list = new ArrayList<Transaction_List.Results>();
		account_adapter = new Account_Adapter(getApplicationContext(), list);
		account_balance_list.setAdapter(account_adapter);

	}

	@Override
	protected void setListner() {
		tv_titlebar_left.setOnClickListener(this);
		account_balance_list.setMode(Mode.PULL_FROM_END);
		account_balance_list
		.setOnRefreshListener(new OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				page = 1;
				HttpUtils();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				HttpUtils();
			}
		});
	}

	public void HttpUtils() {

		params = new RequestParams();
		params.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		// params.addBodyParameter("user",
		// SpUtils.getStringSp(context, "userId", ""));
		// String pageStr = "" + page;
		// params.addBodyParameter("page", pageStr);
		String str=SpUtils.getStringSp(context, "userId", "");
		AccessServerUtil.server_get(this, HttpUrl.account_url+ "?user="
				+ str+"&page=" + page, params,
				Transaction_List.class, new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						account_balance_list.onRefreshComplete();
						Transaction_List transaction_list = (Transaction_List) object;
						if (transaction_list.getResults() != null) {
							if (page == 1) {
								list.clear();
								list.addAll(transaction_list.getResults());
							} else {
								list.addAll(transaction_list.getResults());
							}
							page++;
						}

						account_adapter.notifyDataSetChanged();
					}

					@Override
					public void onFailure(String code, String info) {
						account_balance_list.onRefreshComplete();
					}
				});

	}

}
