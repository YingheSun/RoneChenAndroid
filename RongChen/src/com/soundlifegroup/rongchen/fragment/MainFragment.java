package com.soundlifegroup.rongchen.fragment;

import java.util.ArrayList;
import java.util.List;

import android.R.string;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.CommApplication;
import com.soundlifegroup.rongchen.News_detail_Activity;
import com.soundlifegroup.rongchen.R;
import com.soundlifegroup.rongchen.adapter.Drive_Adapter;
import com.soundlifegroup.rongchen.model.Profile_List;
import com.soundlifegroup.rongchen.model.Profile_List.Results;
import com.soundlifegroup.rongchen.utils.AccessServerUtil;
import com.soundlifegroup.rongchen.utils.AccessServerUtil.ServerResult;
import com.soundlifegroup.rongchen.utils.HttpUrl;

//主页
public class MainFragment extends Fragment implements OnClickListener,
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
	@ViewInject(R.id.driving_schools_list)
	private PullToRefreshListView driving_schools_list;
	private SlidingMenu slidingMenu;
	private RequestParams params;
	private Drive_Adapter already_adapter;
	private List<Results> list;
	private int page = 1, size, count;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_fragment, null);
		ViewUtils.inject(this, view);
		init();
		HttpUtils();
		setListener();
		return view;
	}

	private void init() {
		tv_titlebar_title.setText("新闻公告");
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
		slidingMenu = new SlidingMenu(getActivity());
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setBehindWidth(750);
		slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu
				.attachToActivity(getActivity(), SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setMenu(R.layout.sliding_menu);
		list = new ArrayList<Profile_List.Results>();
		already_adapter = new Drive_Adapter(getActivity(), list);
		driving_schools_list.setAdapter(already_adapter);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_left:
			slidingMenu.toggle(true);
			break;
		case R.id.tv_titlebar_right:
			framelayout_bar.setVisibility(View.GONE);
			framelayout_search.setVisibility(View.VISIBLE);
			list.clear();
			break;
		case R.id.tv_cancel:
			framelayout_search.setVisibility(View.GONE);
			page = 1;
			HttpUtils();
			framelayout_bar.setVisibility(View.VISIBLE);
			break;
		// YHS search
		case R.id.tv_search:
			// get search Info
			HttpUtils_search();
			break;

		}

	}

	protected void setListener() {
		tv_titlebar_left.setOnClickListener(this);
		tv_titlebar_right.setOnClickListener(this);
		tv_cancel.setOnClickListener(this);
		tv_search.setOnClickListener(this);
		et_search_text.setOnClickListener(this);
		driving_schools_list.setMode(Mode.PULL_FROM_END);
		// driving_schools_list.setScrollingWhileRefreshingEnabled(true);
		driving_schools_list
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
		driving_schools_list.setOnItemClickListener(this);

	}

	public void HttpUtils() {
		params = new RequestParams();
		params.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		// String pageStr = "" + page;
		AccessServerUtil.server_get(getActivity(), HttpUrl.articles_url
				+ "?page=" + page, params, Profile_List.class,
				new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						driving_schools_list.onRefreshComplete();
						Profile_List profile_list = (Profile_List) object;
						count = profile_list.getCount();
						if (profile_list.getResults() != null) {
							if (page == 1) {
								list.clear();
								list.addAll(profile_list.getResults());
							} else {
								list.addAll(profile_list.getResults());
							}
							page++;
						}
						already_adapter.notifyDataSetChanged();
						driving_schools_list.onRefreshComplete();

					}

					@Override
					public void onFailure(String code, String info) {
						driving_schools_list.onRefreshComplete();
					}
				});

	}

	// YHS search Request
	public void HttpUtils_search() {
		params = new RequestParams();
		params.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		AccessServerUtil.server_get(getActivity(), HttpUrl.articles_url
				+ "?search=" + et_search_text.getText().toString(), params,
				Profile_List.class, new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						driving_schools_list.onRefreshComplete();
						Profile_List profile_list = (Profile_List) object;
						count = profile_list.getCount();
						if (profile_list.getResults() != null) {
							list.addAll(profile_list.getResults());
						}
						already_adapter.notifyDataSetChanged();
						driving_schools_list.onRefreshComplete();

					}

					@Override
					public void onFailure(String code, String info) {
						driving_schools_list.onRefreshComplete();
					}
				});

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// Toast.makeText(getActivity(), position + "", 1).show();

		CommApplication.getInstance().customizedBundle.putString("title", list
				.get(position).getTitle());
		CommApplication.getInstance().customizedBundle.putString("content",
				list.get(position).getContent());
		startActivity(News_detail_Activity.class);
	}

	public void startActivity(Class target) {
		Intent intent = new Intent(getActivity(), target);
		startActivity(intent);
	}

}
