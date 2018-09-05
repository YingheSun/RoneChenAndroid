package com.soundlifegroup.rongchen.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.CommApplication;
import com.soundlifegroup.rongchen.R;
import com.soundlifegroup.rongchen.Recommended_Success_Activity;
import com.soundlifegroup.rongchen.model.Login_Model;
import com.soundlifegroup.rongchen.model.Recommends_Model;
import com.soundlifegroup.rongchen.utils.AccessServerUtil;
import com.soundlifegroup.rongchen.utils.SpUtils;
import com.soundlifegroup.rongchen.utils.AccessServerUtil.ServerResult;
import com.soundlifegroup.rongchen.utils.HttpUrl;

//推荐
public class RecommendFragment extends Fragment implements OnClickListener {
	@ViewInject(R.id.tv_titlebar_left)
	private TextView tv_titlebar_left;
	@ViewInject(R.id.tv_titlebar_title)
	private TextView tv_titlebar_title;
	@ViewInject(R.id.et_phone)
	private EditText et_phone;
	@ViewInject(R.id.et_name)
	private EditText et_name;
	@ViewInject(R.id.tv_recommend)
	private TextView tv_recommend;
	private RequestParams params = new RequestParams();;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.recommend_fragment, null);
		ViewUtils.inject(this, view);
		init();
		return view;
	}

	private void init() {
		tv_titlebar_title.setText("推荐学车");
		Drawable drawable_menu = getResources().getDrawable(R.drawable.menu);
		drawable_menu.setBounds(0, 0, drawable_menu.getMinimumWidth(),
				drawable_menu.getMinimumHeight());
		tv_titlebar_left.setCompoundDrawables(drawable_menu, null, null, null);
		tv_titlebar_left.setOnClickListener(this);
		tv_recommend.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_recommend:
			HttpUtils();
			break;

		}
	}

	public void startActivity(Class target) {
		Intent intent = new Intent(getActivity(), target);
		startActivity(intent);
	}

	public void HttpUtils() {
		params.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		params.addBodyParameter("recommendMobile", et_phone.getText()
				.toString());
		params.addBodyParameter("recommendUserName", et_name.getText()
				.toString());
		params.addBodyParameter("user",
				SpUtils.getStringSp(getActivity(), "userId", ""));
		AccessServerUtil.server_post(getActivity(), HttpUrl.recommends_url,
				params, Recommends_Model.class, new ServerResult() {
					@Override
					public void onSuccess(Object object) {
						CommApplication.getInstance().customizedBundle
								.putString("phone", et_phone.getText()
										.toString());
						startActivity(Recommended_Success_Activity.class);
					}

					@Override
					public void onFailure(String code, String info) {
						Toast.makeText(getActivity(), "失败", 1).show();
					}
				});
	}
}
