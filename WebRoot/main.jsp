<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
if(session.getAttribute("LoginBean")==null) {
  response.sendRedirect(path+"/login.jsp");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title></title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
    <link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/css/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/js/nav.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			//初始化菜单
			initNav();
			//添加点击效果
			
			$("a[name='id_1_1']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/exam/subject1/initExerciseQuestion.d");
			});
		});
	
	</script>
  </head>
  <body>
  <div id="head">
  		<div id="logo"></div>
  		<div id="nav-div">
  			<ul id="nav" class="nav">
  				<li class="menu-item">
  					<a href="javascript:void(0);">科目一</a>
  					<ul class="sub-nav">
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_1_1">练习模式</a></li>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_1_2">模拟考试</a></li>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_1_3">顺序练习</a></li>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_1_4">标记练习</a></li>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_1_5">错题练习</a></li>
  					</ul>
  					</li>
  				<li class="menu-item">
  				    <a href="javascript:void(0);">科目二</a>
  				    <ul class="sub-nav">
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_2_1">考试须知</a></li>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_2_2" >场地仿真动画</a></li>
  					</ul>
  				    </li>
  				<li class="menu-item">
  				    <a href="javascript:void(0);">科目三</a>
  				    <ul class="sub-nav">
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_3_1">练习模式</a></li>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_3_2">模拟考试</a></li>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_3_3">顺序练习</a></li>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_3_4">标记练习</a></li>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_3_5">错题练习</a></li>
  					</ul>
  				    </li>
  				<li class="menu-item"><a href="javascript:void(0);" name="id_4_1">个人中心</a></li>
  			</ul>
  		</div>
  		<div id="userInfo">
  		欢迎您：<s:property value="#session['LoginBean'].user.name"/> | 剩余次数：<s:property value="#session['LoginBean'].user.remindtimes"/>
  		| 剩余天数：<s:property value="#session['LoginBean'].user.reminddays"/>
  		| <a href="<%=path%>/logout.d">安全退出</a>
  		</div>
  </div>
  <div id="content">
  	<iframe id="mainFrame" class="mainFrame" src="subject1/simulation.jsp"></iframe>
</html>