<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/form.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#next").click(function(){
			if($("#name").val()==""){
				alert("请填写姓名");
				return;
			}
			if($("#identity").val()==""){
				alert("请填写姓名");
				return;
			}
			$("#infoForm").submit();
		});
	});

</script>
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
#content{
	width:300px;
	height:300px;
	margin:0px auto;
	margin-top:100px;
}
fieldset {
	padding:20px;
}
#barDiv{
	text-align:center;
}
</style>
</head>
<body>
		考试信息列表
</body>
</html>