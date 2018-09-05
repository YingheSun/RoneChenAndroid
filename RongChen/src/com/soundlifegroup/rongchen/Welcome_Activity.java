package com.soundlifegroup.rongchen;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.soundlifegroup.rongchen.model.Login_Model;
import com.soundlifegroup.rongchen.utils.AccessServerUtil;
import com.soundlifegroup.rongchen.utils.HttpUrl;
import com.soundlifegroup.rongchen.utils.SpUtils;
import com.soundlifegroup.rongchen.utils.AccessServerUtil.ServerResult;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class Welcome_Activity extends BaseFragmentActivity {
	private Handler handler = new Handler();
	private RequestParams params = new RequestParams();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_activity);
		ViewUtils.inject(this);
		init();
		HttpUtils();
		setListner();

	}

	@Override
	public void onClick(View arg0) {

	}

	@Override
	protected void init() {

	}

	@Override
	protected void setListner() {

	}

	public void HttpUtils() {
		params.addBodyParameter("username",
				SpUtils.getStringSp(context, "username", ""));
		params.addBodyParameter("password",
				SpUtils.getStringSp(context, "password", ""));
		AccessServerUtil.server_post(this, HttpUrl.login_url, params,
				Login_Model.class, new ServerResult() {
					@Override
					public void onSuccess(Object object) {
						Login_Model login_model = (Login_Model) object;
						CommApplication.token = login_model.getToken()
								.toString();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								startActivity(MainActivity.class);
								finish();
							}
						}, 500);

					}

					@Override
					public void onFailure(String code, String info) {
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								startActivity(LogInActivity.class);
								finish();
							}
						}, 500);

					}
				});
	}

}
