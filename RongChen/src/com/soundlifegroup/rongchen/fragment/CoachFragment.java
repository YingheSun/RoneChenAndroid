package com.soundlifegroup.rongchen.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.Coach_details_Activity;
import com.soundlifegroup.rongchen.CommApplication;
import com.soundlifegroup.rongchen.R;
import com.soundlifegroup.rongchen.adapter.Coach_Adapter;
import com.soundlifegroup.rongchen.model.Coach_Model;
import com.soundlifegroup.rongchen.model.Coach_Model.Coach_Results;
import com.soundlifegroup.rongchen.model.Profile_List;
import com.soundlifegroup.rongchen.model.Profile_List.Results;
import com.soundlifegroup.rongchen.utils.AccessServerUtil;
import com.soundlifegroup.rongchen.utils.AccessServerUtil.ServerResult;
import com.soundlifegroup.rongchen.utils.HttpUrl;

//教练
public class CoachFragment extends Fragment implements OnClickListener,
		OnItemClickListener {
	@ViewInject(R.id.tv_titlebar_left)
	private TextView tv_titlebar_left;
	@ViewInject(R.id.tv_titlebar_title)
	private TextView tv_titlebar_title;
	@ViewInject(R.id.tv_titlebar_right)
	private TextView tv_titlebar_right;
	@ViewInject(R.id.framelayout_bar)
	private FrameLayout framelayout_bar;
	@ViewInject(R.id.framelayout_search)
	private FrameLayout framelayout_search;
	@ViewInject(R.id.et_search_text)
	private EditText et_search_text;
	@ViewInject(R.id.tv_cancel)
	private TextView tv_cancel;
	@ViewInject(R.id.tv_search)
	private TextView tv_search;
	@ViewInject(R.id.coach_information_gridview)
	private PullToRefreshGridView coach_information_gridview;
	private RequestParams params;
	private Coach_Adapter coach_adapter;
	private List<Coach_Results> list;
	private int page = 1, size, count;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.coach_fragment, null);
		ViewUtils.inject(this, view);
		init();
		HttpUtils();
		setListener();
		return view;
	}

	private void init() {
		tv_titlebar_title.setText("教练信息");
		Drawable drawable_menu = getResources().getDrawable(R.drawable.menu);
		drawable_menu.setBounds(0, 0, drawable_menu.getMinimumWidth(),
				drawable_menu.getMinimumHeight());
		Drawable drawable_search = getResources()
				.getDrawable(R.drawable.search);
		drawable_search.setBounds(0, 0, drawable_search.getMinimumWidth(),
				drawable_search.getMinimumHeight());
		tv_titlebar_left.setCompoundDrawables(drawable_menu, null, null, null);
		tv_titlebar_right.setCompoundDrawables(null, null, drawable_search,
				null);
		list = new ArrayList<Coach_Results>();
		coach_adapter = new Coach_Adapter(getActivity(), list);
		coach_information_gridview.setAdapter(coach_adapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_right:
			framelayout_bar.setVisibility(View.GONE);
			framelayout_search.setVisibility(View.VISIBLE);
			list.clear();
			break;
		case R.id.tv_cancel:
			framelayout_search.setVisibility(View.GONE);
			framelayout_bar.setVisibility(View.VISIBLE);
			page = 1;
			HttpUtils();
			break;
		case R.id.tv_search:
			HttpUtils_search();
			break;

		}

	}

	protected void setListener() {
		tv_titlebar_right.setOnClickListener(this);
		tv_cancel.setOnClickListener(this);
		tv_search.setOnClickListener(this);
		coach_information_gridview.setMode(Mode.PULL_FROM_END);
		coach_information_gridview
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

		coach_information_gridview.setOnItemClickListener(this);

	}

	public void HttpUtils() {
		params = new RequestParams();
		params.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		AccessServerUtil.server_get(getActivity(), HttpUrl.coaches_url
				+ "?page=" + page, params, Coach_Model.class,
				new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						coach_information_gridview.onRefreshComplete();
						Coach_Model coach_list = (Coach_Model) object;
						count = coach_list.getCount();
						if (coach_list.getResults() != null) {
							if (page == 1) {
								list.clear();
								list.addAll(coach_list.getResults());
							} else {
								list.addAll(coach_list.getResults());
							}
							page++;
						}
						coach_adapter.notifyDataSetChanged();
						coach_information_gridview.onRefreshComplete();

					}

					@Override
					public void onFailure(String code, String info) {
						coach_information_gridview.onRefreshComplete();
					}
				});

	}

	public void HttpUtils_search() {
		params = new RequestParams();
		params.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		AccessServerUtil.server_get(getActivity(), HttpUrl.coaches_url
				+ "?search=" + et_search_text.getText().toString(), params,
				Coach_Model.class, new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						coach_information_gridview.onRefreshComplete();
						Coach_Model coach_list = (Coach_Model) object;
						count = coach_list.getCount();
						if (coach_list.getResults() != null) {
							list.addAll(coach_list.getResults());
						}
						coach_adapter.notifyDataSetChanged();
						coach_information_gridview.onRefreshComplete();

					}

					@Override
					public void onFailure(String code, String info) {
						coach_information_gridview.onRefreshComplete();
					}
				});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		CommApplication.getInstance().customizedBundle.putString("name", list
				.get(position).getName());
		CommApplication.getInstance().customizedBundle.putString("image",
				"http://image.soundlifeonline.com/p/"
						+ list.get(position).getAvatar() + "@!mobile");
		CommApplication.getInstance().customizedBundle.putString("event", list
				.get(position).getIntro());
		CommApplication.getInstance().customizedBundle.putString("coach_id",
				list.get(position).getId());
		startActivity(Coach_details_Activity.class);

	}

	public void startActivity(Class target) {
		Intent intent = new Intent(getActivity(), target);
		startActivity(intent);
	}

}
