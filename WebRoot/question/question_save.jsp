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
<script type="text/javascript">
$(document).ready(function(){
	
});
</script>
</head>
<body style="overflow:auto">
	 <div id="main">
			<div id="title"><span>公告管理:添加公告</span></div>
			<div id="sectionContent">
				<form action="<%=path%>/exam/subject1/saveAnnouncement.d" method="post">
				问题:<input type="text" name="announcement.title"><br/>
				答案a:<input type="text" name="announcement.title"><br/>
				答案b:<input type="text" name="announcement.title"><br/>
				答案c:<input type="text" name="announcement.title"><br/>
				答案d:<input type="text" name="announcement.title"><br/>
				所属题库:<input type="text" name="announcement.title"><br/>
				图片:<input type="text" name="announcement.title"><br/>
				视频:<input type="text" name="announcement.title"><br/>
				所属章节:<input type="text" name="announcement.title"><br/>
				提示:<input type="text" name="announcement.title"><br/>
				<input type="submit" value="保存">
				</form>
			</div>
	 </div>
	 
</body>
</html>