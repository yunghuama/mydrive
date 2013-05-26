<%@ page language="java" pageEncoding="UTF-8"
  contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Platform</title>
    <style type="text/css">
    html,body {
      padding:0;
      margin:0;
      width:100%;
      height:100%;
      overflow:hidden;
    }
    div {
    }
    #main{
      width:1010px;
      position:relative;
      margin:0px auto;
     
    }
	#logo {
		width:500px;
		height:86px;
		position:relative;
	}
	
	#logo img{
		position:absolute;
		right:30px;
	}
	
	#car {
		float:left;
		width:500px;
		text-align:center;
		padding:40px 0px;
	}
	#loginDiv {
		float:left;
		text-align:center;
		width:500px;
		padding:70px 0px;
		border-left : 1px solid #7d7d7d;
	}
	#loginDiv input {
		background-image:url("image/input.png");
		border : none;
		color:#FFFFFF;
      	font-size:16px;
      	font-family:verdana,simsun,sans-serif;
     	width:235px;
     	height:45px;
     	line-height:30px;
        border:none;
        margin-top:10px;
        text-align:center;
	}
	
	#loginDiv a {
		 margin-top:10px;
		 text-decoration : none;
		 display : block;
	}
	
	#center{
		height:320px;
		margin-top:30px;
	}
	#password {
		display:none;
	}
	a {
		blr:expression(this.onfocus=this.blur());
		outline:none;
	}
	
    </style>
  </head>

  <body>
    <div id="main">
    	<div id="logo"><img src="<%=path %>/image/logo.png" alt="" /></div>
    	<div id="center">
    	<div id="car"><img src="<%=path %>/image/car.png" alt="" /> </div>
    	<div id="loginDiv">
    	<form id="loginForm" method="post" action="<%=path%>/login.d">
    		<input id="accountName" name="accountName" type="text"/><br/>
    		<input id="title" type="text"/><input id="password" name="password" type="password" /><br/>
    		<a href="javascript:void(0);"><img src="image/loginButton.png" alt="" id="submitImg"/></a>
    	</form>
    	</div>
    	</div>
    </div>

    <script src="<%=path%>/js/jquery.js" type="text/javascript"></script>
    <script type="text/javascript">
    var defaultVal = "帐号";
    var defaultPass = "密码";
    $(document).ready(function(){
      $("input:visible").eq(0).val(defaultVal).bind("focus",function(){
			$(this).val("");
			$(this).unbind("focus");
      });
      $("input:visible").eq(1).val(defaultPass).bind("focus",function(){
			$(this).unbind("focus");
    	    $(this).remove();
			$("#password").show();
			$("#password").focus();
      });
      $("#password").bind("keydown",function(e){
    	  var key = e.which;
          if (key == 13) {
        	  e.preventDefault();
        	  postSubmit();
          }
      });
      $("#main").css('margin-top',($("body").height()-$("#main").height())/2-30);
      $(window).resize(function(){
    	  $("#main").css('margin-top',($("body").height()-$("#main").height())/2-30);
      });
      if('${param.PW}'=='1') {
        alert('用户名或密码错误，请重新登录');
        //注销一下，防止拿缓存数据去验证而不停的alert
        window.location = '<%=path%>/logout.d';
      }
      if('${param.PW}'=='2') {
        alert('该用户已被禁用，无法登录');
      }
      $("#submitImg").click(function(){
    	  postSubmit();
      });
    });
    
    function postSubmit(){
      var accountName = $("#accountName").val();
  	  var password = $("#password").val();
  	  if(accountName==defaultVal||accountName==""){
  		  alert("请输入帐号");
  		  return;
  	  }
  	  if(password==defaultPass||password==""){
  		  alert("请输入密码");
  		  return;
  	  }
  	  $("#loginForm").submit();
    }
    </script>
  </body>
</html>