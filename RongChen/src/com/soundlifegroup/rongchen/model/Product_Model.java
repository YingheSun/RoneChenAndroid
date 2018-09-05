package com.soundlifegroup.rongchen.model;

import java.util.List;

public class Product_Model {
	private int count;
	private String next;
	private String previous;
	public List<Product_Results> results;

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

	public List<Product_Results> getResults() {
		return results;
	}

	public void setResults(List<Product_Results> results) {
		this.results = results;
	}

	public class Product_Results {
		private String url;
		private String id;
		private String name;
		private String price;
		private String coverImage;
		private String contentImage1;
		private String contentImage2;
		private String contentImage3;
		private String content;
		private String status;
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

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getCoverImage() {
			return coverImage;
		}

		public void setCoverImage(String coverImage) {
			this.coverImage = coverImage;
		}

		public String getContentImage1() {
			return contentImage1;
		}

		public void setContentImage1(String contentImage1) {
			this.contentImage1 = contentImage1;
		}

		public String getContentImage2() {
			return contentImage2;
		}

		public void setContentImage2(String contentImage2) {
			this.contentImage2 = contentImage2;
		}

		public String getContentImage3() {
			return contentImage3;
		}

		public void setContentImage3(String contentImage3) {
			this.contentImage3 = contentImage3;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getCreateDate() {
			return createDate;
		}

		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}

	}

}
