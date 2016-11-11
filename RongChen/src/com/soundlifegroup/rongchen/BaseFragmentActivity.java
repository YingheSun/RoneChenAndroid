package com.soundlifegroup.rongchen;

import java.io.Serializable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public abstract class BaseFragmentActivity extends FragmentActivity implements
		OnClickListener {
	public Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
	}

	/**
	 * 子类用来初始化操作
	 */
	protected abstract void init();

	/**
	 * 子类用来初始化监听
	 */
	protected abstract void setListner();

	/**
	 * 初始化标题,不需要右按钮，传空即可
	 * 
	 * @param left
	 * @param title
	 * @param right
	 */
	protected void initTitle(String left, String title, String right) {
		TextView titlebar_left = (TextView) findViewById(R.id.tv_titlebar_left);
		TextView titlebar_title = (TextView) findViewById(R.id.tv_titlebar_title);
		TextView titlebar_right = (TextView) findViewById(R.id.tv_titlebar_right);

		if (left == null) {
			titlebar_left.setVisibility(View.INVISIBLE);
		} else {
			titlebar_left.setText(left);
			titlebar_left.setVisibility(View.VISIBLE);
		}
		if (right == null) {
			titlebar_right.setVisibility(View.INVISIBLE);
		} else {
			titlebar_right.setText(right);
			titlebar_right.setVisibility(View.VISIBLE);
		}
		titlebar_title.setText(title);
		titlebar_left.setOnClickListener(this);
		titlebar_right.setOnClickListener(this);
		titlebar_title.setOnClickListener(this);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.gc();
	}

	// public void HttpXutils(final Activity activity, final String url,
	// final RequestParams params, final Class<?> c,
	// final ServerResult daoResult) {
	// HttpUtils http = new HttpUtils();
	// http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {
	//
	// @Override
	// public void onFailure(HttpException arg0, String result) {
	// daoResult.onFailure("0", result);
	// }
	//
	// @Override
	// public void onSuccess(ResponseInfo<String> results) {
	// Gson json = new Gson();
	// Object o;
	// try {
	// o = c.newInstance();
	// } catch (InstantiationException e) {
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// e.printStackTrace();
	// }
	// o = json.fromJson(results.result, c);
	// daoResult.onSuccess(o);
	//
	// }
	// });
	// }
	//
	// public interface ServerResult {
	// public void onFailure(String code, String info);
	//
	// public void onSuccess(Object object);
	// }

	public void startActivity(Class target) {
		Intent intent = new Intent(this, target);
		startActivity(intent);
	}

	/**
	 * 启动新的Activity并传递一定的参数
	 */
	public void startActivity(Class<?> target, Bundle bundle) {
		Intent intent = new Intent(this, target);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	/**
	 * 
	 * @描述:启动新的Activity并传递Serializable参数
	 * 
	 */
	public void startActivity(Class target, String key, Serializable value) {
		Intent intent = new Intent(this, target);
		Bundle bundle = new Bundle();
		bundle.putSerializable(key, value);
		intent.putExtras(bundle);
		startActivity(intent);
	}

}
