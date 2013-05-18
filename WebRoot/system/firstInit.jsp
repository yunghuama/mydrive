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
		<div id="head">
				<span>首次登录，请完善以下个人信息</span>
		</div>
		
		
	<div id="content">
	<fieldset>
	<legend>个人资料</legend>
	<form name="infoForm" id="infoForm" action="<%=path%>/system/users/firstInit.d" class="form" method="post">
	<s:hidden name="users.id"></s:hidden>
      <table class="form-table" cellspacing="0" cellpadding="0">
        <tr>
          <td class="form-left"><span class="form-required">*</span>真实姓名</td>
          <td class="form-right"><input type="text" id="name" name="users.name" class="text"/></td>
        </tr>
        <tr>
          <td class="form-left"><span class="form-required">*</span>身份证</td>
          <td class="form-right"><input type="text" id="identity" name="users.identity" class="text"/></td>
        </tr>
        <tr>
          <td class="form-left">年龄</td>
          <td class="form-right"><input type="text" id="age" name="users.age" class="text"/></td>
        </tr>
        <tr>
          <td class="form-left"></span>性别</td>
          <td class="form-right">
          		<select  name="users.sex">
          			<option value="0">男</option>
					<option value="1">女</option>
          		</select>
          </td>
        </tr>
        <tr>
          <td class="form-left"><span class="form-required">*</span>车型</td>
          <td class="form-right"><select name="users.cartype">
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
							  </select> </td>
        </tr>
        <tr>
          <td class="form-left">手机号</td>
          <td class="form-right"><input type="text" id="number" name="users.phonenumber" class="text"/></td>
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