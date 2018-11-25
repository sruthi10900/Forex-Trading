package com.forex.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="currency")
@Table(name="currency")
public class CurrencyPair {
	@Id
	@Column(name="currency_pair")
	String currency_pair;
	
	@Column(name="bid")
	double bid;
	
	@Column(name="ask_price")
	double ask_price;
	
	public CurrencyPair() {}

	public String getCurrency_pair() {
		return currency_pair;
	}

	public void setCurrency_pair(String currency_pair) {
		this.currency_pair = currency_pair;
	}

	public double getBid() {
		return bid;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	public double getAsk_price() {
		return ask_price;
	}

	public void setAsk_price(double ask_price) {
		this.ask_price = ask_price;
	}
	
	
	
}
