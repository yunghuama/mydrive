<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title></title>
    <link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
    .textDiv{
    	background-image : url("<%=path%>/image/success.png");
    	width : 398px;
    	height: 230px;
    	position:absolute;
    	text-align:center;
    }
    .textDiv img {
    	margin-top:150px;
    }
    .noData {
    	width:100%;
    	height:100%;
    }
    </style>
  </head>
  <body>
    <div class="noData">
      <div class="textDiv">
      		<a href="javascript:void(0);"><img src="<%=path%>/image/back.png" alt="" /></a>
       </div>
    </div>
    <script src="<%=path%>/js/jquery.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(function(){
      var textDiv = $('div.textDiv');
      
      textDiv.css({
        left: ($(document.body).width() - textDiv.width())/2,
        top: ($(document.body).height() - textDiv.height())/2-30
      });
    });
    
    $(document).ready(function(){
		$(".textDiv img").click(function(){
			history.go(-1);
		});
		
	});
    </script>
  </body>
</html>