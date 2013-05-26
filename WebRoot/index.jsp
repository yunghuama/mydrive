<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>驾考之家</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
     <script type="text/javascript" src="<%=path%>/js/core.js" ></script>
    <script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
  </head>
  <body>
   
    <script type="text/javascript">
    window.location = projectName+'/main.jsp';
    </script>
  </body>
</html>