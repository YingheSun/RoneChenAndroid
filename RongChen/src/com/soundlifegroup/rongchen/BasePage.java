package com.soundlifegroup.rongchen;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public abstract class BasePage extends Fragment {
	protected Context ct;

	public void startActivity(Class target) {
		Intent intent = new Intent(ct, target);
		ct.startActivity(intent);
	}

}
