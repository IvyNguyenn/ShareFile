package com.vy.bean;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vy.dao.SinhVienDao;


@WebServlet("/update")
public class updateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public updateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doPost(request, response);
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("ID"));
		SinhVien sv = SinhVienDao.getRecordById(id);
		request.setAttribute("sv", sv);
		request.getRequestDispatcher("updateFrom.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("Post");
		int id = Integer.parseInt(request.getParameter("ID"));
		String mssv = request.getParameter("mssv");
		String name = request.getParameter("name");
		String pass = request.getParameter("password");
		String email = request.getParameter("email");
		String sdt = request.getParameter("sdt");
		if(mssv!=null && name!=null && pass!=null && email!=null && sdt!=null
				&& mssv!=""&& name!=""&& pass!="" && email!="" && sdt!="") {
			SinhVien sv= new SinhVien();
			sv.setID(id);
			sv.setMssv(mssv);
			sv.setName(name);
			sv.setPassword(pass);
			sv.setEmail(email);
			sv.setSdt(sdt);
			int i= SinhVienDao.update(sv);
			if(i>0)
				response.sendRedirect("/DanhSach/home");
			else response.sendRedirect("updateFrom.jsp");
		}
		else 
			response.sendRedirect("updateFrom.jsp");
	}

}
