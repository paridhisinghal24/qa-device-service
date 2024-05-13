package com.device.qa.model;

import java.util.List;

public class PhoneDetails {
	private Mobile mobile;
	private FonoapiResponse otherDetails;
	private List<Booking> bookingLog;
	public PhoneDetails() {
		
	}

	public PhoneDetails(Mobile mobile, FonoapiResponse otherDetails, List<Booking> bookingLog) {
		super();
		this.mobile = mobile;
		this.otherDetails = otherDetails;
		this.bookingLog = bookingLog;
	}

	public Mobile getMobile() {
		return mobile;
	}
	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
	}

	public FonoapiResponse getOtherDetails() {
		return otherDetails;
	}
	public void setOtherDetails(FonoapiResponse otherDetails) {
		this.otherDetails = otherDetails;
	}
	public List<Booking> getBookingLog() {
		return bookingLog;
	}
	public void setBookingLog(List<Booking> bookingLog) {
		this.bookingLog = bookingLog;
	}
	
}
