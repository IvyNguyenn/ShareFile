<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.vy.dao.SinhVienDao"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Danh Sách Sinh Viên</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<%@page import="com.vy.dao.SinhVienDao,com.vy.dao.MonHocDao,com.vy.bean.*,java.util.*"%>
	<div class="container">
		<h2>Liên hệ</h2>
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th><center>MSSV</center></th>
					<th><center>Họ và tên</center></th>
					<th><center>Email</center></th>
					<th><center>Số điện thoại</center></th>
					<th><center>Sửa</center></th>
					<th><center>Xóa</center></th>
					<th><center>ĐKMH</center></th>
					<th><center>Môn đã ĐK</center></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listSinhVien}" var="sv">
					<tr>
						<td><center>${sv.mssv }</center></td>
						<td><center>${sv.name}</center></td>
						<td><center>${sv.email }</center></td>
						<td><center>${sv.sdt }</center></td>
						<td><center>
								<a href="update?ID=${sv.ID}"><span
									class="glyphicon glyphicon-edit"></span></a>
							</center></td>
						<td><center>
								<a href="delete?ID=${sv.ID}"><span
									class="glyphicon glyphicon-remove" style='color: red;'></span></a>
							</center></td>
						<td><center>
								<a href="dkmh?ID=${sv.ID }"><span
									class="glyphicon glyphicon-education" style='color: green;'></span></a>
							</center></td>
						<td><center>
								<div class="dropdown">
									<a href="" class="btn btn-primary btn-xs dropdown-toggle" 
									type="button" data-toggle="dropdown">Môn học <span
										class="caret"></span></a>
									<ul class="dropdown-menu">
										<c:forEach items="${sv.listMonHoc}" var="mh">
											<li><a href="">${mh.name}</a></li>
										</c:forEach>
									</ul>
								</div>
							</center></td>
					</tr>
				</c:forEach>


			</tbody>
		</table>
		<a class="nav-link btn btn-primary btn-lg" href="add">Thêm sinh
			viên</a>
	</div>
</body>
</html>