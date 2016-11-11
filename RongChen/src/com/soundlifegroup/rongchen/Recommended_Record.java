package com.soundlifegroup.rongchen;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.adapter.Drive_Adapter;
import com.soundlifegroup.rongchen.adapter.Recommended_Adapter;
import com.soundlifegroup.rongchen.model.Recommends_List;
import com.soundlifegroup.rongchen.model.Recommends_List.Results;
import com.soundlifegroup.rongchen.utils.AccessServerUtil;
import com.soundlifegroup.rongchen.utils.AccessServerUtil.ServerResult;
import com.soundlifegroup.rongchen.utils.HttpUrl;
import com.soundlifegroup.rongchen.utils.SpUtils;

public class Recommended_Record extends BaseFragmentActivity {
	@ViewInject(R.id.tv_titlebar_left)
	private TextView tv_titlebar_left;
	@ViewInject(R.id.tv_titlebar_title)
	private TextView tv_titlebar_title;
	@ViewInject(R.id.tv_titlebar_right)
	private TextView tv_titlebar_right;
	@ViewInject(R.id.recommended_record_list)
	private PullToRefreshListView recommended_record_list;
	private RequestParams params;
	private Recommended_Adapter recommended_adapter;
	private List<Results> list;
	private int page = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recommended_record);
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
		tv_titlebar_title.setText("推荐记录");
		Drawable drawable_menu = getResources().getDrawable(
				R.drawable.back_left);
		drawable_menu.setBounds(0, 0, drawable_menu.getMinimumWidth(),
				drawable_menu.getMinimumHeight());
		tv_titlebar_left.setCompoundDrawables(drawable_menu, null, null, null);
		list = new ArrayList<Recommends_List.Results>();
		recommended_adapter = new Recommended_Adapter(getApplicationContext(),
				list);
		recommended_record_list.setAdapter(recommended_adapter);

	}

	@Override
	protected void setListner() {
		tv_titlebar_left.setOnClickListener(this);
		recommended_record_list.setMode(Mode.PULL_FROM_END);
		recommended_record_list
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
		String str=SpUtils.getStringSp(context, "userId", "");
		AccessServerUtil.server_get(this, HttpUrl.recommends_url + "?user="
				+ str+"&page=" + page, params,
				Recommends_List.class, new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						recommended_record_list.onRefreshComplete();
						Recommends_List recommend_list = (Recommends_List) object;
						if (recommend_list.getResults() != null) {
							if (page == 1) {
								list.clear();
								list.addAll(recommend_list.getResults());
							} else {
								list.addAll(recommend_list.getResults());
							}
							page++;
						}
						recommended_adapter.notifyDataSetChanged();
//						recommended_record_list.onRefreshComplete();
					}

					@Override
					public void onFailure(String code, String info) {
						recommended_record_list.onRefreshComplete();
					}
				});
	}

}
