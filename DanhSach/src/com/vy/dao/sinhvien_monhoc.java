package com.vy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class sinhvien_monhoc {
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
	public static int dkmh(int idmh,int idsv) {
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO sinhvien_monhoc(IDsinhvien,IDmonhoc) VALUES  (?,?)");
			ps.setInt(1, idmh);
			ps.setInt(2, idsv);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
}
