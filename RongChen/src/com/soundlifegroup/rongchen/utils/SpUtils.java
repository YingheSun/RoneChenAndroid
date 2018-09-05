package com.soundlifegroup.rongchen.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {

	private static final String CONFIG = "config";
	private static SharedPreferences sharedPreferences;

	private SpUtils() {

	}

	/*
	 * 保存一个String类型的字符串
	 */
	public static void saveStringSP(Context context, String key, String value) {
		if (sharedPreferences == null) {
			sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
		}
		sharedPreferences.edit().putString(key, value).commit();

	}

	/*
	 * 保存一个int类型的字符串
	 */
	public static void saveIntSP(Context context, String key, int value) {
		if (sharedPreferences == null) {
			sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
		}
		sharedPreferences.edit().putInt(key, value).commit();

	}

	/*
	 * 获取一个String类型的字符串
	 */
	public static String getStringSp(Context context, String key, String value) {
		if (sharedPreferences == null) {
			sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
		}
		return sharedPreferences.getString(key, value);

	}

	/*
	 * 获取一个int类型的字符串
	 */
	public static int getIntSp(Context context, String key, int value) {
		if (sharedPreferences == null) {
			sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
		}
		return sharedPreferences.getInt(key, value);

	}

}
