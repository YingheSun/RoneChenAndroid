package com.soundlifegroup.rongchen;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class News_detail_Activity extends BaseFragmentActivity {
	@ViewInject(R.id.tv_titlebar_left)
	private TextView tv_titlebar_left;
	@ViewInject(R.id.tv_titlebar_title)
	private TextView tv_titlebar_title;
	@ViewInject(R.id.tv_titlebar_right)
	private TextView tv_titlebar_right;
	@ViewInject(R.id.wv_content)
	private WebView wv_content;
	private String title;
	private String content;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_detail);
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
		title = CommApplication.getInstance().customizedBundle
				.getString("title");
		content = CommApplication.getInstance().customizedBundle
				.getString("content");
		tv_titlebar_title.setText(title);
		Drawable drawable_menu = getResources().getDrawable(
				R.drawable.back_left);
		drawable_menu.setBounds(0, 0, drawable_menu.getMinimumWidth(),
				drawable_menu.getMinimumHeight());
		tv_titlebar_left.setCompoundDrawables(drawable_menu, null, null, null);
		wv_content.getSettings().setJavaScriptEnabled(true);
//		Toast.makeText(context, title, 1).show();
		wv_content.getSettings().setLoadWithOverviewMode(true);
		wv_content.getSettings().setUseWideViewPort(true);
		wv_content.loadData(content, "text/html", "UTF-8");
	}

	@Override
	protected void setListner() {
		tv_titlebar_left.setOnClickListener(this);
	}

}
