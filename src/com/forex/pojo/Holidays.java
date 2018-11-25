package com.forex.pojo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="holidays")
@Table(name="holidays")
public class Holidays {
	@Id
	@Column(name="hid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int hid;
	
	@Column(name="currency")
	String currency;
	
	@Column(name="holiday_date")
	Date holiday_date;
	
	@Column(name="holiday_name")
	String holiday_name;

	public Integer gethid() {
		return hid;
	}

	public void sethid(Integer hid) {
		this.hid = hid;
	}

	public String getHoliday_name() {
		return holiday_name;
	}

	public void setHoliday_name(String holiday_name) {
		this.holiday_name = holiday_name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getHoliday_date() {
		return holiday_date;
	}

	public void setHoliday_date(Date holiday_date) {
		this.holiday_date = holiday_date;
	}
	
	public Holidays(){}
	
	
}