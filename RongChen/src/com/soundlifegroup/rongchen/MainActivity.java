package com.soundlifegroup.rongchen;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.fragment.AccountFragment;
import com.soundlifegroup.rongchen.fragment.CoachFragment;
import com.soundlifegroup.rongchen.fragment.MainFragment;
import com.soundlifegroup.rongchen.fragment.RecommendFragment;
import com.soundlifegroup.rongchen.fragment.StudyFragment;

public class MainActivity extends BaseFragmentActivity implements
		OnCheckedChangeListener {

	@ViewInject(R.id.main_bottom_tabs)
	private RadioGroup group;
	@ViewInject(R.id.main_home_page)
	private RadioButton main_home_page;
	private FragmentManager fragmentManager;// 管理fragment
	private MainFragment mainfragment;
	private RecommendFragment recommendfragment;
	private StudyFragment studyfragment;
	private CoachFragment coachfragment;
	private AccountFragment accountfragment;
	private long exitTime = 0;// 两次按返回退出

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		// 初始化fragmentManager
		fragmentManager = getSupportFragmentManager();
		// 设置默认选中
		main_home_page.setChecked(true);
		group.setOnCheckedChangeListener(this);
		// 切换不同的fragment
		changeFragment(0);

	}

	@Override
	public void onClick(View v) {

	}

	@Override
	protected void init() {

	}

	@Override
	protected void setListner() {

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.main_home_page:
			changeFragment(0);
			break;
		case R.id.main_recommend:
			changeFragment(1);
			break;
		case R.id.main_learn_drive:
			changeFragment(2);
			break;
		case R.id.main_trainer:
			changeFragment(3);
			break;
		case R.id.main_my_home:
			changeFragment(4);
			break;
		default:
			break;
		}

	}

	public void changeFragment(int index)// 同时保存每个fragment
	{
		FragmentTransaction beginTransaction = fragmentManager
				.beginTransaction();
		hideFragments(beginTransaction);
		switch (index) {
		case 0:
			if (mainfragment == null) {
				mainfragment = new MainFragment();
				beginTransaction.add(R.id.main_content, mainfragment);
//				beginTransaction.addToBackStack(null);
//				beginTransaction.replace(R.id.main_content, mainfragment);
				
			} else {
				beginTransaction.show(mainfragment);
			}
			break;
		case 1:
			if (recommendfragment == null) {
				recommendfragment = new RecommendFragment();
				beginTransaction.add(R.id.main_content, recommendfragment);
//				beginTransaction.addToBackStack(null);
//				beginTransaction.replace(R.id.main_content, mainfragment);
			} else {
				beginTransaction.show(recommendfragment);
			}
			break;
		case 2:
			if (studyfragment == null) {
				studyfragment = new StudyFragment();
				beginTransaction.add(R.id.main_content, studyfragment);
//				beginTransaction.addToBackStack(null);
//				beginTransaction.replace(R.id.main_content, mainfragment);
			} else {
				beginTransaction.show(studyfragment);
			}
			break;
		case 3:
			if (coachfragment == null) {
				coachfragment = new CoachFragment();
				beginTransaction.add(R.id.main_content, coachfragment);
//				beginTransaction.addToBackStack(null);
//				beginTransaction.replace(R.id.main_content, mainfragment);
			} else {
				beginTransaction.show(coachfragment);
			}
			break;
		case 4:
			if (accountfragment == null) {
				accountfragment = new AccountFragment();
//				beginTransaction.addToBackStack(null);
//				beginTransaction.replace(R.id.main_content, mainfragment);
				beginTransaction.add(R.id.main_content, accountfragment);
			} else {
				beginTransaction.show(accountfragment);
			}
			break;

		default:
			break;
		}
		beginTransaction.commit();// 需要提交事务
	}

	private void hideFragments(FragmentTransaction transaction) {
		if (mainfragment != null)
			transaction.hide(mainfragment);
		if (recommendfragment != null)
			transaction.hide(recommendfragment);
		if (studyfragment != null)
			transaction.hide(studyfragment);
		if (coachfragment != null)
			transaction.hide(coachfragment);
		if (accountfragment != null)
			transaction.hide(accountfragment);
	}

	@Override
	public void onBackPressed() {

		exit(); // /退出应用

	}

	public void exit() { // 退出应用
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(getApplicationContext(), "再按一次退出程序",
					Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			finish();
		}
	}

//	@Override
//	public void finish() {
//		super.finish();
//	}
	
	public void onResume() {
		//...更新View
		super.onResume();
	}

}
