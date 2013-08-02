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
	width:600px;
	height:450px;
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
	width:300px;
}
.select {
	width:300px;
}
	.hasDel {
		text-decoration:line-through;
	}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#next").click(function(){
	    $("form").submit();
	});

});

</script>
</head>
<body style="overflow:auto">
	 		<div id="infoContent">
			<fieldset>
			<legend>添加驾校</legend>
			<div id="sectionContent">
				<form action="<%=path%>/exam/system/saveSchool.d" method="post">
				<s:hidden name="schoolVo.id"/>
				<table class="form-table" cellspacing="0" cellpadding="0">
		        <tr>
		          <td class="form-left">登录名</td>
		          <td class="form-right"><s:textfield name="schoolVo.number" theme="simple" cssClass="text"/> </td>
		        </tr>
                <tr>
                    <td class="form-left">密码</td>
                    <td class="form-right"><s:textfield name="schoolVo.password" theme="simple" cssClass="text"/></td>
                </tr>
		        <tr>
		          <td class="form-left">名称</td>
		          <td class="form-right"><s:textfield name="schoolVo.name" theme="simple" cssClass="text"/></td>
		        </tr>
		        <tr>
		          <td class="form-left">电话</td>
		          <td class="form-right"><s:textfield name="schoolVo.tel" theme="simple" cssClass="text"/></td>
		        </tr>
		        <tr>
		          <td class="form-left">地址</td>
		          <td class="form-right"><s:textfield name="schoolVo.address" theme="simple" cssClass="text"/></td>
		        </tr>
		        <tr>
		          <td class="form-left">题库</td>
		          <td class="form-right">  <s:select list="questionTableList" listKey="name" listValue="title" name="schoolVo.questionType" id="code" cssClass="select">

                  </s:select></td>
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