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
	<div id="content">
	<fieldset>
	<legend>个人资料</legend>
	<form name="infoForm" id="infoForm" action="<%=path%>/exam/system/updateUsers.d" class="form" method="post">
	<s:hidden name="users.id"></s:hidden>
      <table class="form-table" cellspacing="0" cellpadding="0">
        <tr>
          <td class="form-left"><span class="form-required">*</span>真实姓名</td>
          <td class="form-right"><s:textfield name="users.name" theme="simple"/> </td>
        </tr>
        <tr>
          <td class="form-left"><span class="form-required">*</span>身份证</td>
          <td class="form-right"><s:textfield name="users.identity" theme="simple"/></td>
        </tr>
        <tr>
          <td class="form-left">年龄</td>
          <td class="form-right"><s:textfield name="users.age" theme="simple"/></td>
        </tr>
        <tr>
          <td class="form-left"></span>性别</td>
          <td class="form-right">
          		<s:select list="#{'0':'男','1':'女'}" name="users.sex"></s:select>
          </td>
        </tr>
        <tr>
          <td class="form-left"><span class="form-required">*</span>车型</td>
          <td class="form-right">
          <s:select name="users.cartype" list="#{'c1':'C1','c2':'C2','c3':'C3','c4':'C4','c5':'C5','a1':'A1','a2':'A2','a3':'A3','b1':'B1','b2':'B2','d':'D','e':'E','f':'F'}"></s:select>
           </td>
        </tr>
        <tr>
          <td class="form-left">手机号</td>
          <td class="form-right"><s:textfield name="users.phonenumber" theme="simple"/></td>
        </tr>
      </table>
    </form>
    </fieldset>
	</div>
	<div id="barDiv">
			<button id="next">下一步</button>
	</div>
</body>
</html>