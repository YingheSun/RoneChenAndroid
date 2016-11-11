package com.soundlifegroup.rongchen.model;

import java.util.List;

public class Driving_Schedule_Model {
	private int count;
	private String next;
	private String previous;
	public List<Driving_Results> results;

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

	public List<Driving_Results> getResults() {
		return results;
	}

	public void setResults(List<Driving_Results> results) {
		this.results = results;
	}

	public class Driving_Results {
		private String coach;
		private String comment;
		private String createDate;
		private String id;
		private String itemName;
		private String status;
		private String step;
		private String url;
		private String user;

		public String getCoach() {
			return coach;
		}

		public void setCoach(String coach) {
			this.coach = coach;
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

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getStep() {
			return step;
		}

		public void setStep(String step) {
			this.step = step;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

	}

}
