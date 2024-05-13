package com.device.qa.model;

public class PhoneReturnRequest {
	String mobileName;
	Long userId;
	public String getMobileName() {
		return mobileName;
	}
	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public PhoneReturnRequest() {
		
	}
	public PhoneReturnRequest(String mobileName, Long userId) {
		this.mobileName = mobileName;
		this.userId = userId;
	}
}
