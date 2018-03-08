package com.vy.bean;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vy.dao.MonHocDao;
import com.vy.dao.SinhVienDao;

/**
 * Servlet implementation class homeServlet
 */
@WebServlet("/home")
public class homeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public homeServlet() {
        super();

    }


	public void init(ServletConfig config) throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		List<SinhVien> list = SinhVienDao.getAllRecords();
		request.setAttribute("listSinhVien", list);
		for(SinhVien sv:list) {
			List<MonHoc> listMonHoc = MonHocDao.getAllMonHocByIdSinhVien(sv.getID());
			sv.setListMonHoc(listMonHoc);
		}
		request.getRequestDispatcher("home.jsp").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
