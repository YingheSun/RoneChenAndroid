package com.soundlifegroup.rongchen.model;

import java.util.List;

import com.soundlifegroup.rongchen.model.CoachComments_List.CoachComments;

public class Shop_Car_List {
	private int count;
	private String next;
	private String previous;
	public List<Shop> results;

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

	public List<Shop> getResults() {
		return results;
	}

	public void setResults(List<Shop> results) {
		this.results = results;
	}

	public class Shop {
		private String url;
		private int id;
		private String product;
		private String productName;
		private float productPrice;
		private String productCoverImage;
		private int amount;
		private String user;
		private String createDate;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getProduct() {
			return product;
		}

		public void setProduct(String product) {
			this.product = product;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public float getProductPrice() {
			return productPrice;
		}

		public void setProductPrice(float productPrice) {
			this.productPrice = productPrice;
		}

		public String getProductCoverImage() {
			return productCoverImage;
		}

		public void setProductCoverImage(String productCoverImage) {
			this.productCoverImage = productCoverImage;
		}

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getCreateDate() {
			return createDate;
		}

		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}

	}
}
