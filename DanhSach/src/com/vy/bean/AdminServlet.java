package com.vy.bean;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vy.dao.SinhVienDao;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//doPost(request, response);
		
		PrintWriter out = response.getWriter();
		out.println("Get");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Cookie ck[] = request.getCookies();
		if (ck != null) {
			for(int i=0;i<ck.length;i++){  
				String mssv = ck[i].getName();
				String pass= ck[i].getValue();
				SinhVien sv =SinhVienDao.getRecordByMssv(mssv);
				if (sv!=null && pass.equals(sv.getPassword()) ) {
					out.println("Login again successfully! Welcome "+ sv.getName());
				}
				else {
					response.sendRedirect("login.html");
				}
			}  
		}
		else {
			response.sendRedirect("login.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("Post");
		String mssv = request.getParameter("mssv");
		String pass = MD5Password.MD5(request.getParameter("password"));
		SinhVien sv =SinhVienDao.getRecordByMssv(mssv);
		if (sv!=null && pass.equals(sv.getPassword()) ) {
			out.println("Login successfully! Welcome "+ sv.getName());
			Cookie ck = new Cookie(mssv, pass);
			ck.setMaxAge(3000);
			response.addCookie(ck);

		}
		else {
			response.sendRedirect("login.html");
		}
	}

}
