<%@ page language="java" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); 
   String name = (String)request.getParameter("name");
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/svideo.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/flowplayer/skin/minimalist.css" />
<script type="text/javascript" src="<%=path%>/js/core.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/ckplayer/ckplayer.js" charset="utf-8"></script>
<script type="text/javascript">
$(document).ready(function(){
	
});
</script>
<style type="text/css">
	.video{
		width:800px;
		height:600px;
		margin:50px auto;
	}
</style>
</head>
<body style="overflow:auto">
	<div class="video">
	<embed src="<%=path%>/js/ckplayer/ckplayer.swf" flashvars="f=/image/5/<%=name %>&p=0" quality="high" width="700" height="400" align="middle" allowScriptAccess="always" allowFullscreen="true" type="application/x-shockwave-flash"></embed>
	</div>
</body>
</html>