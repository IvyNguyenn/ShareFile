package com.vy.bean;

public class MonHoc {
	private int ID;
	private String maMon;
	private String name;
	private String giaoVien;
	private String phongHoc;
	public MonHoc(int id, String maMon, String name, String giaoVien, String phongHoc) {
		super();
		this.ID=id;
		this.maMon=maMon;
		this.name=name;
		this.giaoVien=giaoVien;
		this.phongHoc = phongHoc;
	}
	public MonHoc() {
	
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getMaMon() {
		return maMon;
	}
	public void setMaMon(String maMon) {
		this.maMon = maMon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGiaoVien() {
		return giaoVien;
	}
	public void setGiaoVien(String giaoVien) {
		this.giaoVien = giaoVien;
	}
	public String getPhongHoc() {
		return phongHoc;
	}
	public void setPhongHoc(String phongHoc) {
		this.phongHoc = phongHoc;
	}
	

}
