package com.soundlifegroup.rongchen.model;

import java.util.List;

public class Learn_Drive_Model {
	private int count;
	private String next;
	private String previous;
	public List<Learn_Results> results;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public List<Learn_Results> getResults() {
		return results;
	}

	public void setResults(List<Learn_Results> results) {
		this.results = results;
	}

	public class Learn_Results {
		private String IDCard;
		private String IDCardImage;
		private String avatar;
		private String createDate;
		private String email;
		private String gender;
		private String level;
		private String mobile;
		private String nickname;
		private String payAccount;
		private String realName;
		private String recommendTotalCount;
		private String recommendSuccessCount;
		private String score;
		private String status;
		private String url;
		private String wechat;

		public String getIDCard() {
			return IDCard;
		}

		public void setIDCard(String iDCard) {
			IDCard = iDCard;
		}

		public String getIDCardImage() {
			return IDCardImage;
		}

		public void setIDCardImage(String iDCardImage) {
			IDCardImage = iDCardImage;
		}

		public String getAvatar() {
			return avatar;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		public String getCreateDate() {
			return createDate;
		}

		public void setCreateDate(String createDate) {
			this.createDate = createDate;
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

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getPayAccount() {
			return payAccount;
		}

		public void setPayAccount(String payAccount) {
			this.payAccount = payAccount;
		}

		public String getRealName() {
			return realName;
		}

		public void setRealName(String realName) {
			this.realName = realName;
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

		public String getScore() {
			return score;
		}

		public void setScore(String score) {
			this.score = score;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getWechat() {
			return wechat;
		}

		public void setWechat(String wechat) {
			this.wechat = wechat;
		}

	}
}
