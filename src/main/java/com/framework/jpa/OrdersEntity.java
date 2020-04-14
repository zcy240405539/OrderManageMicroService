package com.framework.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class OrdersEntity {
	@Id
	@Column(name="orderid")	
	String orderid;
	@Column(name="userid")	
	String userid;
	@Column(name="total_price")	
	String totalPrice; 
	@Column(name="order_time")	
	String orderTime;
	@Column(name="ip")	
	String ip;
	
	public OrdersEntity(){}
	
	public OrdersEntity(String orderid){
		this.orderid = orderid;
	}

	public OrdersEntity(String orderid, String userid, String totalPrice, String orderTime, String ip){
		this.orderid = orderid;
		this.userid = userid;
		this.totalPrice = totalPrice;
		this.orderTime = orderTime;
		this.ip = ip;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
