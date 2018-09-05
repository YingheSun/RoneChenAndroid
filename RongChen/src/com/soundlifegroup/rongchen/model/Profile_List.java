package com.soundlifegroup.rongchen.model;

import java.util.List;

public class Profile_List {
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
		private String title;
		private String content;
		private String category;
		private String comment;
		private String coverImage;
		private String showOrder;
		private String createDate;

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
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

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getCoverImage() {
			return coverImage;
		}

		public void setCoverImage(String coverImage) {
			this.coverImage = coverImage;
		}

		public String getShowOrder() {
			return showOrder;
		}

		public void setShowOrder(String showOrder) {
			this.showOrder = showOrder;
		}

		public String getCreateDate() {
			return createDate;
		}

		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}

	}
}
