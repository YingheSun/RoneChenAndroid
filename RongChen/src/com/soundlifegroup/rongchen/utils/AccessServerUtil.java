package com.soundlifegroup.rongchen.utils;

import android.app.Activity;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class AccessServerUtil {
	public static void server_post(final Activity activity, final String url,
			final RequestParams params, final Class<?> c,
			final ServerResult daoResult) {

		HttpUtils http = new HttpUtils(1000);
		http.configTimeout(20000);
		http.configRequestThreadPoolSize(10);
		http.configCurrentHttpCacheExpiry(1000);
		http.configResponseTextCharset("UTF-8");
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				daoResult.onFailure("0", arg1);
			}

			@Override
			public void onSuccess(ResponseInfo<String> results) {
				Gson json = new Gson();
				Object o;
				try {
					o = c.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				o = json.fromJson(results.result, c);
				daoResult.onSuccess(o);
			}
		});
	}

	public static void server_put(final Activity activity, final String url,
			final RequestParams params, final Class<?> c,
			final ServerResult daoResult) {

		HttpUtils http = new HttpUtils(1000);
		http.configTimeout(20000);
		http.configRequestThreadPoolSize(10);
		http.configCurrentHttpCacheExpiry(1000);
		http.configResponseTextCharset("UTF-8");
		http.send(HttpMethod.PUT, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				daoResult.onFailure("0", arg1);
			}

			@Override
			public void onSuccess(ResponseInfo<String> results) {
				Gson json = new Gson();
				Object o;
				try {
					o = c.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				o = json.fromJson(results.result, c);
				daoResult.onSuccess(o);
			}
		});
	}

	public static void server_get(final Activity activity, final String url,
			final RequestParams params, final Class<?> c,
			final ServerResult daoResult) {

		HttpUtils http = new HttpUtils(1000);
		http.configTimeout(20000);
		http.configRequestThreadPoolSize(10);
		http.configCurrentHttpCacheExpiry(1000);
		http.configResponseTextCharset("UTF-8");
		http.send(HttpMethod.GET, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				daoResult.onFailure("0", arg1);
			}

			@Override
			public void onSuccess(ResponseInfo<String> results) {
				Gson json = new Gson();
				Object o;
				try {
					o = c.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				o = json.fromJson(results.result, c);
				daoResult.onSuccess(o);
			}
		});
	}

	public interface ServerResult {
		public void onFailure(String code, String info);

		public void onSuccess(Object object);
	}
}
