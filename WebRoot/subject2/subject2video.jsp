<%@ page language="java" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
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
<script type="text/javascript" src="<%=path%>/js/flowplayer/embed.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/flowplayer/flowplayer.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
});
</script>
</head>
<body style="overflow:auto">
	 <div id="main">
			<div id="divcontent">
				<div class="videoDiv">
					<div class="title"><span>视频</span></div>
					<div class="video">
					<div class="flowplayer" data-swf="<%=path%>/js/flowplayer/flowplayer.swf" data-ratio="0.417">
						<video>
        		 		<source type="video/flv" src="http://stream.flowplayer.org/bauhaus/624x260.mp4"/>
     		  			</video>
     		  		</div>
					</div>
				</div>
				<div class="videoDiv">
					<div class="title"></div>
					<div class="video"></div>
				</div>
				<div class="videoDiv">
					<div class="title"></div>
					<div class="video"></div>
				</div>
				<div class="videoDiv">
					<div class="title"></div>
					<div class="video"></div>
				</div>
				<div class="videoDiv">
					<div class="title"></div>
					<div class="video"></div>
				</div>
				<div class="videoDiv">
					<div class="title"></div>
					<div class="video"></div>
				</div>
			</div>
	 </div>
	 
</body>
</html>