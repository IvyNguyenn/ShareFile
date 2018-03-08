package com.vy.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.vy.bean.MD5Password;
import com.vy.bean.SinhVien;
import com.vy.bean.MonHoc;

public class MonHocDao {
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

	public static List<MonHoc> getAllRecords() {
		List<MonHoc> list = new ArrayList<MonHoc>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from monhoc");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MonHoc mh = new MonHoc();
				mh.setID(rs.getInt("ID"));
				mh.setMaMon(rs.getString("maMon"));
				mh.setName(rs.getString("name"));
				mh.setGiaoVien(rs.getString("giaoVien"));
				mh.setPhongHoc(rs.getString("phongHoc"));
				list.add(mh);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public static MonHoc getRecordById(int id) {
		MonHoc mh = null;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from monhoc where ID=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			mh = new MonHoc();
			while (rs.next()) {
				mh.setID(rs.getInt("ID"));
				mh.setMaMon(rs.getString("maMon"));
				mh.setMaMon(rs.getString("name"));
				mh.setMaMon(rs.getString("giaoVien"));
				mh.setMaMon(rs.getString("phongHoc"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return mh;
	}

	public static MonHoc getRecordByMaMon(String mm) {
		MonHoc mh = null;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from monhoc where maMon=?");
			ps.setString(1, "maMon");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				mh = new MonHoc();
				mh.setID(rs.getInt("ID"));
				mh.setMaMon(rs.getString("maMon"));
				mh.setMaMon(rs.getString("name"));
				mh.setMaMon(rs.getString("giaoVien"));
				mh.setMaMon(rs.getString("phongHoc"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return mh;
	}

	public static List<MonHoc> getAllMonHocByIdSinhVien(int id) {
		List<MonHoc> list = new ArrayList<MonHoc>();
		try {
			Connection con = getConnection();
			String query = "SELECT * FROM monhoc WHERE ID IN (SELECT sinhvien_monhoc.IDmonhoc FROM dbo.sinhvien_monhoc WHERE IDsinhvien=?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MonHoc mh = new MonHoc();
				mh.setID(rs.getInt("ID"));
				mh.setMaMon(rs.getString("maMon"));
				mh.setName(rs.getString("name"));
				mh.setGiaoVien(rs.getString("giaoVien"));
				mh.setPhongHoc(rs.getString("phongHoc"));
				list.add(mh);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
}
