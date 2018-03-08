<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Thêm sinh viên</title>
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

	<div class="container">
		<h2><center>Thêm sinh viên</center></h2>
		<form class="form-horizontal" action='add' method='post'>
			<div class="form-group">
				<label class="control-label col-sm-2" for="mssv">MSSV:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="mssv"
						placeholder="Mã số sinh viên" name="mssv" >
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="name">Họ và tên:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name"
						placeholder="Họ và tên" name="name" >
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="name">Mật khẩu:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="password"
						placeholder="Mật khẩu" name="password" >
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Email:</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="email"
						placeholder="Email" name="email" >
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="phone">Số điện
					thoại:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="sdt"
						placeholder="Số điện thoại" name="sdt" >
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Thêm</button>
					<a class="nav-link btn btn-danger" href="/DanhSach/">Hủy</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>