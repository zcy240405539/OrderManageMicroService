package com.framework.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orders_item")
public class ItemsEntity {
	@Id
	@Column(name="itemid")	
	String itemid;
	@Column(name="orderid")	
	String orderid;
	@Column(name="productid")	
	String productid;
	@Column(name="quantity")	
	String quantity; 

	public ItemsEntity(){}
	
	public ItemsEntity(String itemid, String orderid, String productid, String quantity){
		this.itemid = itemid;
		this.orderid = orderid;
		this.productid = productid;
		this.quantity = quantity;
	}
	
	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
}
