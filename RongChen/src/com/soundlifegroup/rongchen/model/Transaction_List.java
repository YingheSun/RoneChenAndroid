package com.soundlifegroup.rongchen.model;

import java.util.List;

public class Transaction_List {
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
		private String url;
		private String id;
		private String user;
		private String coach;
		private String score;
		private String tranType;
		private String comment;
		private String createDate;

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

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getCoach() {
			return coach;
		}

		public void setCoach(String coach) {
			this.coach = coach;
		}

		public String getScore() {
			return score;
		}

		public void setScore(String score) {
			this.score = score;
		}

		public String getTranType() {
			return tranType;
		}

		public void setTranType(String tranType) {
			this.tranType = tranType;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public String getCreateDate() {
			return createDate;
		}

		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}

	}

}
