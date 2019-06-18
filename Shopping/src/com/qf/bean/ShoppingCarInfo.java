package com.qf.bean;

public class ShoppingCarInfo {
	
	private int goodsId;
	private int quantity;
	private int userId;
	
	public ShoppingCarInfo() {
		
	}

	public ShoppingCarInfo(int goodsId, int quantity, int userId) {
		this.goodsId = goodsId;
		this.quantity = quantity;
		this.userId = userId;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
