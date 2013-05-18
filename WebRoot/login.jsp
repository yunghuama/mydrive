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
      background-color:#081733;
      overflow:hidden;
    }
    body { background-image:url(image/login/login_bg.png); background-repeat:repeat-x;}
    form {
      padding:0;
      margin:0;
      height:347px;
      width:378px;
      margin:0 auto;
      position:relative;
      background-image:url(image/login/login.png);
      z-index:2;
    }
    input {position:absolute;}
    .textInput {
      color:#4E4E4E;
      font-size:22px;
      font-family:verdana,simsun,sans-serif;
      font-weight:700;
      width:150px;
      height:30px;
      line-height:30px;
      background:transparent;
      border:none;
    }
    .acnDiv { left:148px; top:145px;}
    .pwdDiv { left:148px; top:205px;}
    .sbtDiv {
      left:225px;
      top:260px;
      cursor:pointer;
      width:80px;
      height:40px;
      background-color:#FFFFFF;
      border:none;
      filter:alpha(opacity = 0);
      -moz-opacity:0;
      opacity:0;
    }
    .noRemember {
      position:absolute;
      top:260px;
      left:73px;
      display:block;
      width:140px;
      height:40px;
      background:transparent url(image/login/no_remember.gif) no-repeat;
      cursor:pointer;
    }
    .remember {
      background:transparent url(image/login/remember.gif) no-repeat;
    }
    #loginBack {
      height:200px;
      width:100%;
      background:transparent url(image/login/login_back.png) center;
      position:absolute;
      left:0;
      z-index:1;
    }
    </style>
  </head>

  <body>
    <form id="loginForm" method="post" action="<%=path%>/login.d">
      <input type="hidden" id="remember" name="remember" value="${cookie.remember.value}"/>
      <input type="text" id="accountName" name="accountName" class="acnDiv textInput" value="${cookie.accountName.value}"/>
      <input type="password" id="passwd" name="password" class="pwdDiv textInput" value="${cookie.password.value}"/>
      <input id="submitButton" type="submit" value="" class="sbtDiv"/>
      <a id="rememberTrigger" class="noRemember"></a>
    </form>
    <div id="loginBack"></div>
    <script src="<%=path%>/js/jquery.js" type="text/javascript"></script>
    <script type="text/javascript">
    var remember = $('#remember');
    $(document).ready(function(){
      if(remember.val()=='T') {
        $('#rememberTrigger').addClass('remember');
        $('#loginForm').submit();
      }
      $('#loginForm').css('margin-top', ($(document.body).height()/2)-173);
      $('#loginBack').css('top', ($(document.body).height()/2)-100);
      $('#submitButton').hover(function(){
        $(this).css({
          'filter' : 'alpha(opacity = 20)',
          'opacity' : '0.2'
        });
      },function(){
        $(this).css({
          'filter' : 'alpha(opacity = 0)',
          'opacity' : '0'
        });
      });
      $('#rememberTrigger').click(function(){
        if(remember.val()=='T') {
          remember.val('F');
          $(this).removeClass('remember');
        } else {
          remember.val('T');
          $(this).addClass('remember');
        }
      });
      $(window).resize(function(){
        $('#loginForm').css('margin-top', ($(this).height()/2)-173);
      });
      if('${param.PW}'=='1') {
        alert('用户名或密码错误，请重新登录');
        //注销一下，防止拿缓存数据去验证而不停的alert
        window.location = '<%=path%>/logout.d';
      }
      if('${param.PW}'=='2') {
        alert('该用户已被禁用，无法登录');
      }
    });
    </script>
  </body>
</html>