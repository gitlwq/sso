package com.example.sso.bean;

public class Order {

	
	private String oid;
	private String saleid;
	private String address;
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getSaleid() {
		return saleid;
	}
	public void setSaleid(String saleid) {
		this.saleid = saleid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public Order(String oid, String saleid, String address) {
		super();
		this.oid = oid;
		this.saleid = saleid;
		this.address = address;
	}
	
	
	
	
}
