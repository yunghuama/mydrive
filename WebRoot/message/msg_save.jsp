<%@ page language="java" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/form.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=path%>/js/core.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<style type="text/css">

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
	width:600px;
	height:400px;
	margin:0px auto;
	margin-top:50px;
}
fieldset {
	padding:20px;
	border : 1px solid #7d7d7d;
}
#barDiv{
	text-align:center;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#barDiv a").click(function(){
		if($("#aTitle").val()==""){
			alert("请输入标题");
			return;
		}
		if($("#aContent").val()==""){
			alert("请输入内容");
			return;
		}
		$("form").submit();
	});
});
</script>
</head>
<body>
		<div id="content">
			<fieldset>
			<legend>意见反馈</legend>
			<div id="sectionContent">
				<form action="<%=path%>/exam/system/saveMessage.d" method="post">
		          <span class="form-required">*</span>标题
		          <s:textfield id="aTitle" name="msg.title" theme="simple" style="width:400px;" cssClass="text"/><br/>
		          <span class="form-required">*</span>内容
		          <s:textarea  id="aContent" name="msg.content" theme="simple" style="height:250px;" cssClass="textarea"/>
				</form>
			</div>
			</fieldset>
	 </div>
	 <div id="barDiv">
			<a id="next" href="javascript:void(0);"><img alt="" src="<%=path%>/image/save.png"> </a>
	</div>
</body>
</html>