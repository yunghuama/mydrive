<%@ page language="java" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/section.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=path%>/js/core.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<style type="text/css">
	#main {
		width:800px;
		margin:0px auto;
		margin-top:20px;
	}
	#title {
		text-align:center;
	}
	
	#title span {
	font-size:18px;
	}
	
	#timediv {
		text-align:right;
		margin-bottom:5px;
		margin-top:5px;
	}
	#contents {
		text-align:left;
	}
	
	#contents span {
		font-size:16px;
		line-height:28px;
	}
	
	span {
	white-space: normal;
	word-wrap: break-word;
    word-break: break-all;
	}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("table tr").bind("mouseover",function(){
		$(this).addClass("mouseover");
	}).bind("mouseout",function(){
		$(this).removeClass("mouseover");
	});
});
</script>
</head>
<body style="overflow:auto">
	 <div id="main">
			<div id="title"><span>驾校公告</span></div>
			<div id="sectionContent">
			<div id="title"><span><s:property value="announcement.title"/></span></div>
			<div id="timediv"><span>发布时间:&nbsp;&nbsp;&nbsp;<s:property value="announcement.createTime"/></span></div>
			<div id="contents">
			<span><s:property value="announcement.content"/></span>
			</div>
			</div>
	 </div>
	 
</body>
</html>