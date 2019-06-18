package com.qf.bean;

import java.sql.Timestamp;

public class CustomerInfo {
	private int id;
	private String email;
	private String pwd;
	private Timestamp registerTime;
	
	//customerDetailInfoµÄBean
	private int customerId;
	private String name;
	private String telphone;
	private String movePhone;
	private String address;
	
	public CustomerInfo() {
		
	}

	public CustomerInfo(int id, String email, String pwd, Timestamp registerTime) {
		this.id = id;
		this.email = email;
		this.pwd = pwd;
		this.registerTime = registerTime;
	}

	
	
	public CustomerInfo(int id, String email, String pwd, Timestamp registerTime, int customerId, String name,
			String telphone, String movePhone, String address) {
		this.id = id;
		this.email = email;
		this.pwd = pwd;
		this.registerTime = registerTime;
		this.customerId = customerId;
		this.name = name;
		this.telphone = telphone;
		this.movePhone = movePhone;
		this.address = address;
	}

	
	public CustomerInfo(int customerId, String name, String telphone, String movePhone, String address) {
		this.customerId = customerId;
		this.name = name;
		this.telphone = telphone;
		this.movePhone = movePhone;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Timestamp getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getMovePhone() {
		return movePhone;
	}

	public void setMovePhone(String movePhone) {
		this.movePhone = movePhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
