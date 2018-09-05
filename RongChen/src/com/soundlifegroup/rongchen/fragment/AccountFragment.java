package com.soundlifegroup.rongchen.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.Account_Balance;
import com.soundlifegroup.rongchen.Account_Manage_Activity;
import com.soundlifegroup.rongchen.CommApplication;
import com.soundlifegroup.rongchen.MyReceiver;
import com.soundlifegroup.rongchen.Order_List_Activity;
import com.soundlifegroup.rongchen.Phone_Activity;
import com.soundlifegroup.rongchen.Product_List_Activity;
import com.soundlifegroup.rongchen.R;
import com.soundlifegroup.rongchen.Recommended_Record;
import com.soundlifegroup.rongchen.model.Learn_Drive_Model;
import com.soundlifegroup.rongchen.utils.AccessServerUtil;
import com.soundlifegroup.rongchen.utils.AccessServerUtil.ServerResult;
import com.soundlifegroup.rongchen.utils.HttpUrl;
import com.soundlifegroup.rongchen.utils.SpUtils;

//我的
public class AccountFragment extends Fragment implements OnClickListener {
	@ViewInject(R.id.tv_titlebar_left)
	private TextView tv_titlebar_left;
	@ViewInject(R.id.tv_titlebar_title)
	private TextView tv_titlebar_title;
	@ViewInject(R.id.recommended_linear)
	private LinearLayout recommended_linear;
	@ViewInject(R.id.account_linear)
	private LinearLayout account_linear;
	@ViewInject(R.id.phone_linear)
	private LinearLayout phone_linear;
	@ViewInject(R.id.order_linear)
	private LinearLayout order_linear;
	@ViewInject(R.id.shop_linear)
	private LinearLayout shop_linear;
	@ViewInject(R.id.my_name)
	private TextView my_name;
	@ViewInject(R.id.my_id)
	private TextView my_id;
	@ViewInject(R.id.my_editor)
	private TextView my_editor;
	@ViewInject(R.id.my_level)
	private TextView my_level;
	@ViewInject(R.id.my_recommends)
	private TextView my_recommends;
	@ViewInject(R.id.my_account)
	private TextView my_account;
	@ViewInject(R.id.my_order)
	private TextView my_order;
	@ViewInject(R.id.et_shoping)
	private TextView et_shoping;
	@ViewInject(R.id.my_phone)
	private TextView my_phone;
	private RequestParams params = new RequestParams();
	private MyReceiver myReceiver;
	private String name,sex;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.account_fragment, null);
		ViewUtils.inject(this, view);
		init();
		return view;
	}

	private void init() {
		tv_titlebar_title.setText("账户信息");
		account_linear.setOnClickListener(this);
		recommended_linear.setOnClickListener(this);
		my_editor.setOnClickListener(this);
		phone_linear.setOnClickListener(this);
		order_linear.setOnClickListener(this);
		shop_linear.setOnClickListener(this);
		HttpUtils();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.recommended_linear:
			startActivity(Recommended_Record.class);
			break;
		case R.id.account_linear:
			startActivity(Account_Balance.class);
			break;
		case R.id.phone_linear:
			startActivity(Phone_Activity.class);
			break;
		case R.id.my_editor:
			CommApplication.getInstance().customizedBundle.putString(
					"name", name);
			CommApplication.getInstance().customizedBundle.putString(
					"sex", sex);
			startActivity(Account_Manage_Activity.class);
			break;
		case R.id.order_linear:
			startActivity(Order_List_Activity.class);
			break;
		case R.id.shop_linear:
			startActivity(Product_List_Activity.class);
			break;
		}
	}

	public void HttpUtils() {

		params.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		String userStr = SpUtils.getStringSp(getActivity(), "userId", "");
		AccessServerUtil.server_get(getActivity(), HttpUrl.userProfiles_url
				+ "?user=" + userStr, params, Learn_Drive_Model.class,
				new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						Learn_Drive_Model learn_drive = (Learn_Drive_Model) object;
						name=learn_drive.getResults().get(0)
								.getRealName();
						sex=learn_drive.getResults().get(0)
								.getGender();
						my_name.setText(learn_drive.getResults().get(0)
								.getRealName());
						my_id.setText("手机:"
								+ learn_drive.getResults().get(0).getMobile());
						my_phone.setText(learn_drive.getResults().get(0)
								.getMobile());
						my_level.setText(learn_drive.getResults().get(0)
								.getLevel());
						my_recommends.setText("一共推荐"
								+ learn_drive.getResults().get(0)
										.getRecommendTotalCount()
								+ "个,成功推荐"
								+ learn_drive.getResults().get(0)
										.getRecommendSuccessCount() + "个");
						my_account.setText(learn_drive.getResults().get(0)
								.getScore());
					}

					@Override
					public void onFailure(String code, String info) {
					}
				});

	}

	public void startActivity(Class target) {
		Intent intent = new Intent(getActivity(), target);
		startActivity(intent);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		myReceiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		// 接收信息的action
		filter.addAction(Intent.ACTION_EDIT);
		getActivity().registerReceiver(myReceiver, filter);

	}

	//
	// @Override
	// public void onResume() {
	// super.onResume();
	// Toast.makeText(getActivity(), "onResume", 1).show();
	// HttpUtils();
	// }

	public class MyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			my_name.setText(arg1.getStringExtra("msg"));

		}

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		getActivity().unregisterReceiver(myReceiver);
	}

}
