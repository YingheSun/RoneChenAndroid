package com.soundlifegroup.rongchen.model;

import java.util.List;

public class Submit_Order_Model {
	private String url;
	private String id;
	private String code;
	private String totalPrice;
	private String status;
	private String comment;
	public Submit_User user;
	private String createDate;
	private String receiver;
	private String receiverAddress;
	private String receiverPhone;
	public List<Submit_OrderItem> orderItem;
	
	public class Submit_User {
		private String url;
		private String username;
		private String id;
		private boolean is_superuser;
		private boolean is_staff;
		private boolean is_active;
		private String last_login;
		public Submit_Profile profile;
		public List<Submit_Groups> groups;
		
		public class Submit_Profile {
			private String url;
			private String email;
			private String mobile;
			private String gender;
			private String avatar;
			private String wechat;
			private String nickname;
			private String IDCard;
			private String IDCardImage;
			private String createDate;
			private String realName;
			private String level;
			private String status;
			private String score;
			private String payAccount;
			private String recommendTotalCount;
			private String recommendSuccessCount;
			private String event;
   
		}
		
		public class Submit_Groups{
			
		}
		
	}
	
	public class Submit_OrderItem{
		
	}
}
