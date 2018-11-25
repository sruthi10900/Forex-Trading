package com.forex.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="order_board")
@Table(name="order_board")

public class Order {
	@Id
	@Column(name="orderid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int orderid;
	
	@Column(name="cid") // session.getAttribute
	public int cid;
	
	@Column(name="currency_pair")
	public String currency_pair;
	
	@Column(name="order_date")
	public String order_date;
	
	@Column(name="value_date")
	public String value_date;
	
	@Column(name="notional_value")
	public int notional_value;
	
	@Column(name="rate")
	public double rate;
	
	@Column(name="status")
	public String status;
	
	@Column(name="t_ype")
	public String t_ype;
	
	public Order() {}
	
	

	public Order(int cid,String currency_pair, String order_date, String value_date, int notional_value,
			double rate, String status, String t_ype) {
		super();
		this.cid = cid;
		this.currency_pair = currency_pair;
		this.order_date = order_date;
		this.value_date = value_date;
		this.notional_value = notional_value;
		this.rate = rate;
		this.status = status;
		this.t_ype = t_ype;
	}



	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCurrency_pair() {
		return currency_pair;
	}

	public void setCurrency_pair(String currency_pair) {
		this.currency_pair = currency_pair;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getValue_date() {
		return value_date;
	}

	public void setValue_date(String value_date) {
		this.value_date = value_date;
	}

	public int getNotional_value() {
		return notional_value;
	}

	public void setNotional_value(int notional_value) {
		this.notional_value = notional_value;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getT_ype() {
		return t_ype;
	}

	public void setT_ype(String t_ype) {
		this.t_ype = t_ype;
	}
	
	
}
