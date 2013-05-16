<%@ page language="java" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/exam.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/exam.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	var exam = new Exam();
	 <s:iterator id="question" value="list" status="i">
	 var question = new Question('<s:property value="#i.index"/>','<s:property value="#question.id"/>','<s:property value="#question.question"/>','<s:property value="#question.answer_a"/>','<s:property value="#question.answer_b"/>','<s:property value="#question.answer_c"/>','<s:property value="#question.answer_d"/>','<s:property value="#question.answer"/>','<s:property value="#question.image"/>','<s:property value="#question.vedio"/>');
	 exam.add(question);
	 </s:iterator>
	
	
});
</script>
</head>
<body>
	 <div id="main">
	 <div id="persionInfo">

	 </div>
	 <div id="questionContent">
	 
	 </div>
	 <div id="questionnumber">
	 
	 </div>
	 </div>
</body>
</html>