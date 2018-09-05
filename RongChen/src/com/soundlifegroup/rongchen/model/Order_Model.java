package com.soundlifegroup.rongchen.model;

import java.io.Serializable;
import java.util.List;

public class Order_Model {
	public List<Order_Seria> orderItem;

	public List<Order_Seria> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<Order_Seria> orderItem) {
		this.orderItem = orderItem;
	}

	public static class Order_Seria implements Serializable {
		public int productID;
		public int amount;

		public int getProductID() {
			return productID;
		}

		public void setProductID(int productID) {
			this.productID = productID;
		}

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

	}
}
