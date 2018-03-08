package com.vy.bean;

public class SinhVien {
	private int ID;
	private String mssv;
	private String name;
	private String password;
	private String email;
	private String sdt;
	
	public SinhVien(int id, String mssv, String name, String email, String sdt) {
		super();
		this.ID=id;
		this.mssv=mssv;
		this.name=name;
		this.email=email;
		this.sdt=sdt;
	}
	public SinhVien() {
	
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getMssv() {
		return mssv;
	}
	public void setMssv(String mssv) {
		this.mssv = mssv;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

