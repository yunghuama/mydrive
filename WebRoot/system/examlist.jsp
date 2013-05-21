<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/workspace.css" rel="stylesheet" type="text/css"/>
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
<title></title>
</head>
<body>
		<div id="main">
		
			<div id="ads">
			<div class="title"></div>
			<div class="contents"></div>
			</div>
			
			<div id="statistic_sub1">
			<div class="title"></div>
			<div class="contents"></div>
			</div>
			<div id="score_sub1">
			<div class="title"></div>
			<div class="contents"></div>
			</div>
			
			<div id="statistic_sub2">
			<div class="title"></div>
			<div class="contents"></div>
			</div>
			
			<div id="score_sub2">
			<div class="title"></div>
			<div class="contents"></div>
			</div>
		</div>
</body>
</html>