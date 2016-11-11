package com.soundlifegroup.rongchen;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class CommApplication extends Application {
	public static Context context;
	private static CommApplication instance;
	public volatile Bundle customizedBundle;
	public static String token;

	public static CommApplication getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		context = getApplicationContext();
		customizedBundle = new Bundle();
	}
}
