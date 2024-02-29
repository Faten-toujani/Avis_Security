package com.example.demo.web.model;

public class AvisModel {
	
	private String message;
	private String statu;
	public AvisModel(String message, String statu) {
		super();
		this.message = message;
		this.statu = statu;
	}
	public AvisModel() {
		super();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	
	

}
