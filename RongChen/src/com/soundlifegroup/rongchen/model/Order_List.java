package com.soundlifegroup.rongchen.model;

import java.io.Serializable;
import java.util.List;

import com.soundlifegroup.rongchen.model.Shop_Car_List.Shop;

public class Order_List {
	private int count;
	private String next;
	private String previous;
	public List<Order> results;

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

	public List<Order> getResults() {
		return results;
	}

	public void setResults(List<Order> results) {
		this.results = results;
	}

	public class Order {
		private String url;
		private String id;
		private String code;
		private String totalPrice;
		private String status;
		private String comment;
		public User user;
		private String createDate;
		private String receiver;
		private String receiverAddress;
		private String receiverPhone;
		public List<OrderItem> orderItem;

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

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(String totalPrice) {
			this.totalPrice = totalPrice;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public String getCreateDate() {
			return createDate;
		}

		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}

		public String getReceiver() {
			return receiver;
		}

		public void setReceiver(String receiver) {
			this.receiver = receiver;
		}

		public String getReceiverAddress() {
			return receiverAddress;
		}

		public void setReceiverAddress(String receiverAddress) {
			this.receiverAddress = receiverAddress;
		}

		public String getReceiverPhone() {
			return receiverPhone;
		}

		public void setReceiverPhone(String receiverPhone) {
			this.receiverPhone = receiverPhone;
		}

		public List<OrderItem> getOrderItem() {
			return orderItem;
		}

		public void setOrderItem(List<OrderItem> orderItem) {
			this.orderItem = orderItem;
		}

		public class OrderItem implements Serializable{
			private int id;
			private String name;
			private int amount;
			private String content;
			private int price;
			private String coverImage;
			private String contentImage1;
			private String contentImage2;
			private String contentImage3;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public int getAmount() {
				return amount;
			}

			public void setAmount(int amount) {
				this.amount = amount;
			}

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

			public int getPrice() {
				return price;
			}

			public void setPrice(int price) {
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

		}

		public class User {
			private String url;
			private String username;
			private String id;
			private boolean is_superuser;
			private boolean is_staff;
			private boolean is_active;
			private boolean last_login;
			public Profile profile;
			public List<Groups> groups;

			public String getUrl() {
				return url;
			}

			public void setUrl(String url) {
				this.url = url;
			}

			public String getUsername() {
				return username;
			}

			public void setUsername(String username) {
				this.username = username;
			}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public boolean isIs_superuser() {
				return is_superuser;
			}

			public void setIs_superuser(boolean is_superuser) {
				this.is_superuser = is_superuser;
			}

			public boolean isIs_staff() {
				return is_staff;
			}

			public void setIs_staff(boolean is_staff) {
				this.is_staff = is_staff;
			}

			public boolean isIs_active() {
				return is_active;
			}

			public void setIs_active(boolean is_active) {
				this.is_active = is_active;
			}

			public boolean isLast_login() {
				return last_login;
			}

			public void setLast_login(boolean last_login) {
				this.last_login = last_login;
			}

			public Profile getProfile() {
				return profile;
			}

			public void setProfile(Profile profile) {
				this.profile = profile;
			}

			public List<Groups> getGroups() {
				return groups;
			}

			public void setGroups(List<Groups> groups) {
				this.groups = groups;
			}

			public class Profile {
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

				public String getUrl() {
					return url;
				}

				public void setUrl(String url) {
					this.url = url;
				}

				public String getEmail() {
					return email;
				}

				public void setEmail(String email) {
					this.email = email;
				}

				public String getMobile() {
					return mobile;
				}

				public void setMobile(String mobile) {
					this.mobile = mobile;
				}

				public String getGender() {
					return gender;
				}

				public void setGender(String gender) {
					this.gender = gender;
				}

				public String getAvatar() {
					return avatar;
				}

				public void setAvatar(String avatar) {
					this.avatar = avatar;
				}

				public String getWechat() {
					return wechat;
				}

				public void setWechat(String wechat) {
					this.wechat = wechat;
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

				public void setIDCard(String iDCard) {
					IDCard = iDCard;
				}

				public String getIDCardImage() {
					return IDCardImage;
				}

				public void setIDCardImage(String iDCardImage) {
					IDCardImage = iDCardImage;
				}

				public String getCreateDate() {
					return createDate;
				}

				public void setCreateDate(String createDate) {
					this.createDate = createDate;
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

				public void setRecommendSuccessCount(
						String recommendSuccessCount) {
					this.recommendSuccessCount = recommendSuccessCount;
				}

				public String getEvent() {
					return event;
				}

				public void setEvent(String event) {
					this.event = event;
				}

			}

			public class Groups {

			}

		}

	}
}
