package com.soundlifegroup.rongchen;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.adapter.Evaluate_Adapter;
import com.soundlifegroup.rongchen.adapter.Recommended_Adapter;
import com.soundlifegroup.rongchen.model.CoachComments_List;
import com.soundlifegroup.rongchen.model.CoachComments_List.CoachComments;
import com.soundlifegroup.rongchen.model.Profile_List;
import com.soundlifegroup.rongchen.model.Profile_List.Results;
import com.soundlifegroup.rongchen.model.Student_Comment;
import com.soundlifegroup.rongchen.utils.AccessServerUtil;
import com.soundlifegroup.rongchen.utils.HttpUrl;
import com.soundlifegroup.rongchen.utils.SpUtils;
import com.soundlifegroup.rongchen.utils.AccessServerUtil.ServerResult;
import com.soundlifegroup.rongchen.view.RoundImageView;
import com.squareup.picasso.Picasso;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Coach_details_Activity extends BaseFragmentActivity {
	@ViewInject(R.id.tv_titlebar_left)
	private TextView tv_titlebar_left;
	@ViewInject(R.id.tv_titlebar_title)
	private TextView tv_titlebar_title;
	@ViewInject(R.id.iv_image)
	private RoundImageView iv_image;
	@ViewInject(R.id.tv_synopsis)
	private TextView tv_synopsis;
	@ViewInject(R.id.coach_list)
	private ListView coach_list;
	@ViewInject(R.id.et_content)
	private EditText et_content;
	@ViewInject(R.id.iv_send)
	private ImageView iv_send;
	@ViewInject(R.id.iv_reward)
	private ImageView iv_reward;
	@ViewInject(R.id.ly_bottom)
	private LinearLayout ly_bottom;
	@ViewInject(R.id.linear_reward)
	private LinearLayout linear_reward;
	@ViewInject(R.id.tv_20)
	private TextView tv_20;
	@ViewInject(R.id.tv_50)
	private TextView tv_50;
	@ViewInject(R.id.tv_100)
	private TextView tv_100;
	@ViewInject(R.id.tv_cancel)
	private TextView tv_cancel;

	private RequestParams params, params_send, params_reward;
	private Evaluate_Adapter evaluate_adapter;
	private List<CoachComments> list = new ArrayList<CoachComments_List.CoachComments>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.coach_details);
		ViewUtils.inject(this);
		init();
		HttpUtils();
		setListner();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_left:
			finish();
			break;
		case R.id.iv_send:
			if (!et_content.getText().toString().equals("")) {
				HttpUtils_Send();
			} else {
				Toast.makeText(context, "内容不能为空", 1).show();
			}
			break;
		case R.id.iv_reward:
			ly_bottom.setVisibility(View.GONE);
			linear_reward.setVisibility(View.VISIBLE);
			// reward();
			break;
		case R.id.tv_20:
			HttpUtils_Resward("20");
			break;
		case R.id.tv_50:
			HttpUtils_Resward("50");
			break;
		case R.id.tv_100:
			HttpUtils_Resward("100");
			break;
		case R.id.tv_cancel:
			ly_bottom.setVisibility(View.VISIBLE);
			linear_reward.setVisibility(View.GONE);
			break;
		}

	}

	@Override
	protected void init() {
		tv_titlebar_title
				.setText(CommApplication.getInstance().customizedBundle
						.getString("name"));
		Drawable drawable_menu = getResources().getDrawable(
				R.drawable.back_left);
		drawable_menu.setBounds(0, 0, drawable_menu.getMinimumWidth(),
				drawable_menu.getMinimumHeight());
		tv_titlebar_left.setCompoundDrawables(drawable_menu, null, null, null);
		Picasso.with(context)
				.load(CommApplication.getInstance().customizedBundle
						.getString("image")).placeholder(R.drawable.bg_list)
				.into(iv_image);
		tv_synopsis.setText(CommApplication.getInstance().customizedBundle
				.getString("event"));
		evaluate_adapter = new Evaluate_Adapter(getApplicationContext(), list);
		coach_list.setAdapter(evaluate_adapter);
	}

	@Override
	protected void setListner() {
		tv_titlebar_left.setOnClickListener(this);
		iv_reward.setOnClickListener(this);
		iv_send.setOnClickListener(this);
		tv_20.setOnClickListener(this);
		tv_50.setOnClickListener(this);
		tv_100.setOnClickListener(this);
		tv_cancel.setOnClickListener(this);
	}

	public void HttpUtils() {
		params = new RequestParams();
		params.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		AccessServerUtil.server_get(
				this,
				HttpUrl.coachcomments_url
						+ "?coach="
						+ CommApplication.getInstance().customizedBundle
								.getString("coach_id"), params,
				CoachComments_List.class, new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						CoachComments_List coachcomments_list = (CoachComments_List) object;
						list.clear();
						list.addAll(coachcomments_list.getResults());
						evaluate_adapter.notifyDataSetChanged();
					}

					@Override
					public void onFailure(String code, String info) {
					}
				});

	}

	public void HttpUtils_Send() {
		params_send = new RequestParams();
		params_send.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		params_send
				.addBodyParameter("content", et_content.getText().toString());
		params_send.addBodyParameter("star", "1");
		params_send.addBodyParameter("coach",
				CommApplication.getInstance().customizedBundle
						.getString("coach_id"));
		params_send.addBodyParameter("user",
				SpUtils.getStringSp(context, "userId", ""));
		AccessServerUtil.server_post(this, HttpUrl.coachcomments_url,
				params_send, Student_Comment.class, new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						Student_Comment student_comment = (Student_Comment) object;
						Toast.makeText(context, "发表评论成功", 1).show();
					    InputMethodManager imm = (InputMethodManager) getSystemService(context.INPUT_METHOD_SERVICE);  
					    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);  
						et_content.setText("");
						HttpUtils();
					}

					@Override
					public void onFailure(String code, String info) {
						Toast.makeText(context, "发表评论失败", 1).show();
					}
				});

	}

	// public void reward(){
	//
	// iv_send.setVisibility(View.GONE);
	// iv_reward.setVisibility(View.GONE);
	// et_content.setVisibility(View.GONE);
	//
	// final Button btn1 = new Button(this);
	// btn1.setText("20元");
	// btn1.setBackgroundColor(0xfffffff);
	// btn1.setLayoutParams(new
	// ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT));
	// ly_bottom.addView(btn1);
	// btn1.setOnClickListener(new View.OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// HttpUtils_Resward("20");
	// }
	// });
	//
	// final Button btn2 = new Button(this);
	// btn2.setText("50元");
	// btn2.setBackgroundColor(0xfffffff);
	// btn2.setLayoutParams(new
	// ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT));
	// ly_bottom.addView(btn2);
	// btn2.setOnClickListener(new View.OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// HttpUtils_Resward("50");
	// }
	// });
	//
	// final Button btn3 = new Button(this);
	// btn3.setText("100元");
	// btn3.setBackgroundColor(0xfffffff);
	// btn3.setLayoutParams(new
	// ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT));
	// ly_bottom.addView(btn3);
	// btn3.setOnClickListener(new View.OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// HttpUtils_Resward("100");
	// }
	// });
	//
	// final Button cancelBtn = new Button(this);
	// cancelBtn.setText("取消");
	// cancelBtn.setBackgroundColor(0xf8f8f8);
	// cancelBtn.setLayoutParams(new
	// ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT));
	// ly_bottom.addView(cancelBtn);
	// cancelBtn.setOnClickListener(new View.OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// iv_send.setVisibility(View.VISIBLE);
	// iv_reward.setVisibility(View.VISIBLE);
	// et_content.setVisibility(View.VISIBLE);
	// btn1.setVisibility(View.GONE);
	// btn2.setVisibility(View.GONE);
	// btn3.setVisibility(View.GONE);
	// cancelBtn.setVisibility(View.GONE);
	// }
	// });
	//
	// }

	public void HttpUtils_Resward(String tip) {
		params_reward = new RequestParams();
		params_reward.addHeader("Authorization",
				"JWT " + CommApplication.getInstance().token);
		params_reward.addBodyParameter("coachId",
				CommApplication.getInstance().customizedBundle
						.getString("coach_id"));
		params_reward.addBodyParameter("userId",
				SpUtils.getStringSp(context, "userId", ""));
		params_reward.addBodyParameter("score", tip);
		AccessServerUtil.server_post(this, HttpUrl.reward_url, params_reward,
				Student_Comment.class, new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						Toast.makeText(context, "打赏成功", 1).show();
					}

					@Override
					public void onFailure(String code, String info) {
						Toast.makeText(context, "打赏失败", 1).show();
					}
				});
	}
}
