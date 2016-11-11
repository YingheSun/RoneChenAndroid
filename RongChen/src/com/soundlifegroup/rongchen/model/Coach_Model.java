package com.soundlifegroup.rongchen.model;

import java.util.List;

public class Coach_Model {
	private int count;
	private String next;
	private String previous;
	public List<Coach_Results> results;

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

	public List<Coach_Results> getResults() {
		return results;
	}

	public void setResults(List<Coach_Results> results) {
		this.results = results;
	}

	public class Coach_Results {
		private String url;
		private String id;
		private String name;
		private String gender;
		private String mobile;
		private String level;
		private String drivingYears;
		private String avatar;
		private String licenseImage;
		private String licenseNumber;
		private String IDCardImage;
		private String IDCardNumber;
		private String licenseStartDate;
		private String licenseEndDate;
		private String licenseType;
		private String intro;
		private String introImage;
		private String event;
		private String score;
		private String payAccount;
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

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}

		public String getDrivingYears() {
			return drivingYears;
		}

		public void setDrivingYears(String drivingYears) {
			this.drivingYears = drivingYears;
		}

		public String getAvatar() {
			return avatar;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		public String getLicenseImage() {
			return licenseImage;
		}

		public void setLicenseImage(String licenseImage) {
			this.licenseImage = licenseImage;
		}

		public String getLicenseNumber() {
			return licenseNumber;
		}

		public void setLicenseNumber(String licenseNumber) {
			this.licenseNumber = licenseNumber;
		}

		public String getIDCardImage() {
			return IDCardImage;
		}

		public void setIDCardImage(String iDCardImage) {
			IDCardImage = iDCardImage;
		}

		public String getIDCardNumber() {
			return IDCardNumber;
		}

		public void setIDCardNumber(String iDCardNumber) {
			IDCardNumber = iDCardNumber;
		}

		public String getLicenseStartDate() {
			return licenseStartDate;
		}

		public void setLicenseStartDate(String licenseStartDate) {
			this.licenseStartDate = licenseStartDate;
		}

		public String getLicenseEndDate() {
			return licenseEndDate;
		}

		public void setLicenseEndDate(String licenseEndDate) {
			this.licenseEndDate = licenseEndDate;
		}

		public String getLicenseType() {
			return licenseType;
		}

		public void setLicenseType(String licenseType) {
			this.licenseType = licenseType;
		}

		public String getIntro() {
			return intro;
		}

		public void setIntro(String intro) {
			this.intro = intro;
		}

		public String getIntroImage() {
			return introImage;
		}

		public void setIntroImage(String introImage) {
			this.introImage = introImage;
		}

		public String getEvent() {
			return event;
		}

		public void setEvent(String event) {
			this.event = event;
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

		public String getCreateDate() {
			return createDate;
		}

		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}

	}

}
