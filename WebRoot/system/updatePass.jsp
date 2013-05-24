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
			var oldPass = $("#oldPass").val();
			var newPass = $("#newPass").val();
			var newPass1 = $("#newPass1").val();
			if(newPass!=newPass1){
				alert("两次输入的密码不一样");
				return;
			}
			$.ajax({
				   type: "POST",
				   url: '<%=path%>/system/ajax/updatePass.d',
				   data: {'oldPass':oldPass,'newPass':newPass},
				   async: false,
				   success: function(data){
					  if(data=='success'){
						  alert("密码修改成功,请重新登录");
					  }else {
						  alert("密码修改失败");
					  }
				   }
				});
			
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
	height:200px;
	margin:0px auto;
	margin-top:100px;
}
fieldset {
	padding:20px;
	border : 1px solid #7d7d7d;
}
#barDiv{
	text-align:center;
}
</style>
</head>
<body>
	<div id="content">
	<fieldset>
	<legend>密码修改</legend>
	<form name="infoForm" id="infoForm" action="<%=path%>/exam/system/updatePass.d" class="form" method="post">
	<s:hidden name="users.id"></s:hidden>
      <table class="form-table" cellspacing="0" cellpadding="0">
        <tr>
          <td class="form-left"><span class="form-required">*</span>旧密码</td>
          <td class="form-right"><s:password id="oldPass" name="oldPass" theme="simple" cssClass="text"/> </td>
        </tr>
        <tr>
          <td class="form-left"><span class="form-required">*</span>新密码</td>
          <td class="form-right"><s:password id="newPass" name="newPass" theme="simple" cssClass="text"/></td>
        </tr>
        <tr>
          <td class="form-left"><span class="form-required">*</span>重复新密码</td>
          <td class="form-right"><s:password id="newPass1" name="newPass1" theme="simple" cssClass="text"/></td>
        </tr>
      </table>
    </form>
    </fieldset>
	</div>
	<div id="barDiv">
			<a id="next" href="javascript:void(0);"><img alt="" src="<%=path%>/image/save.png"> </a>
	</div>
</body>
</html>