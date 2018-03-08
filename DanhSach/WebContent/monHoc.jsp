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
	<%@page import="com.vy.dao.SinhVienDao,com.vy.bean.*,java.util.*"%>
	<div class="container">
		<h2>
			<center>Danh sách môn học</center>
		</h2>
		<br>
		<form action='dkmh' method='post'>
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th><center>Mã môn</center></th>
						<th><center>Tên môn</center></th>
						<th><center>Tên giáo viên</center></th>
						<th><center>Phòng học</center></th>
						<th><center>Đăng kí</center></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listMonHoc}" var="mh">
						<tr>
							<td><center>${mh.maMon }</center></td>
							<td><center>${mh.name}</center></td>
							<td><center>${mh.giaoVien }</center></td>
							<td><center>${mh.phongHoc }</center></td>
							<td><center>
									<input type="checkbox" name="listMonDK" value="${mh.ID}">
								</center></td>
						</tr>
					</c:forEach>


				</tbody>
			</table>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-20" style="float: right;">
					<input type="hidden" name="listMonDK" value="0">
					<input type="hidden" name="ID" id="ID" value="${sv.ID }" />
					<button type="submit" class="btn btn-primary">Xác nhận ĐK</button>
					<a class="nav-link btn btn-danger" href="/DanhSach/home">Hủy</a>
				</div>
			</div><br>
			<h3><center>Môn học đã đăng kí</center></h3>
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th><center>Mã môn</center></th>
						<th><center>Tên môn</center></th>
						<th><center>Tên giáo viên</center></th>
						<th><center>Phòng học</center></th>
						<th><center>Xóa Đăng kí</center></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sv.listMonHoc}" var="mh">
						<tr>
							<td><center>${mh.maMon }</center></td>
							<td><center>${mh.name}</center></td>
							<td><center>${mh.giaoVien }</center></td>
							<td><center>${mh.phongHoc }</center></td>
							<td><center>
									<input type="checkbox" name="listMonXoa" value="${mh.ID}">
								</center></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-20" style="float: right;">
					<input type="hidden" name="listMonXoa" value="0">
					<button type="submit" class="btn btn-primary">Xác nhận Xóa</button>
					<a class="nav-link btn btn-danger" href="/DanhSach/home">Hủy</a>
				</div>
			</div><br>
		</form>
	</div>
</body>
</html>