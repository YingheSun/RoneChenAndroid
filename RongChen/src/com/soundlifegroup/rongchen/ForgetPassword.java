package com.soundlifegroup.rongchen;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.model.Login_Model;
import com.soundlifegroup.rongchen.model.Mobile_Code;
import com.soundlifegroup.rongchen.utils.AccessServerUtil;
import com.soundlifegroup.rongchen.utils.HttpUrl;
import com.soundlifegroup.rongchen.utils.MD5Tool;
import com.soundlifegroup.rongchen.utils.SpUtils;
import com.soundlifegroup.rongchen.utils.AccessServerUtil.ServerResult;

public class ForgetPassword extends BaseFragmentActivity {
	private int recLen = 60;
	private String code;
	@ViewInject(R.id.tv_titlebar_left)
	private TextView tv_titlebar_left;
	@ViewInject(R.id.tv_titlebar_title)
	private TextView tv_titlebar_title;
	@ViewInject(R.id.et_phone_number)
	private EditText et_phone_number;
	@ViewInject(R.id.et_forget_code)
	private EditText et_forget_code;
	@ViewInject(R.id.tv_forget_code)
	private TextView tv_forget_code;
	@ViewInject(R.id.et_new_password)
	private EditText et_new_password;
	@ViewInject(R.id.tv_change_password)
	private TextView tv_change_password;
	private RequestParams params;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forget_password);
		ViewUtils.inject(this);
		init();
		setListner();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_forget_code:
			AccessServerUtil.server_get(this, HttpUrl.verifycode_url
					+ "?mobile=" + et_phone_number.getText().toString(), null,
					Mobile_Code.class, new ServerResult() {

						@Override
						public void onSuccess(Object object) {
							// Mobile_Code mobile_code = (Mobile_Code) object;
							Toast.makeText(context, "发送成功", 1).show();
							handler.postDelayed(runnable, 1000);
							tv_forget_code.setClickable(false);
						}

						@Override
						public void onFailure(String code, String info) {
							Toast.makeText(context, "发送失败", 1).show();
						}
					});

			break;
		case R.id.tv_titlebar_left:
			finish();
			break;
		case R.id.tv_change_password:
			HttpUtils();
			break;
		}

	}

	@Override
	protected void init() {
		tv_titlebar_title.setText("忘记密码");
		Drawable drawable_menu = getResources().getDrawable(
				R.drawable.back_left);
		drawable_menu.setBounds(0, 0, drawable_menu.getMinimumWidth(),
				drawable_menu.getMinimumHeight());
		tv_titlebar_left.setCompoundDrawables(drawable_menu, null, null, null);
		params = new RequestParams();
	}

	@Override
	protected void setListner() {
		tv_forget_code.setOnClickListener(this);
		tv_change_password.setOnClickListener(this);
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
		params.addBodyParameter("mobile", et_phone_number.getText().toString());
		params.addBodyParameter("verifyCode", et_forget_code.getText()
				.toString());
		// YHS md5
		params.addBodyParameter("password",
				MD5Tool.MD5(et_new_password.getText().toString()));
		AccessServerUtil.server_post(this, HttpUrl.reset_password_url, params,
				Login_Model.class, new ServerResult() {
					@Override
					public void onSuccess(Object object) {
						Login_Model login_model = (Login_Model) object;
						// CommApplication.token = login_model.getToken()
						// .toString();
						Toast.makeText(context, "修改成功", 1).show();
						finish();

					}

					@Override
					public void onFailure(String code, String info) {
					}
				});
	}
}
