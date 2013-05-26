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
#infoContent{
	width:400px;
	height:250px;
	margin:0px auto;
	margin-top:20px;
}
fieldset {
	padding:20px;
	border : 1px solid #7d7d7d;
}
#barDiv{
	text-align:center;
}
.text {
	width:200px;
}
.select {
	width:200px;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#next").click(function(){
		if($("input[name='adver.name']").val()==""){
			alert("广告名称不能为空");
			return;
		}
		if($("input[name='upload']").val()==""){
			alert("请选择图片");
			return;
		}
		if(confirm("确认添加该广告吗?")){
			$("#infoForm").submit();
		}
	});
});

</script>
</head>
<body style="overflow:auto">
	 <div id="infoContent">
			<fieldset>
			<legend>添加</legend>
			<div id="sectionContent">
				<form  name="infoForm" id="infoForm" action="<%=path%>/system/adver/save.d" method="post" enctype="multipart/form-data">
				<table class="form-table" cellspacing="0" cellpadding="0">
		        <tr>
		          <td class="form-left"><span class="form-required">*</span>名称</td>
		          <td class="form-right"><s:textfield name="adver.name" theme="simple" cssClass="text"/> </td>
		        </tr>
		        <tr>
		          <td class="form-left">链接</td>
		          <td class="form-right"><s:textfield name="adver.url" theme="simple" cssClass="text"/></td>
		        </tr>
		        <tr>
		          <td class="form-left">页面</td>
		          <td class="form-right">
		         		 <select name="adver.page" class="select">
								<option value="0">登录页面</option>
								<option value="1">工作台页面</option>
						</select>
		          </td>
		        </tr>
		         <tr>
		          <td class="form-left">位置</td>
		          <td class="form-right">
		          <select name="adver.position" class="select">
								<option value="0">左边</option>
								<option value="1">右边</option>
						</select>
		          </td>
		        </tr>
		        <tr>
		          <td class="form-left"><span class="form-required">*</span>广告图片</td>
		          <td class="form-right"><input type="file" name="upload" class="file"></td>
		        </tr>
		        <tr>
		          <td class="form-left">轮换时间</td>
		          <td class="form-right"><s:textfield name="adver.time" theme="simple" cssClass="text"/></td>
		        </tr>
     			 </table>
				</form>
			</div>
			</fieldset>
	 </div>
	 <div id="barDiv">
			<a id="next" href="javascript:void(0);"><img alt="" src="<%=path%>/image/save.png"> </a>
	</div>
</body>
</html>