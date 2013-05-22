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
			<div id="title"><span>题库管理</span></div>
			<div id="questionContent">
				<table>
					   <tr>
					   		<th>题号</th>
					   		<th>问题</th>
					   		<th>A选项</th>
					   		<th>B选项</th>
					   		<th>C选项</th>
					   		<th>D选项</th>
					   		<th>答案</th>
					   		<th>图片</th>
					   		<th>视频</th>
					   		<th>答案提示</th>
					   		<th>所属章节</th>
					   </tr>
						<s:iterator id="question" value="page.list">
						<tr>
							<td><span><s:property value="#question.id"/></span></td>
							<td><span><s:property value="#question.question"/></span></td>
							<td><span><s:property value="#question.answer_a"/></span></td>
							<td><span><s:property value="#question.answer_b"/></span></td>
							<td><span><s:property value="#question.answer_c"/></span></td>
							<td><span><s:property value="#question.answer_d"/></span></td>
							<td><span><s:property value="#question.answer"/></span></td>
							<td><span><s:property value="#question.image"/></span></td>
							<td><span><s:property value="#question.video"/></span></td>
							<td><span><s:property value="#question.tips"/></span></td>
							<td><span><s:property value="#question.id"/></span></td>
						</tr>
						
						</s:iterator>
				</table>
			</div>
	 </div>
	 
</body>
</html>