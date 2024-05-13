package com.device.qa.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FonoapiResponse {
	private List<Data> data;

	public List<Data> getData() {
		return data;
	}

	public FonoapiResponse(List<Data> data) {
		super();
		this.data = data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public FonoapiResponse() {

	}

}
@JsonIgnoreProperties(ignoreUnknown = true)
class Data {
	private String userId;
	private String userName;
	private String userDob;
	public Data() {
		
	}
	public Data(String userId, String userName, String userDob) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userDob = userDob;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserDob() {
		return userDob;
	}
	public void setUserDob(String userDob) {
		this.userDob = userDob;
	}
	

}