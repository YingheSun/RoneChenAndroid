package com.soundlifegroup.rongchen;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.model.Login_Model;
import com.soundlifegroup.rongchen.utils.AccessServerUtil;
import com.soundlifegroup.rongchen.utils.AccessServerUtil.ServerResult;
import com.soundlifegroup.rongchen.utils.HttpUrl;
import com.soundlifegroup.rongchen.utils.SpUtils;

public class Account_Manage_Activity extends BaseFragmentActivity {
	private String sex;
	@ViewInject(R.id.tv_titlebar_left)
	private TextView tv_titlebar_left;
	@ViewInject(R.id.tv_titlebar_title)
	private TextView tv_titlebar_title;
	@ViewInject(R.id.et_user_name)
	private EditText et_user_name;
	@ViewInject(R.id.radioGroup)
	private RadioGroup radioGroup;
	@ViewInject(R.id.radioMale)
	private RadioButton radioMale;
	@ViewInject(R.id.radioFemale)
	private RadioButton radioFemale;
	@ViewInject(R.id.tv_userinfo_sub)
	private TextView tv_userinfo_sub;
	private RequestParams params = new RequestParams();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_manage);
		ViewUtils.inject(this);
		init();
		sex = "Male";
		setListner();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_left:
			finish();
			break;
		case R.id.tv_userinfo_sub:
			HttpUtils();
			break;
		case R.id.radioMale:
			sex = "Male";
			break;
		case R.id.radioFemale:
			sex = "Female";
			break;
		}
	}

	@Override
	protected void init() {
		tv_titlebar_title.setText("个人信息");
		Drawable drawable_menu = getResources().getDrawable(
				R.drawable.back_left);
		drawable_menu.setBounds(0, 0, drawable_menu.getMinimumWidth(),
				drawable_menu.getMinimumHeight());
		tv_titlebar_left.setCompoundDrawables(drawable_menu, null, null, null);

	}

	@Override
	protected void setListner() {
		radioMale.setOnClickListener(this);
		radioFemale.setOnClickListener(this);
		tv_userinfo_sub.setOnClickListener(this);
	}

	public void HttpUtils() {
		String userStr = SpUtils.getStringSp(context, "userId", "");
		params.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		params.addBodyParameter("realName", et_user_name.getText().toString());
		params.addBodyParameter("gender", sex);
		AccessServerUtil.server_put(this, HttpUrl.userProfiles_url + userStr
				+ "/", params, Login_Model.class, new ServerResult() {
			@Override
			public void onSuccess(Object object) {
				Toast.makeText(context, "修改成功", 1).show();
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_EDIT);
				intent.putExtra("msg", et_user_name.getText().toString());
				Account_Manage_Activity.this.sendBroadcast(intent);
				finish();
			}

			@Override
			public void onFailure(String code, String info) {
				Toast.makeText(context, "修改失败", 1).show();
				finish();
			}
		});
	}
}