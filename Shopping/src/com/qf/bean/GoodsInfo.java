package com.qf.bean;

import java.math.BigDecimal;

public class GoodsInfo {
	private int goodsId;
	private int typeId;
	private String goodsName;
	private BigDecimal price;
	private double discount;
	private int isNew=1;
	private int isRecommend=1;
	private int status=1;
	private String photo;
	private String remark;
	
	public GoodsInfo() {
		
	}

	public GoodsInfo(int goodsId, int typeId, String goodsName, BigDecimal price, double discount, int isNew,
			int isRecommend, int status, String photo, String remark) {
		this.goodsId = goodsId;
		this.typeId = typeId;
		this.goodsName = goodsName;
		this.price = price;
		this.discount = discount;
		this.isNew = isNew;
		this.isRecommend = isRecommend;
		this.status = status;
		this.photo = photo;
		this.remark = remark;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getIsNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public int getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
