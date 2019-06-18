package com.qf.bean;

public class OrderGoodsInfo {

	private int orderId;
	private int goodsId;
	private int quantity;
	
	public OrderGoodsInfo() {
		
	}

	public OrderGoodsInfo(int orderId, int goodsId, int quantity) {
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.quantity = quantity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

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
}
