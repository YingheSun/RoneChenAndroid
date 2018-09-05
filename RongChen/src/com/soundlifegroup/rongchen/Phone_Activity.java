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
import com.soundlifegroup.rongchen.utils.AccessServerUtil.ServerResult;

public class Phone_Activity extends BaseFragmentActivity {
	private int recLen = 60;
	private String code;
	@ViewInject(R.id.tv_titlebar_left)
	private TextView tv_titlebar_left;
	@ViewInject(R.id.tv_titlebar_title)
	private TextView tv_titlebar_title;
	@ViewInject(R.id.et_phone_num)
	private EditText et_phone_num;
	@ViewInject(R.id.et_confirm_code)
	private EditText et_confirm_code;
	@ViewInject(R.id.tv_get_msg_code)
	private TextView tv_get_msg_code;
	@ViewInject(R.id.tv_confirm_sub)
	private TextView tv_confirm_sub;
	private RequestParams params;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_confirm);
		ViewUtils.inject(this);
		init();
		setListner();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_get_msg_code:
			AccessServerUtil.server_get(this, HttpUrl.verifycode_url
					+ "?mobile=" + et_phone_num.getText().toString(), null,
					Mobile_Code.class, new ServerResult() {

						@Override
						public void onSuccess(Object object) {
							// Mobile_Code mobile_code = (Mobile_Code) object;
							Toast.makeText(context, "发送成功", 1).show();
							handler.postDelayed(runnable, 1000);
							tv_get_msg_code.setClickable(false);
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
		case R.id.tv_confirm_sub:
			HttpUtils();
			break;
		}

	}

	@Override
	protected void init() {
		tv_titlebar_title.setText("手機綁定");
		Drawable drawable_menu = getResources().getDrawable(
				R.drawable.back_left);
		drawable_menu.setBounds(0, 0, drawable_menu.getMinimumWidth(),
				drawable_menu.getMinimumHeight());
		tv_titlebar_left.setCompoundDrawables(drawable_menu, null, null, null);
		params = new RequestParams();
	}

	@Override
	protected void setListner() {
		tv_get_msg_code.setOnClickListener(this);
		tv_confirm_sub.setOnClickListener(this);
	}

	@SuppressLint("HandlerLeak")
	final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				tv_get_msg_code.setText(recLen
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
			tv_get_msg_code.setText(recLen
					+ getResources().getString(R.string.register_sregetcode));
			handler.postDelayed(this, 1000); // postDelayed(this,1000)方法安排一个Runnable对象到主线程队列中
		}

	};

	public void reset() {
		handler.removeCallbacks(runnable); // 停止Timer
		recLen = 60;
		tv_get_msg_code.setText(getResources().getString(
				R.string.register_regetcode));
		tv_get_msg_code.setClickable(true);
	}

	public void HttpUtils() {
		params.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		params.addBodyParameter("mobile", et_phone_num.getText().toString());
		params.addBodyParameter("verifyCode", et_confirm_code.getText()
				.toString());
		params.addBodyParameter("registerType", "mobile");
		AccessServerUtil.server_post(this, HttpUrl.mobile_band_url, params,
				Login_Model.class, new ServerResult() {
					@Override
					public void onSuccess(Object object) {
						Toast.makeText(context, "綁定成功", 1).show();
						finish();
					}

					@Override
					public void onFailure(String code, String info) {
						Toast.makeText(context, "該手機號已經綁定了", 1).show();
						finish();
					}
				});
	}
}