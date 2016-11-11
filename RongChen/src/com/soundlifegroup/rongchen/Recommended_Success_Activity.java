package com.soundlifegroup.rongchen;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class Recommended_Success_Activity extends BaseFragmentActivity {
	@ViewInject(R.id.tv_titlebar_left)
	private TextView tv_titlebar_left;
	@ViewInject(R.id.tv_titlebar_title)
	private TextView tv_titlebar_title;
	@ViewInject(R.id.tv_titlebar_right)
	private TextView tv_titlebar_right;
	@ViewInject(R.id.recommend_message)
	private TextView recommend_message;
	private String phone;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recommended_success);
		ViewUtils.inject(this);
		init();
		setListner();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_left:
			finish();
			break;
		}
	}

	@Override
	protected void init() {
		tv_titlebar_title.setText("推荐学车");
		Drawable drawable_menu = getResources().getDrawable(
				R.drawable.back_left);
		drawable_menu.setBounds(0, 0, drawable_menu.getMinimumWidth(),
				drawable_menu.getMinimumHeight());
		tv_titlebar_left.setCompoundDrawables(drawable_menu, null, null, null);

		phone = CommApplication.getInstance().customizedBundle
				.getString("phone");
		recommend_message.setText(getResources().getString(
				R.string.recommend_content_one)
				+ phone
				+ getResources().getString(R.string.recommend_content_two));
	}

	@Override
	protected void setListner() {
		tv_titlebar_left.setOnClickListener(this);
	}

}
