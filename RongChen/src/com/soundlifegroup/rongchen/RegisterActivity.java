package com.soundlifegroup.rongchen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.model.Login_Model;
import com.soundlifegroup.rongchen.model.Mobile_Code;
import com.soundlifegroup.rongchen.utils.AccessServerUtil;
import com.soundlifegroup.rongchen.utils.HttpUrl;
import com.soundlifegroup.rongchen.utils.AccessServerUtil.ServerResult;
import com.soundlifegroup.rongchen.utils.MD5Tool;

public class RegisterActivity extends BaseFragmentActivity {
	private int recLen = 60;
	@ViewInject(R.id.iv_back_left)
	private ImageView iv_back_left;
	@ViewInject(R.id.et_register_phone)
	private EditText et_register_phone;
	@ViewInject(R.id.et_register_code)
	private EditText et_register_code;
	@ViewInject(R.id.tv_forget_code)
	private TextView tv_forget_code;
	@ViewInject(R.id.et_password)
	private EditText et_password;
	@ViewInject(R.id.tv_register)
	private TextView tv_register;
	@ViewInject(R.id.tv_login_now)
	private TextView tv_login_now;
	private RequestParams params;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity);
		ViewUtils.inject(this);
		init();
		setListner();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back_left:
			finish();
			break;
		case R.id.tv_forget_code:

			AccessServerUtil.server_get(this, HttpUrl.verifycode_url
					+ "?mobile=" + et_register_phone.getText().toString(),
					null, Mobile_Code.class, new ServerResult() {

						@Override
						public void onSuccess(Object object) {
							// Mobile_Code mobile_code = (Mobile_Code) object;

							if (!(et_register_phone.getText().length() == 11)) {
								Toast.makeText(context, "手机号码格式不正确", 1).show();
							} else {
								Toast.makeText(context, "发送成功", 1).show();
								handler.postDelayed(runnable, 1000);
								tv_forget_code.setClickable(false);
							}

						}

						@Override
						public void onFailure(String code, String info) {
							Toast.makeText(context, "发送失败", 1).show();
						}
					});

			break;
		case R.id.tv_register:
			HttpUtils();
			break;
		case R.id.tv_login_now:
			finish();
			break;

		}
	}

	@Override
	protected void init() {

	}

	@Override
	protected void setListner() {
		iv_back_left.setOnClickListener(this);
		tv_forget_code.setOnClickListener(this);
		tv_register.setOnClickListener(this);
		tv_login_now.setOnClickListener(this);
		params = new RequestParams();
	}

	@SuppressLint("HandlerLeak")
	final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				tv_forget_code.setText(recLen
						+ getResources()
								.getString(R.string.register_sregetcode));
				break;
			case 0:
				reset();
				break;
			}
			super.handleMessage(msg);
		}
	};

	private Runnable runnable = new Runnable() {
		public void run() {
			Message message = new Message();
			message.what = 1;
			recLen--;
			if (recLen == 0) {
				message.what = 0;
			}
			handler.sendMessage(message);
			tv_forget_code.setText(recLen
					+ getResources().getString(R.string.register_sregetcode));
			handler.postDelayed(this, 1000); // postDelayed(this,1000)方法安排一个Runnable对象到主线程队列中
		}

	};

	public void reset() {
		handler.removeCallbacks(runnable); // 停止Timer
		recLen = 60;
		tv_forget_code.setText(getResources().getString(
				R.string.register_regetcode));
		tv_forget_code.setClickable(true);
	}

	public void HttpUtils() {
		params.addBodyParameter("mobile", et_register_phone.getText()
				.toString());
		params.addBodyParameter("verifyCode", et_register_code.getText()
				.toString());
		//YHS md5
		params.addBodyParameter("password", MD5Tool.MD5(et_password.getText().toString()));
		AccessServerUtil.server_post(this, HttpUrl.register_url, params,
				Login_Model.class, new ServerResult() {
					@Override
					public void onSuccess(Object object) {
						Login_Model login_model = (Login_Model) object;
						CommApplication.token = login_model.getToken()
								.toString();
						Toast.makeText(context, "注册成功", 1).show();
						finish();
						startActivity(MainActivity.class);

					}

					@Override
					public void onFailure(String code, String info) {
						Toast.makeText(context, "该号码已注册", 1).show();
					}
				});
	}

}
