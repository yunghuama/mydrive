<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
<title>首次登录，完善个人信息</title>
<style type="text/css">
#head {
	position:relative;
	width:100%;
	height:50px;
	background-color:#2d89ef;
	margin:0px auto;
	text-align:center;
	padding-top:15px;
}

span {
	font-size:20px;
	color:#FFFFFF;
	height:20px;
	line-height:20px;
}

form span {
color:#000000;
}
</style>
</head>
<body>
		<div id="head">
				<span>首次登录，请完善一下个人信息</span>
		</div>
		
		
		<div id="content">
			<form action="<%=path%>/system/firstInit.d">
			<span>真实姓名:</span><input type="text" name="users.name"><br/>
			<span>身份证:</span><input type="text" name="users.identity"><br/>
			<span>年龄:</span><input type="text" name="users.age"><br/>
			<span>性别:</span><input type="text" name="users.sex"><br/>
			<span>车型:</span><select name="users.cartype">
								<option value="c1">C1</option>
								<option value="c2">C2</option>
								<option value="c3">C3</option>
								<option value="c4">C4</option>
								<option value="c5">C5</option>
								<option value="a1">A1</option>
								<option value="a2">A2</option>
								<option value="a3">A3</option>
								<option value="b1">B1</option>
								<option value="b2">B2</option>
								<option value="d">D</option>
								<option value="e">E</option>
								<option value="f">F</option>
							  </select> <br/>
			<span>手机号:</span><input type="text" name="users.name"><br/>
			</form>
		
		</div>
</body>
</html>