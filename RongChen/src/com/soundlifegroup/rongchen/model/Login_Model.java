package com.soundlifegroup.rongchen.model;

/**
 * Created by wang on 2016/9/27.
 */
public class Login_Model {
	private User user;
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public class User {
		private Profile profile;
		private String url;
		private String username;

		private String id;
		private boolean is_superuser;
		private boolean is_staff;
		private boolean is_active;
		private String last_login;

		public Profile getProfile() {
			return profile;
		}

		public void setProfile(Profile profile) {
			this.profile = profile;
		}

		public String getLast_login() {
			return last_login;
		}

		public void setLast_login(String last_login) {
			this.last_login = last_login;
		}

		public boolean is_active() {
			return is_active;
		}

		public void setIs_active(boolean is_active) {
			this.is_active = is_active;
		}

		public boolean is_superuser() {
			return is_superuser;
		}

		public void setIs_superuser(boolean is_superuser) {
			this.is_superuser = is_superuser;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public boolean is_staff() {
			return is_staff;
		}

		public void setIs_staff(boolean is_staff) {
			this.is_staff = is_staff;
		}
	}

	class Profile {
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

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getScore() {
			return score;
		}

		public void setScore(String score) {
			this.score = score;
		}

		public String getPayAccount() {
			return payAccount;
		}

		public void setPayAccount(String payAccount) {
			this.payAccount = payAccount;
		}

		public String getRecommendTotalCount() {
			return recommendTotalCount;
		}

		public void setRecommendTotalCount(String recommendTotalCount) {
			this.recommendTotalCount = recommendTotalCount;
		}

		public String getRecommendSuccessCount() {
			return recommendSuccessCount;
		}

		public void setRecommendSuccessCount(String recommendSuccessCount) {
			this.recommendSuccessCount = recommendSuccessCount;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getCreateDate() {
			return createDate;
		}

		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getWechat() {
			return wechat;
		}

		public void setWechat(String wechat) {
			this.wechat = wechat;
		}

		public String getAvatar() {
			return avatar;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getIDCard() {
			return IDCard;
		}

		public void setIDCard(String IDCard) {
			this.IDCard = IDCard;
		}

		public String getIDCardImage() {
			return IDCardImage;
		}

		public void setIDCardImage(String IDCardImage) {
			this.IDCardImage = IDCardImage;
		}

		public String getRealName() {
			return realName;
		}

		public void setRealName(String realName) {
			this.realName = realName;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}
	}
}
