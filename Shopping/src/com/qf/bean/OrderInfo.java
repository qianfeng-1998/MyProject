package com.qf.bean;

import java.sql.Timestamp;

public class OrderInfo {
	private int orderId;
	private int customerId;
	private int status;
	
	
	private int goodsId;
	private int quantity;
	private Timestamp orderTime;
	


	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public OrderInfo(int orderId, int customerId, int status, int goodsId, int quantity, Timestamp orderTime) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.status = status;
		this.goodsId = goodsId;
		this.quantity = quantity;
		this.orderTime = orderTime;
	}

	public OrderInfo() {
		
	}

	public OrderInfo(int orderId, int customerId, int status) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.status = status;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
