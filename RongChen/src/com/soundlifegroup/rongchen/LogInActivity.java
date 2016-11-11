package com.soundlifegroup.rongchen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.model.Login_Model;
import com.soundlifegroup.rongchen.utils.AccessServerUtil;
import com.soundlifegroup.rongchen.utils.AccessServerUtil.ServerResult;
import com.soundlifegroup.rongchen.utils.HttpUrl;
import com.soundlifegroup.rongchen.utils.MD5Tool;
import com.soundlifegroup.rongchen.utils.SpUtils;

public class LogInActivity extends BaseFragmentActivity {
	@ViewInject(R.id.et_login_phone)
	private EditText et_login_phone;
	@ViewInject(R.id.et_login_password)
	private EditText et_login_password;
	@ViewInject(R.id.tv_login)
	private TextView tv_login;
	@ViewInject(R.id.tv_forget_password)
	private TextView tv_forget_password;
	@ViewInject(R.id.tv_register_now)
	private TextView tv_register_now;
	private RequestParams params = new RequestParams();
	private String url_username, url_password;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		if (!(SpUtils.getStringSp(context, "username", "").equals(""))
//				&& !(SpUtils.getStringSp(context, "password", "").equals(""))) {
//			HttpUtils(SpUtils.getStringSp(context, "username", ""),
//					SpUtils.getStringSp(context, "password", ""), params);
//		} else {
			setContentView(R.layout.login_activity);
			ViewUtils.inject(this);
			init();
			setListner();
//		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_login:
			url_username = et_login_phone.getText().toString();
			// YHS md5
			url_password = MD5Tool.MD5(et_login_password.getText().toString());
			HttpUtils(url_username, url_password, params);
			break;
		case R.id.tv_forget_password:
			startActivity(ForgetPassword.class);
			break;
		case R.id.tv_register_now:
			startActivity(RegisterActivity.class);
			break;
		}
	}

	@Override
	protected void init() {

	}

	@Override
	protected void setListner() {
		tv_login.setOnClickListener(this);
		tv_forget_password.setOnClickListener(this);
		tv_register_now.setOnClickListener(this);
	}

	public void HttpUtils(final String username, final String password,
			RequestParams params) {
		params.addBodyParameter("username", username);
		params.addBodyParameter("password", password);
		AccessServerUtil.server_post(this, HttpUrl.login_url, params,
				Login_Model.class, new ServerResult() {
					@Override
					public void onSuccess(Object object) {
						Login_Model login_model = (Login_Model) object;
						CommApplication.token = login_model.getToken()
								.toString();
						SpUtils.saveStringSP(context, "userId", login_model
								.getUser().getId());
						SpUtils.saveStringSP(context, "username", username);
						SpUtils.saveStringSP(context, "password", password);
						finish();
						startActivity(MainActivity.class);
					}

					@Override
					public void onFailure(String code, String info) {
					}
				});
	}

}
