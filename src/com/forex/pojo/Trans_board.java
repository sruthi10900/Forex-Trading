package com.forex.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="trans_board")
@Table(name="trans_board")
public class Trans_board {

	@Id
	@Column(name="tid")
	int tid;
	
	@Column(name="currency_pair")
	String currency_pair;
	
	@Column(name="volume")
	int volume;
	
	@Column(name="commission")
	double commission;
	
	@Column(name="trans_date")
	String trans_date;
	
	public Trans_board() {}
	
	public Trans_board(int tid,String currency_pair,int volume,double commission2,String trans_date) {
		this.tid=tid;
		this.currency_pair=currency_pair;
		this.volume=volume;
		this.commission=commission2;
		this.trans_date=trans_date;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getCurrency_pair() {
		return currency_pair;
	}

	public void setCurrency_pair(String currency_pair) {
		this.currency_pair = currency_pair;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public String getTrans_date() {
		return trans_date;
	}

	public void setTrans_date(String trans_date) {
		this.trans_date = trans_date;
	}
	
	
	
}
