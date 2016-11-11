package com.soundlifegroup.rongchen.model;

import java.util.List;

import com.soundlifegroup.rongchen.model.Profile_List.Results;

public class Recommends_List {

	private int count;
	private String next;
	private String previous;
	public List<Results> results;

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

	public List<Results> getResults() {
		return results;
	}

	public void setResults(List<Results> results) {
		this.results = results;
	}

	public class Results {
		private String id;
		private String recommendMobile;
		private String recommendUserName;
		private String status;
		private String user;
		private String userAvatar;
		private String userName;
		private String createDate;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getRecommendMobile() {
			return recommendMobile;
		}

		public void setRecommendMobile(String recommendMobile) {
			this.recommendMobile = recommendMobile;
		}

		public String getRecommendUserName() {
			return recommendUserName;
		}

		public void setRecommendUserName(String recommendUserName) {
			this.recommendUserName = recommendUserName;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getUserAvatar() {
			return userAvatar;
		}

		public void setUserAvatar(String userAvatar) {
			this.userAvatar = userAvatar;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getCreateDate() {
			return createDate;
		}

		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}

	}

}
