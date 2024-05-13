package com.device.qa.model;
import java.io.Serializable;
import java.time.Instant;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
@Entity
@Table(name = "booking_log")
public class Booking implements Serializable {
    private static final long serialVersionUID = 432154291451321L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "userid")
    private Long userid;

    @Column(name = "mobileid")
    private Long mobileid;
    
    @Nonnull
    @Column(name = "booking_date")
    private Instant bookingDate;

	public Booking() {

	}
	
	public Booking( Long userid, Long mobileid, Instant bookingDate) {
		this.userid = userid;
		this.mobileid = mobileid;
		this.bookingDate = bookingDate;
	}
   

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getMobileid() {
		return mobileid;
	}

	public void setMobileid(Long mobileid) {
		this.mobileid = mobileid;
	}

	public Instant getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Instant bookingDate) {
		this.bookingDate = bookingDate;
	}
   
}
