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
    <title>通用办公平台</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
    <link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/css/index.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/css/message.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/css/autocomplete.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/css/xTabPanel.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/css/xWindowPanel.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/css/xToolbar.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/css/form.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
    <!-- 消息提醒 -->
    <div id="vMsgDiv">
      <div id="vMsg">
        <div class="vMsg-title">审批提醒(<span id="_num1">0</span>)</div>
        <div id="vMsg-items1"></div>
        <div class="vMsg-title">日志批注(<span id="_num2">0</span>)</div>
        <div id="vMsg-items2"></div>
        <div class="vMsg-title">内部通讯(<span id="_num0">0</span>)</div>
        <div id="vMsg-items0"></div>
      </div>
      <div id="vMsgFoot">
        <div id="vMsgAll">全 部</div>
        <div id="vMsgClose">&nbsp;</div>
      </div>
      <div id="vMsgArrow"></div>
      <div id="vMsgShadow"></div>
    </div>
    <!-- END 消息提醒 END -->
    
    <s:include value="/share/load.jsp">
      <s:param name="msg" value="'数据加载中...'"/>
    </s:include>
    <div class="header">
      <div class="logo"></div>
      <div class="info"><span><img id="hasNoRead" src="<%=path%>/image/index/havemessage.gif" style="display:none;"/><a href="javascript:void(0);" id="noteView">您有(<b id="noteCount" style="color:#E3EAF5;font-size:13px;">0</b>)条未读消息</a></span>&nbsp;&nbsp;&nbsp;&nbsp;欢迎您：<s:property value="#session['LoginBean'].user.realName"/> | <a href="javascript:void(0);openChangePassword();">修改密码</a> | <a href="#" style="cursor:help;">帮助</a> | <a href="javascript:void(0);window.location = '<%=path%>/logout.v';">注销</a></div>
    </div>

    <s:set name="tString" value="@com.platform.constants.StringConstant@TRUE"/>
    <ul class="module">
      <li class="od">工作台
        <ul class="module-second">
<!--            <li id="desktop" class="second"><a href="javascript:void(0)">桌面</a></li>-->
            <li id="gzrz" class="second"><a href="javascript:void(0)">工作日志</a>
              <!--
              <ul class="module-third">
                <li id="gzrz" class="third"><a href="javascript:void(0)">工作日志</a></li>   
                <li id="b"><a href="javascript:void(0)">SNS版</a></li>
                <li id="t"><a href="javascript:void(0)">标签管理</a></li>
              </ul>
              -->
            </li>
            <li id="hdgl" class="second"><a href="javascript:void(0)">活动管理</a></li>
<!--            <li id="zsk" class="second"><a href="javascript:void(0)">知识库</a></li> -->
        </ul>
      </li>
<!--      
      <li class="od">业务伙伴
        <ul class="module-second">
            <li id="gysgl" class="second"><a href="javascript:void(0)">供应商管理</a></li>
            <li id="lxrgl" class="second"><a href="javascript:void(0)">联系人管理</a></li>
         </ul>
      </li>
-->     
      
      <li class="od">系统管理
        <ul class="module-second">
            <li id="dic" class="second"><a href="javascript:void(0)">字典管理</a></li>
            <li id="user" class="second"><a href="javascript:void(0)">员工管理</a></li>
            <li id="dept" class="second"><a href="javascript:void(0)">部门管理</a></li>
            <li id="ro" class="second"><a href="javascript:void(0)">角色管理</a></li>
            <li id="sys" class="second"><a href="javascript:void(0)">系统日志</a></li>
<!-- 
            <li id="mes" class="second"><a href="javascript:void(0)">系统消息</a></li>
-->
            <!-- 
            <s:if test="@com.platform.util.Meta@getOperate('dataSyn_view') == #tString">
            <li id="syn" class="second"><a href="javascript:void(0)">数据同步</a></li>
            </s:if>
             -->
         </ul>
      </li>
    </ul>
    <div id="main" class="main"></div>
    <div class="footer">Copyright&nbsp;&copy;&nbsp;Vwintech</div>
    
  <script src="<%=path%>/js/core.js" type="text/javascript"></script>
  <script src="<%=path%>/js/jquery.js" type="text/javascript"></script>
  <script src="<%=path%>/js/index.js" type="text/javascript"></script>
  <script src="<%=path%>/js/share.js" type="text/javascript"></script>
  <script src="<%=path%>/js/xTabPanel.js" type="text/javascript"></script>
  <script src="<%=path%>/js/Draggable.js" type="text/javascript"></script>
  <script src="<%=path%>/js/xWindowPanel.js" type="text/javascript"></script>
  <script src="<%=path%>/js/xToolbar.js" type="text/javascript"></script>
  <script src="<%=path%>/js/xToolbar.items.js" type="text/javascript"></script>
  <script src="<%=path%>/js/modules/system.js" type="text/javascript"></script>

  <script type="text/javascript">
    $(document).ready(function(){
      $('#vMsgClose').bind('click', function(){
        $('#vMsgDiv').slideUp(300);
      });
      $('#noteView').bind('click', function(){
        $('#vMsgDiv').slideToggle(300);
      });
      $('#vMsgAll').bind('click', intercomList);
      
      $('.header .btn').bind('click', startSearch).hover(
        function(){$(this).addClass('btn-hover')},
        function(){$(this).removeClass('btn-hover')}
      );
      
      
    });
    </script>
  </body>
</html>