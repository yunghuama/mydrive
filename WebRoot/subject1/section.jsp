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
<script type="text/javascript">
$(document).ready(function(){
	$("table tr").bind("mouseover",function(){
		if($("th",this).size()>0){
			return;
		}
		$(this).addClass("mouseover");
	}).bind("mouseout",function(){
		$(this).removeClass("mouseover");
	});
});
</script>
</head>
<body style="overflow:auto">
	 <div id="main">
			<div id="title"><span>科目一章节</span></div>
			<div id="sectionContent">
				<table>
					<tr>
						<th width="10%">序号</th>
						<th>章节名称</th>
						<th  width="10%">操作</th>
					</tr>
					 <s:iterator id="section" value="sectionList" status="i">
					<tr id='<s:property value="#section.id"/>'>
						<td><s:property value="#i.index+1"/> </td>
						<td><s:property value="#section.name"/> </td>
						<td><a href='<%=path%>/exam/subject1/orderQuestion1.d?categoryId=<s:property value="#section.id"/>'>开始练习</a> </td>
					</tr>
					</s:iterator>
				</table>
			
			</div>
	 </div>
	 
</body>
</html>