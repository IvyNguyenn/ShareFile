<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.vy.dao.SinhVienDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My Contact</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<%@page import="com.vy.dao.SinhVienDao,com.vy.bean.*,java.util.*"%>
	<div class="container">
		<h2>Liên hệ</h2>
		<%
			List<SinhVien> list = SinhVienDao.getAllRecords();
			request.setAttribute("list", list);
		%>
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>MSSV</th>
					<th>Họ và tên</th>
					<th>Email</th>
					<th>Số điện thoại</th>
					<th><center>Sửa</center></th>
					<th><center>Xóa</center></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="sv">
					<tr>
						<td>${sv.getMssv() }</td>
						<td>${sv.getName()}</td>
						<td>${sv.getEmail() }</td>
						<td>${sv.getSdt() }</td>
						<td><center>
								<a href="updateFrom.jsp?ID=${sv.getID()}"><span class="glyphicon glyphicon-edit"></span></a>
							</center></td>
						<td><center>
								<a href="delete?ID=${sv.getID()}"><span class="glyphicon glyphicon-remove"
									style='color: red;'></span></a>
							</center></td>
					</tr>
				</c:forEach>


			</tbody>
		</table>
		<a class="nav-link btn btn-primary btn-lg" href="addFrom.jsp">Thêm sinh viên</a>
	</div>
</body>
</html>