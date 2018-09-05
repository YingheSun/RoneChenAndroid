package com.soundlifegroup.rongchen.fragment;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.CommApplication;
import com.soundlifegroup.rongchen.R;
import com.soundlifegroup.rongchen.Recommended_Success_Activity;
import com.soundlifegroup.rongchen.R.layout;
import com.soundlifegroup.rongchen.model.Driving_Schedule_Model;
import com.soundlifegroup.rongchen.model.Learn_Drive_Model;
import com.soundlifegroup.rongchen.model.Learn_Drive_Model.Learn_Results;
import com.soundlifegroup.rongchen.model.Profile_List;
import com.soundlifegroup.rongchen.model.Profile_List.Results;
import com.soundlifegroup.rongchen.utils.AccessServerUtil;
import com.soundlifegroup.rongchen.utils.HttpUrl;
import com.soundlifegroup.rongchen.utils.SpUtils;
import com.soundlifegroup.rongchen.utils.AccessServerUtil.ServerResult;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//学车
public class StudyFragment extends Fragment implements OnClickListener {
	@ViewInject(R.id.tv_titlebar_left)
	private TextView tv_titlebar_left;
	@ViewInject(R.id.tv_titlebar_title)
	private TextView tv_titlebar_title;
	@ViewInject(R.id.no_registration_linear)
	private LinearLayout no_registration_linear;
	@ViewInject(R.id.already_registered_linear)
	private LinearLayout already_registered_linear;
	@ViewInject(R.id.review_linear)
	private LinearLayout review_linear;
	@ViewInject(R.id.et_phone_number)
	private EditText et_phone_number;
	@ViewInject(R.id.et_name)
	private EditText et_name;
	@ViewInject(R.id.et_id_number)
	private EditText et_id_number;
	@ViewInject(R.id.tv_enroll)
	private TextView tv_enroll;
	@ViewInject(R.id.tv_current_state)
	private TextView tv_current_state;
	@ViewInject(R.id.tv_subject_one)
	private TextView tv_subject_one;
	@ViewInject(R.id.tv_subject_two)
	private TextView tv_subject_two;
	@ViewInject(R.id.tv_subject_three)
	private TextView tv_subject_three;
	// @ViewInject(R.id.tv_subject_four)
	// private TextView tv_subject_four;

	@ViewInject(R.id.tv_subject_one_name)
	private TextView tv_subject_one_name;

	@ViewInject(R.id.tv_subject_two_name)
	private TextView tv_subject_two_name;

	@ViewInject(R.id.tv_subject_three_name)
	private TextView tv_subject_three_name;

	@ViewInject(R.id.tv_state)
	private TextView tv_state;

	// @ViewInject(R.id.tv_subject_four_name)
	// private TextView tv_subject_four_name;

	private RequestParams params, params_enroll, params_studies;
	private String state;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.study_fragment, null);
		ViewUtils.inject(this, view);
		init();
		HttpUtils();
		return view;
	}

	private void init() {
		tv_titlebar_title.setText("学车");
		Drawable drawable_menu = getResources().getDrawable(R.drawable.menu);
		drawable_menu.setBounds(0, 0, drawable_menu.getMinimumWidth(),
				drawable_menu.getMinimumHeight());
		tv_titlebar_left.setCompoundDrawables(drawable_menu, null, null, null);
		tv_titlebar_left.setOnClickListener(this);
		tv_enroll.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_enroll:
			// HttpUtils_Enroll();
			break;

		}
	}

	public void HttpUtils() {
		params = new RequestParams();
		params.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		String userStr = SpUtils.getStringSp(getActivity(), "userId", "");
		AccessServerUtil.server_get(getActivity(), HttpUrl.userProfiles_url
				+ "?user=" + userStr, params, Learn_Drive_Model.class,
				new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						Learn_Drive_Model learn_drive = (Learn_Drive_Model) object;
						if (learn_drive.getResults() != null) {
							state = learn_drive.getResults().get(0).getStatus();
							if (state == "customer") {
								no_registration_linear
										.setVisibility(View.VISIBLE);
								already_registered_linear
										.setVisibility(View.GONE);
								review_linear.setVisibility(View.GONE);
								et_phone_number.setText(learn_drive
										.getResults().get(0).getMobile());
								HttpUtils_Enroll();
							} else if (state == "checking") {
								review_linear.setVisibility(View.VISIBLE);
								no_registration_linear.setVisibility(View.GONE);
								already_registered_linear
										.setVisibility(View.GONE);
							} else {
								already_registered_linear
										.setVisibility(View.VISIBLE);
								tv_state.setText("报名审核中");
								no_registration_linear.setVisibility(View.GONE);
								review_linear.setVisibility(View.GONE);
								HttpUtils_Studies();
							}
						}

					}

					@Override
					public void onFailure(String code, String info) {
					}
				});

	}

	public void HttpUtils_Enroll() {
		params_enroll = new RequestParams();
		params_enroll.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		params_enroll.addBodyParameter("mobile", et_phone_number.getText()
				.toString());
		params_enroll
				.addBodyParameter("realName", et_name.getText().toString());
		params_enroll.addBodyParameter("IDCard", et_id_number.getText()
				.toString());
		AccessServerUtil.server_post(getActivity(), HttpUrl.enroll_url,
				params_enroll, Driving_Schedule_Model.class,
				new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						Driving_Schedule_Model driving_schedule = (Driving_Schedule_Model) object;
						if (driving_schedule.getResults() != null) {

						}

					}

					@Override
					public void onFailure(String code, String info) {
					}
				});

	}

	public void HttpUtils_Studies() {
		params_studies = new RequestParams();
		params_studies.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		String userStr = SpUtils.getStringSp(getActivity(), "userId", "");
		AccessServerUtil.server_get(getActivity(), HttpUrl.studies_url
				+ "?user=" + userStr, params_studies,
				Driving_Schedule_Model.class, new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						Driving_Schedule_Model driving_schedule = (Driving_Schedule_Model) object;
						if (driving_schedule.getResults() != null) {
							tv_subject_one_name.setText(driving_schedule
									.getResults().get(0).getItemName());
							tv_subject_two_name.setText(driving_schedule
									.getResults().get(1).getItemName());
							tv_subject_three_name.setText(driving_schedule
									.getResults().get(2).getItemName());

							if (driving_schedule.getResults().get(0)
									.getStatus().equals("wait")) {
								tv_subject_one.setText("未开始");
							} else if (driving_schedule.getResults().get(0)
									.getStatus().equals("start")) {
								tv_current_state.setText(driving_schedule
										.getResults().get(0).getItemName());
								tv_subject_one.setText("学习中");
								tv_state.setText(driving_schedule.getResults()
										.get(0).getItemName()
										+ "学习中");
							} else if (driving_schedule.getResults().get(0)
									.getStatus().equals("fail")) {
								tv_subject_one.setText("失败");
							} else if (driving_schedule.getResults().get(0)
									.getStatus().equals("pass")) {
								tv_subject_one.setText("通过");
							}

							if (driving_schedule.getResults().get(1)
									.getStatus().equals("wait")) {
								tv_subject_two.setText("未开始");
							} else if (driving_schedule.getResults().get(1)
									.getStatus().equals("start")) {
								tv_current_state.setText(driving_schedule
										.getResults().get(1).getItemName());
								tv_subject_two.setText("学习中");
								tv_state.setText(driving_schedule.getResults()
										.get(1).getItemName()
										+ "学习中");
							} else if (driving_schedule.getResults().get(1)
									.getStatus().equals("fail")) {
								tv_subject_two.setText("失败");
							} else if (driving_schedule.getResults().get(1)
									.getStatus().equals("pass")) {
								tv_subject_two.setText("通过");
							}

							if (driving_schedule.getResults().get(2)
									.getStatus().equals("wait")) {
								tv_subject_three.setText("未开始");
							} else if (driving_schedule.getResults().get(2)
									.getStatus().equals("start")) {
								tv_current_state.setText(driving_schedule
										.getResults().get(2).getItemName());
								tv_subject_three.setText("学习中");
								tv_state.setText(driving_schedule.getResults()
										.get(3).getItemName()
										+ "学习中");
							} else if (driving_schedule.getResults().get(2)
									.getStatus().equals("fail")) {
								tv_subject_three.setText("失败");
							} else if (driving_schedule.getResults().get(2)
									.getStatus().equals("pass")) {
								tv_subject_three.setText("通过");
							}

							// if (driving_schedule.getResults().get(3)
							// .getStatus().equals("wait")) {
							// tv_current_state.setText(driving_schedule
							// .getResults().get(3).getItemName());
							// tv_subject_four.setText("未开始");
							// } else if (driving_schedule.getResults().get(3)
							// .getStatus().equals("start")) {
							// tv_subject_four.setText("学习中");
							// } else if (driving_schedule.getResults().get(3)
							// .getStatus().equals("fail")) {
							// tv_subject_four.setText("失败");
							// } else if (driving_schedule.getResults().get(3)
							// .getStatus().equals("pass")) {
							// tv_subject_four.setText("通过");
							// }
						}

					}

					@Override
					public void onFailure(String code, String info) {
					}
				});

	}
}
