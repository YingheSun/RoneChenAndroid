package com.soundlifegroup.rongchen;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.adapter.Vadapter;
import com.soundlifegroup.rongchen.model.Join_Shop_Car;
import com.soundlifegroup.rongchen.model.Product_Model;
import com.soundlifegroup.rongchen.utils.AccessServerUtil;
import com.soundlifegroup.rongchen.utils.HttpUrl;
import com.soundlifegroup.rongchen.utils.SpUtils;
import com.soundlifegroup.rongchen.utils.AccessServerUtil.ServerResult;
import com.squareup.picasso.Picasso;

public class Product_Details_Activity extends BaseFragmentActivity {
	@ViewInject(R.id.tv_titlebar_left)
	private TextView tv_titlebar_left;
	@ViewInject(R.id.tv_titlebar_title)
	private TextView tv_titlebar_title;
	@ViewInject(R.id.tv_titlebar_right)
	private TextView tv_titlebar_right;
	@ViewInject(R.id.advertise_point_group)
	private RadioGroup radiogroup;
	@ViewInject(R.id.tv_name)
	private TextView tv_name;
	@ViewInject(R.id.tv_price)
	private TextView tv_price;
	@ViewInject(R.id.tv_content)
	private TextView tv_content;
	@ViewInject(R.id.but_shoping_car)
	private Button but_shoping_car;

	private List<View> views;

	@ViewInject(R.id.vPager)
	private ViewPager vPager;

	private Vadapter vadapter;

	private String[] mBeans;

	private RequestParams params;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_details_activity);
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
		case R.id.but_shoping_car:
			HttpUtils();
			break;
		}
	}

	@Override
	protected void init() {
		tv_titlebar_title.setText("商品详情");
		Drawable drawable_menu = getResources().getDrawable(
				R.drawable.back_left);
		drawable_menu.setBounds(0, 0, drawable_menu.getMinimumWidth(),
				drawable_menu.getMinimumHeight());
		tv_titlebar_left.setCompoundDrawables(drawable_menu, null, null, null);
		views = new ArrayList<View>();
		vadapter = new Vadapter(views);

		mBeans = new String[3];
		mBeans[0] = CommApplication.getInstance().customizedBundle
				.getString("contentImage1");
		mBeans[1] = CommApplication.getInstance().customizedBundle
				.getString("contentImage2");
		mBeans[2] = CommApplication.getInstance().customizedBundle
				.getString("contentImage3");
		for (int i = 0; i < 3; i++) {
			ImageView image = new ImageView(this);
			image.setScaleType(ScaleType.CENTER_CROP);
			Picasso.with(this)
					.load("http://image.soundlifeonline.com/p/" + mBeans[i]
							+ "@!mobile").placeholder(R.drawable.bg_list)
					.into(image);
			views.add(image);
		}
		vPager.setAdapter(vadapter);
		tv_name.setText(CommApplication.getInstance().customizedBundle
				.getString("name"));
		tv_price.setText("¥"
				+ CommApplication.getInstance().customizedBundle
						.getString("price"));
		tv_content.setText(CommApplication.getInstance().customizedBundle
				.getString("content"));
	}

	@Override
	protected void setListner() {
		tv_titlebar_left.setOnClickListener(this);
		but_shoping_car.setOnClickListener(this);
		vPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrolled(int position, float arg1, int arg2) {
				// TODO Auto-generated method stub
				((RadioButton) radiogroup.getChildAt(position))
						.setChecked(true);

			}

			@Override
			public void onPageScrollStateChanged(int position) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void HttpUtils() {
		params = new RequestParams();
		params.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		params.addBodyParameter("product",
				CommApplication.getInstance().customizedBundle
						.getString("car_id"));
		params.addBodyParameter("amount", "1");
		params.addBodyParameter("user",
				SpUtils.getStringSp(context, "userId", ""));
		AccessServerUtil.server_post(this, HttpUrl.shopcars_url, params,
				Join_Shop_Car.class, new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						Toast.makeText(context, "添加商品成功", 1).show();
					}

					@Override
					public void onFailure(String code, String info) {
						Toast.makeText(context, "添加商品失败", 1).show();
					}
				});
	}

}
