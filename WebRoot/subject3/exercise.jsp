<%@ page language="java" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); 
	String server = request.getRemoteHost();
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/exam.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=path%>/js/core.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/mask.js"></script>
<script type="text/javascript" src="<%=path%>/js/exam.js"></script>
<script type="text/javascript" src="<%=path%>/js/time.js"></script>
<script type="text/javascript" src="<%=path%>/js/ckplayer/ckplayer.js" charset="utf-8"></script>
<script type="text/javascript">
$(document).ready(function(){
	var exam = new Exam({'selectGridRenderTo':'questionnumber','questionRenderTo':'questionContent','richMediaRenderTo':'questionImage','qScore':2});
	//添加题目
	 <s:iterator id="question" value="list" status="i">
	 var question = new Question('<s:property value="#i.index"/>','<s:property value="#question.id"/>','<s:property value="#question.question"/>','<s:property value="#question.answer_a"/>','<s:property value="#question.answer_b"/>','<s:property value="#question.answer_c"/>','<s:property value="#question.answer_d"/>','<s:property value="#question.answer"/>','<s:property value="#question.image"/>','<s:property value="#question.video"/>','/sub3');
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
 		if(confirm("您确定要重新考试吗?")){
 			location.reload();
 		}
	 });
 	var timer = new Timer({"time":45*60,"renderTo":"timeDiv","complete":function(){
 		exam.score();
 	}});
 	timer.start();
 	$("#commitexam").bind("click",function(){
 		if(confirm("您确定要交卷吗?")){
 			exam.score();
 			timer.stop();
 		}
	 });
});
</script>
</head>
<body style="overflow:auto">
	<div id="commiting">
	</div>
	<div id="commitingInfo">
	<dir id="commitingInfoTitle"><span></span></dir>
	<dir id="commitingButton"><button id="commitingButtonClose">关闭</button> </dir>
	</div>
	 <div id="main">
	 <div id="persionInfo">
	 <div id="infoTitle"><span>考生信息</span></div>
	 <div id="info">
	 	<table id="infoTable" align="center" width="100%">
	 		<tr><td>题型：</td><td>科目三</td></tr>
	 		<tr><td>模式：</td><td>练习模式</td></tr>
	 		<tr><td>姓名：</td><td><s:property value="#session['LoginBean'].user.name"/></td></tr>
	 		<tr><td>车型：</td><td><s:property value="#session['LoginBean'].user.cartype"/></td></tr>
	 	</table>
	 </div>
	 <div id="timeDiv"></div>
	 </div>
	 <div id="questionDiv">
	 <div id="questionTitle"><span>答题区</span></div>
	 <div id="questionContent"></div>
	 <div id="questionButton">
	 <button id="prev">上一个</button>
	 <button id="next">下一个</button>
	 <button id="reexam">重考</button>
	 <button id="commitexam">交卷</button>
	 </div>
	 </div>
	 <div id="questionnumber">
	
	 </div>
	 <div id="questionImageDiv">
	 	<div id="questionImage"></div>
	 </div>
	 </div>
</body>
</html>