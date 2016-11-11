package com.soundlifegroup.rongchen.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soundlifegroup.rongchen.R;
import com.soundlifegroup.rongchen.model.Recommends_List.Results;


public class Recommended_Adapter extends BaseAdapter {
//	private Context context;

	private Context mContext;
	private List<Results> dataList;

	public Recommended_Adapter(Context context, List<Results> dataList) {
		mContext = context;
		this.dataList = dataList;
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if (convertView != null) {
			holder = (Holder) convertView.getTag();
		} else {
			convertView = View.inflate(mContext, R.layout.recommended_adapter,
					null);
			holder = new Holder();
			ViewUtils.inject(holder, convertView);
			convertView.setTag(holder);

		}
		Results results = dataList.get(position);
		holder.tv_time.setText(results.getCreateDate().split("T")[0]);
		holder.tv_name.setText(results.getRecommendUserName() + results.getRecommendMobile());
		String hoderStr = "";
		if(results.getStatus().equals("check")){
			hoderStr = "处理中";
		}else if(results.getStatus().equals("success")){
			hoderStr = "推荐成功";
		}else if(results.getStatus().equals("fail")){
			hoderStr = "推荐失败";
		}
		holder.tv_state.setText(hoderStr);
		return convertView;
	}

	class Holder {

		@ViewInject(R.id.tv_time)
		TextView tv_time;
		@ViewInject(R.id.tv_name)
		TextView tv_name;
		@ViewInject(R.id.tv_state)
		TextView tv_state;

	}
}
