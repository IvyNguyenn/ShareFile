package com.vy.bean;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vy.dao.MonHocDao;
import com.vy.dao.SinhVienDao;
import com.vy.dao.sinhvien_monhoc;

@WebServlet("/dkmh")
public class dkmhServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public dkmhServlet() {
        super();
   
    }

	public void init(ServletConfig config) throws ServletException {
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("ID"));
		SinhVien sv = SinhVienDao.getRecordById(id);
		request.setAttribute("sv", sv);
		List<MonHoc> listmh = MonHocDao.getAllRecords();
		request.setAttribute("listMonHoc", listmh);
		List<MonHoc> listMonHoc = MonHocDao.getAllMonHocByIdSinhVien(sv.getID());
		sv.setListMonHoc(listMonHoc);
		request.getRequestDispatcher("monHoc.jsp").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] listMonDK = request.getParameterValues("listMonDK");
		String[] listMonXoa = request.getParameterValues("listMonXoa");
		//doGet(request, response);
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("ID"));
		int i;
		for(String mh :listMonDK) {
			if(mh != "0")
				i = SinhVienDao.dkmh(id, Integer.parseInt(mh));
		}
		for(String mh :listMonXoa) {
			if(mh != "0")
				i = SinhVienDao.xoadkmh(id, Integer.parseInt(mh));
		}
		response.sendRedirect("home");
	}

}
