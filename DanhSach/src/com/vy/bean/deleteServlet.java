package com.vy.bean;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.vy.dao.SinhVienDao;

@WebServlet("/delete")
public class deleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public deleteServlet() {
        super();
    }


	public void init(ServletConfig config) throws ServletException {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("ID");
		SinhVien sv = SinhVienDao.getRecordById(Integer.parseInt(id));
		SinhVienDao.delete(sv);
		response.sendRedirect("/DanhSach/home");
	}

}
