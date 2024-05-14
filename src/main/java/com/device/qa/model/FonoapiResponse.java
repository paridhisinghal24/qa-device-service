package com.device.qa.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FonoapiResponse {
	
	
	private String DeviceName;
    private String Brand;
    private String technology;
    private String bands2g;
    private String bands3g;
    private String bands4g;
    private String bands5g;
    public FonoapiResponse() {
    	
    }
	public FonoapiResponse(String deviceName, String brand, String technology, String bands2g, String bands3g,
			String bands4g, String bands5g) {
		DeviceName = deviceName;
		Brand = brand;
		this.technology = technology;
		this.bands2g = bands2g;
		this.bands3g = bands3g;
		this.bands4g = bands4g;
		this.bands5g = bands5g;
	}
	
	public String getDeviceName() {
		return DeviceName;
	}
	public void setDeviceName(String deviceName) {
		DeviceName = deviceName;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	public String getBands2g() {
		return bands2g;
	}
	public void setBands2g(String bands2g) {
		this.bands2g = bands2g;
	}
	public String getBands3g() {
		return bands3g;
	}
	public void setBands3g(String bands3g) {
		this.bands3g = bands3g;
	}
	public String getBands4g() {
		return bands4g;
	}
	public void setBands4g(String bands4g) {
		this.bands4g = bands4g;
	}
	public String getBands5g() {
		return bands5g;
	}
	public void setBands5g(String bands5g) {
		this.bands5g = bands5g;
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