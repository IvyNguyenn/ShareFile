<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Sửa thông tin sinh viên</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<%@page import="com.vy.dao.SinhVienDao,com.vy.bean.SinhVien"%>
	<%
		String id = request.getParameter("ID");
		SinhVien sv = SinhVienDao.getRecordById(Integer.parseInt(id));
	%>
	<div class='container'>
		<h2><center>Sửa thông tin sinh viên</center></h2>
		<form class='form-horizontal' action='update' method='post'>
			<input type="hidden" name="ID" id="ID" value="<%=sv.getID() %>"/>
			<div class="form-group">
				<label class="control-label col-sm-2" for="mssv">MSSV:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="mssv"
						placeholder="Mã số sinh viên" name="mssv" value="<%=sv.getMssv() %>">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="name">Họ và tên:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name"
						placeholder="Họ và tên" name="name" value="<%=sv.getName() %>">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="name">Mật khẩu:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="password"
						placeholder="Mật khẩu" name="password" value="">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Email:</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="email"
						placeholder="Email" name="email" value="<%=sv.getEmail() %>">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="phone">Số điện
					thoại:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="sdt"
						placeholder="Số điện thoại" name="sdt" value="<%=sv.getSdt() %>">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Sửa</button>
					<a class="nav-link btn btn-danger" href="index.jsp">Hủy</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>