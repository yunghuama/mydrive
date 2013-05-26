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
	<script type="text/javascript" src="<%=path%>/js/core.js"></script>
	<script type="text/javascript" src="<%=path%>/js/adver.js"></script>
	<style type="text/css">
	#adsLeft {
		position:absolute;
		left:10px;
		top:200px;
		width:100px;
		height:200px;
	}
	
	#adsRight {
		position:absolute;
		right:10px;
		top:200px;
		width:100px;
		height:200px;
	}
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			//初始化菜单
			initNav();
			//添加点击效果
			$("a[name='id_0_0']").bind("click",function(){
				//$("#mainFrame").attr("src","<%=path%>/system/examlist.jsp");
				$("#mainFrame").attr("src","<%=path%>/exam/subject1/importExcel.d");
			});
			
			//科目一
			$("a[name='id_1_1']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/subject1/toExercise.jsp");
			});
			
			$("a[name='id_1_2']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/subject1/toSimulation.jsp");
			});
			
			$("a[name='id_1_3']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/exam/subject1/section1.d");
			});
			
			$("a[name='id_1_4']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/exam/subject1/markQuestion1.d");
			});
			
			$("a[name='id_1_5']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/exam/subject1/listWrongQuestion.d");
			});
			
			//科目二
			$("a[name='id_2_1']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/subject2/subject2video.jsp");
			});
			
			//科目三
			$("a[name='id_3_1']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/subject3/toExercise.jsp");
			});
			
			$("a[name='id_3_2']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/subject3/toSimulation.jsp");
			});
			
			$("a[name='id_3_3']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/exam/subject3/section.d");
			});
			
			$("a[name='id_3_4']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/exam/subject3/markQuestion.d");
			});
			
			$("a[name='id_3_5']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/exam/subject3/listWrongQuestion.d");
			});
			
			//个人资料修改
			$("a[name='id_4_1']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/exam/system/toUpdateUsers.d");
			});
			$("a[name='id_4_2']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/system/updatePass.jsp");
			});
			$("a[name='id_4_3']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/system/updateTime.jsp");
			});
			
			//驾校管理
			$("a[name='id_5_1']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/exam/system/listAnnouncementStu.d");
			});
			$("a[name='id_5_2']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/exam/system/listAnnouncement.d");
			});
			$("a[name='id_5_3']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/exam/system/listScore1.d");
			});
			$("a[name='id_5_4']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/exam/system/listScore3.d");
			});
			
			//题库设置
			$("a[name='id_6_1']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/exam/subject1/listQuestion.d");
			});
			$("a[name='id_6_2']").bind("click",function(){
				$("#mainFrame").attr("src","<%=path%>/system/adver/list.d");
			});
			
			new Adver({
		      	  "renderTo":$("#adsLeft"),
		      	   "page":1,
		      	   "position":0
		        });
		      
		      new Adver({
		      	  "renderTo":$("#adsRight"),
		      	   "page":1,
		      	   "position":1
		        });
		});
	
	</script>
  </head>
  <body>
  <div id="head">
  		<div id="nav-div">
  			<ul id="nav" class="nav">
  				<li class="menu-item">
  					<s:if test='@com.platform.constants.StringConstant@getOperate(\"index\")=="T"'>
  					<a href="javascript:void(0);" class="title" name="id_0_0">首页</a>
  					</s:if>
  				</li>
  				<s:if test='@com.platform.constants.StringConstant@getOperate(\"sub1\")=="T"'>
  				<li class="menu-item">
  					<a href="javascript:void(0);" class="title">科目一</a>
  					<ul class="sub-nav">
  						<s:if test='@com.platform.constants.StringConstant@getOperate(\"sub1_exercise\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_1_1">练习模式</a></li>
  						</s:if>
  						<s:if test='@com.platform.constants.StringConstant@getOperate(\"sub1_simulation\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_1_2">模拟考试</a></li>
  						</s:if>
  						<s:if test='@com.platform.constants.StringConstant@getOperate(\"sub1_order\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_1_3">顺序练习</a></li>
  						</s:if>
  						<s:if test='@com.platform.constants.StringConstant@getOperate(\"sub1_mark\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_1_4">标记练习</a></li>
  						</s:if>
  						<s:if test='@com.platform.constants.StringConstant@getOperate(\"sub1_wrong\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_1_5">错题练习</a></li>
  						</s:if>
  					</ul>
  				</li>
  				</s:if>
  				<s:if test='@com.platform.constants.StringConstant@getOperate(\"sub2\")=="T"'>
  				<li class="menu-item">
  				    <a href="javascript:void(0);" class="title">科目二</a>
  				    <ul class="sub-nav">
  				    	<s:if test='@com.platform.constants.StringConstant@getOperate(\"sub2_animation\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_2_1">场地仿真动画</a></li>
  						</s:if>
  					</ul>
  				    </li>
  				</s:if>
  				<s:if test='@com.platform.constants.StringConstant@getOperate(\"sub3\")=="T"'>
  				<li class="menu-item">
  				    <a href="javascript:void(0);" class="title">科目三</a>
  				    <ul class="sub-nav">
  				    	<s:if test='@com.platform.constants.StringConstant@getOperate(\"sub3_exercise\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_3_1">练习模式</a></li>
  						</s:if>
  						<s:if test='@com.platform.constants.StringConstant@getOperate(\"sub3_simulation\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_3_2">模拟考试</a></li>
  						</s:if>
  						<s:if test='@com.platform.constants.StringConstant@getOperate(\"sub3_order\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_3_3">顺序练习</a></li>
  						</s:if>
  						<s:if test='@com.platform.constants.StringConstant@getOperate(\"sub3_mark\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_3_4">标记练习</a></li>
  						</s:if>
  						<s:if test='@com.platform.constants.StringConstant@getOperate(\"sub3_wrong\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_3_5">错题练习</a></li>
  						</s:if>
  					</ul>
  				    </li>
  				 </s:if>
  				 <s:if test='@com.platform.constants.StringConstant@getOperate(\"user\")=="T"'>
  				<li class="menu-item">
  					<a href="javascript:void(0);" class="title">个人中心</a>
  					 <ul class="sub-nav">
  					 	<s:if test='@com.platform.constants.StringConstant@getOperate(\"user_info\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_4_1">个人资料修改</a></li>
  						</s:if>
  						<s:if test='@com.platform.constants.StringConstant@getOperate(\"user_pass\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_4_2">密码修改</a></li>
  						</s:if>
  						<s:if test='@com.platform.constants.StringConstant@getOperate(\"user_money\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_4_3">续费</a></li>
  						</s:if>
  					</ul>
  				</li>
  				</s:if>
  				<s:if test='@com.platform.constants.StringConstant@getOperate(\"school\")=="T"'>
  				<li class="menu-item">
  					<a href="javascript:void(0);" class="title">驾校公告</a>
  					 <ul class="sub-nav">
  						 <s:if test='@com.platform.constants.StringConstant@getOperate(\"school_announce\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_5_1">驾校公告</a></li>
  						</s:if>
  						<s:if test='@com.platform.constants.StringConstant@getOperate(\"school_announce_admin\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_5_2">公告管理</a></li>
  						</s:if>
  						<s:if test='@com.platform.constants.StringConstant@getOperate(\"school_score1\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_5_3">学员成绩科目一</a></li>
  						</s:if>
  						<s:if test='@com.platform.constants.StringConstant@getOperate(\"school_score3\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_5_4">学员成绩科目三</a></li>
  						</s:if>
  					</ul>
  				</li>
  				</s:if>
  				<s:if test='@com.platform.constants.StringConstant@getOperate(\"question\")=="T"'>
  				<li class="menu-item">
  					<a href="javascript:void(0);" class="title">题库管理</a>
  					 <ul class="sub-nav">
  					 	<s:if test='@com.platform.constants.StringConstant@getOperate(\"question_admin\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_6_1">题库管理</a></li>
  						</s:if>
  						<s:if test='@com.platform.constants.StringConstant@getOperate(\"ads\")=="T"'>
  						<li class="sub-menu-item"><a href="javascript:void(0);" name="id_6_2">广告管理</a></li>
  						</s:if>
  					</ul>
  				</li>
  				</s:if>
  			</ul>
  		</div>
  		<div id="userInfo">
  		欢迎您：<s:property value="#session['LoginBean'].user.name"/> 
  		<s:if test='#session["LoginBean"].user.role=="STUDENT"'>
  		| 剩余次数：<s:property value="#session['LoginBean'].user.remindtimes"/>
  		| 剩余天数：<s:property value="#session['LoginBean'].user.reminddays"/>
  		</s:if>
  		&nbsp;&nbsp;| &nbsp;&nbsp;<a href="<%=path%>/logout.d">安全退出</a>
  		</div>
  </div>
  <div id="content">
  	<iframe id="mainFrame" class="mainFrame" src="system/examlist.jsp"></iframe>
  </div>
  	<div id="adsLeft"></div>
	<div id="adsRight"></div>
	</body>
</html>