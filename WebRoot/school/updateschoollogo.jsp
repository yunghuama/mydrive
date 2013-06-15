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
			$("form").submit();
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
	width:600px;
	height:200px;
	margin:0px auto;
	margin-top:100px;
}
fieldset {
	padding:20px 0;
	border : 1px solid #bfbfbf;
	background-color:#FFFFFF;
}
#barDiv{
	text-align:center;
}
.text {
	width:250px;
}
td {
	height:40px;
}
#slogo {
	width:50px;
	height:30px;
}
</style>
</head>
<body>
	<div id="content">
	<fieldset>
	<legend>驾校logo修改</legend>
	<form name="infoForm" id="infoForm" action="<%=path%>/exam/system/updateSLogo.d" class="form" method="post" enctype="multipart/form-data">
	<s:hidden name="users.id"></s:hidden>
      <table class="form-table" cellspacing="0" cellpadding="0">
        <tr>
          <td class="form-left">当前LOGO</td>
          <td class="form-right">
          <s:if test="logo!=null&&logo!=''">
          <img alt="" src="/image/slogo/<s:property value='logo'/>" id="slogo"> 
          </s:if>
          <s:else>
          <img alt="" src="<%=path %>/image/slogo.png" id="slogo"> 
          </s:else>
          </td>
        </tr>
        <tr>
          <td class="form-left"><span class="form-required">*</span>新LOGO</td>
          <td class="form-right"><input type="file" name="upload" class="file"></td>
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