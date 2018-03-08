package com.vy.bean;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vy.dao.SinhVienDao;

@WebServlet("/add")
public class addServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public addServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("addFrom.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String mssv = request.getParameter("mssv");
		String name = request.getParameter("name");
		String pass = request.getParameter("password");
		String email = request.getParameter("email");
		String sdt = request.getParameter("sdt");
		if (mssv != null && name != null && pass != null && email != null && sdt != null && mssv != "" && name != ""
				&& pass != "" && email != "" && sdt != "") {
			SinhVien sv = new SinhVien();
			sv.setMssv(mssv);
			sv.setName(name);
			sv.setPassword(pass);
			sv.setEmail(email);
			sv.setSdt(sdt);
			sv.setListMonHoc(null);
			int i = SinhVienDao.add(sv);
			
			if (i > 0) {
				response.sendRedirect("/DanhSach/home");
			}
			else
				response.sendRedirect("/DanhSach/add");
		} else
			response.sendRedirect("/DanhSach/add");
	}
}
