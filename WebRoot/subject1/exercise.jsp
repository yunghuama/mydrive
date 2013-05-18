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
	var exam = new Exam({'selectGridRenderTo':'questionnumber','questionRenderTo':'questionContent','richMediaRenderTo':'questionImage'});
	//添加题目
	 <s:iterator id="question" value="list" status="i">
	 var question = new Question('<s:property value="#i.index"/>','<s:property value="#question.id"/>','<s:property value="#question.question"/>','<s:property value="#question.answer_a"/>','<s:property value="#question.answer_b"/>','<s:property value="#question.answer_c"/>','<s:property value="#question.answer_d"/>','<s:property value="#question.answer"/>','<s:property value="#question.image"/>','<s:property value="#question.vedio"/>');
	 exam.add(question);
	 </s:iterator>
	//绘制表格
	 exam.drawSelectGrid();
	//默认选择第一个
	 exam.drawQuestion(exam.getQuestion(0));
	 exam.setClickBorder(exam.getNum(0));
	 $("#prev").bind("click",function(){
		 var question = exam.getPrev();
		 if(question==null||question==undefined)
			 return;
		 exam.drawQuestion(question);
		 exam.setClickBorder(exam.getNum(question.num));
	 });
 	$("#next").bind("click",function(){
 		var question = exam.getNext();
 		if(question==null||question==undefined)
			 return;
		 exam.drawQuestion(question);
		 exam.setClickBorder(exam.getNum(question.num));
	 });
 	$("#reexam").bind("click",function(){
 		location.reload();
	 });
 	$("#commitexam").bind("click",function(){
 		exam.score();
	 });
});
</script>
</head>
<body style="overflow: auto">
	 <div id="main">
	 <div id="persionInfo">
	 
	 </div>
	 <div id="questionDiv">
	 <div id="questionContent">
	 
	 </div>
	 <div id="questionButton">
	 <button id="prev">上一个</button>
	 <button id="next">下一个</button>
	 <button id="reexam">重考</button>
	 <button id="commitexam">交卷</button>
	 </div>
	 </div>
	 <div id="questionnumber">
	 
	 </div>
	 <div id="questionImage"></div>
	 </div>
</body>
</html>