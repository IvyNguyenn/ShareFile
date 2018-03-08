package com.vy.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.vy.bean.MD5Password;
import com.vy.bean.SinhVien;

public class SinhVienDao {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=123456;database=SINHVIEN;");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public static int add(SinhVien sv) {
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con
					.prepareStatement("insert into sinhvien(mssv,name,password,email,sdt) values(?,?,?,?,?)");
			ps.setString(1, sv.getMssv());
			ps.setString(2, sv.getName());
			ps.setString(3, MD5Password.MD5(sv.getPassword()));
			ps.setString(4, sv.getEmail());
			ps.setString(5, sv.getSdt());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static int update(SinhVien sv) {
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con
					.prepareStatement("update sinhvien set mssv=?,name=?,password=?,email=?,sdt=? where ID=?;");
			ps.setString(1, sv.getMssv());
			ps.setString(2, sv.getName());
			ps.setString(3, MD5Password.MD5(sv.getPassword()));
			ps.setString(4, sv.getEmail());
			ps.setString(5, sv.getSdt());
			ps.setInt(6, sv.getID());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static int delete(SinhVien sv) {
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("delete from sinhvien where ID=?;");
			ps.setInt(1, sv.getID());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public static List<SinhVien> getAllRecords() {
		List<SinhVien> list = new ArrayList<SinhVien>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from sinhvien");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SinhVien sv = new SinhVien();
				sv.setID(rs.getInt("ID"));
				sv.setMssv(rs.getString("mssv"));
				sv.setName(rs.getString("name"));
				sv.setPassword(rs.getString("password"));
				sv.setEmail(rs.getString("email"));
				sv.setSdt(rs.getString("sdt"));
				list.add(sv);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public static SinhVien getRecordById(int id) {
		SinhVien sv = null;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from sinhvien where ID=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			sv = new SinhVien();
			while (rs.next()) {

				sv.setID(rs.getInt("id"));
				sv.setMssv(rs.getString("mssv"));
				sv.setName(rs.getString("name"));
				sv.setPassword(rs.getString("password"));
				sv.setEmail(rs.getString("email"));
				sv.setSdt(rs.getString("sdt"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return sv;
	}
	public static int dkmh(int idsv,int idmh) {
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO sinhvien_monhoc(IDsinhvien,IDmonhoc) VALUES  (?,?)");
			ps.setInt(1, idsv);
			ps.setInt(2, idmh);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	public static int xoadkmh(int idsv,int idmh) {
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM sinhvien_monhoc WHERE IDsinhvien=? AND IDmonhoc=?");
			ps.setInt(1, idsv);
			ps.setInt(2, idmh);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	public static SinhVien getRecordByMssv(String mssv) {
		SinhVien sv = null;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from sinhvien where mssv=?");
			ps.setString(2, "mssv");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sv = new SinhVien();
				sv.setMssv(rs.getString("mssv"));
				sv.setName(rs.getString("name"));
				sv.setPassword(rs.getString("password"));
				sv.setEmail(rs.getString("email"));
				sv.setSdt(rs.getString("sdt"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return sv;
	}
}
