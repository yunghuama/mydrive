<%@ page language="java" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); 
   if(session.getAttribute("LoginBean")==null) {
	  response.sendRedirect(path+"/relogin.jsp");
	}
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=path%>/js/core.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//首先检查
	$("#button img").click(function(){
		var flag  =false;
		$.ajax({
		   type: "POST",
		   url: "<%=path%>/system/ajax/canExam.d",
		   data: {},
		   async: false,
		   success: function(msg){
		   	if(msg=='success')
		   		flag = true;
		   },
		   error : function(){
			  
		   }
		});
		if(!flag){
			alert("进入考场失败，请检查您的卡是否已经到期");
			return;
		}
		$("form").submit();
	})
});
</script>
<style type="text/css">
	#main {
		width:701px;
		height:350px;
		position:relative;
		margin:0px auto;
		border : 1px solid #bfbfbf;
		margin-top:50px;
		background-color:#f2f2f2;
	}
	#title{
		width:100%;
		position:relative;
		text-align:center;
		margin-top:30px;
	}
	#title span {
		font-size:16px;
	}
	
	#announce {
		margin-top:16px;
		margin-left:30px;
	}
	
	#announce li {
	font-size:16px;
	white-space: normal;
    word-wrap: break-word;
    word-break: break-all;
	}
	
	#left {
	  float:left;
	  width:280px;
	  height:350px;
	  border-right:1px solid #bfbfbf;
	}
	
	#right{
	  float:left;
	  width:420px;
	  text-align:center;
	}
	
	#button {
		text-align:center;
		margin-top:30px;
	}
	
	fieldset{
		border : 1px solid #bfbfbf;
		width:600px;
	}
	#name {
		margin-top:20px;
		text-align:left;
		margin-left:100px;
	}
	
	#identity,#cartype {
		text-align:left;
		margin-left:100px;
		margin-top:10px;
	}
	
	#name span,#identity span, #cartype span{
	font-size:16px;
	}
	
	#t1 {
		margin-top:60px;
		font-size:25px;
		font-weight:700;
	}
	#t2 {
		font-size:18px;
		font-weight:700;
	}
</style>
</head>
<body style="overflow:auto">
	 <div id="main">
	 		<div id="left">
			<div id="title"><span>考试须知</span></div>
			<div id="announce">
				<ol>
					<li>遵守考场纪律，服从监考人员指挥。</li>
					<li>进入考场，手机关机。禁止抽烟，吃零食。</li>
					<li>未经工作人员允许，考生禁止随意出入考场。</li>
					<li>考场内禁止大声喧哗，禁止随意走动。</li>
					<li>考试中认真答题，不准交头接耳。</li>
					<li>考试中不准冒名顶替，不准弄虚作假。</li>
					<li>注意考场卫生，禁止随地吐痰，禁止乱扔纸屑。</li>
					<li>爱护公物及考试设备。</li>
				</ol>
			</div>
			</div>
			<div id="right">
			<div id="t1">科目一全真模拟</div>
			<div id="t2">(练习模式)</div>
			<div id="name"><span>考生姓名:</span>&nbsp;&nbsp;<span><s:property value="#session['LoginBean'].user.name"/></span></div>
			<div id="identity"><span>证件号码:</span>&nbsp;&nbsp;<span><s:property value="#session['LoginBean'].user.identity"/></span></div>
			<div id="cartype"><span>考试车型:</span>&nbsp;&nbsp;<span><s:property value="#session['LoginBean'].user.cartype"/></span></div>
			<div></div>
			<div></div>
			<div id="button">
				<a href="javascript:void(0);"><img src="<%=path%>/image/startExam.png"/></a>
			</div>
			</div>
			<form action="<%=path%>/exam/subject1/initExerciseQuestion.d"></form>
	 </div>
	
</body>
</html>