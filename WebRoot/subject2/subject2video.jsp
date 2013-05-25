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
<script type="text/javascript" src="<%=path%>/js/ckplayer/ckplayer.js" charset="utf-8"></script>
<script type="text/javascript">
$(document).ready(function(){
	
});
</script>
<style type="text/css">
#des{
	margin: 40px 0px;
}
#des span {
	color:#7d7d7d;
	font-size:28px;
	font-weight:700;
}
embed {
	z-index:100;
}
</style>
</head>
<body style="overflow:auto">
	 <div id="main">
			<div id="divcontent">
				<div id="des"><span>场地仿真动画</span></div>
				<div class="videoDiv">
					<div class="title"><span>考试须知</span></div>
					<div class="video">
					<embed src="<%=path%>/js/ckplayer/ckplayer.swf" flashvars="f=/image/5/0.flv&p=0" quality="high" width="300" height="200" align="middle" allowScriptAccess="always" allowFullscreen="true" type="application/x-shockwave-flash"></embed>
					</div>
				</div>
				<div class="videoDiv">
					<div class="title"><span>1号车侧方停车</span></div>
					<div class="video">
					<embed src="<%=path%>/js/ckplayer/ckplayer.swf" flashvars="f=/image/5/1.flv&p=0" quality="high" width="300" height="200" align="middle" allowScriptAccess="always" allowFullscreen="true" type="application/x-shockwave-flash"></embed>
					</div>
				</div>
				<div class="videoDiv">
					<div class="title"><span>1号车倒库</span></div>
					<div class="video">
					<embed src="<%=path%>/js/ckplayer/ckplayer.swf" flashvars="f=/image/5/2.flv&p=0" quality="high" width="300" height="200" align="middle" allowScriptAccess="always" allowFullscreen="true" type="application/x-shockwave-flash"></embed>
					</div>
				</div>
				<div class="videoDiv">
					<div class="title"><span>1号车模拟考试</span></div>
					<div class="video">
					<embed src="<%=path%>/js/ckplayer/ckplayer.swf" flashvars="f=/image/5/3.flv&p=0" quality="high" width="300" height="200" align="middle" allowScriptAccess="always" allowFullscreen="true" type="application/x-shockwave-flash"></embed>
					</div>
				</div>
				<div class="videoDiv">
					<div class="title"><span>1号车坡道起步	</span></div>
					<div class="video">
					<embed src="<%=path%>/js/ckplayer/ckplayer.swf" flashvars="f=/image/5/4.flv&p=0" quality="high" width="300" height="200" align="middle" allowScriptAccess="always" allowFullscreen="true" type="application/x-shockwave-flash"></embed>
					</div>
				</div>
				<div class="videoDiv">
					<div class="title"><span>1号车曲线行驶</span></div>
					<div class="video">
					<embed src="<%=path%>/js/ckplayer/ckplayer.swf" flashvars="f=/image/5/5.flv&p=0" quality="high" width="300" height="200" align="middle" allowScriptAccess="always" allowFullscreen="true" type="application/x-shockwave-flash"></embed>
					</div>
				</div>
				<div class="videoDiv">
					<div class="title"><span>1号车直角转弯</span></div>
					<div class="video">
					<embed src="<%=path%>/js/ckplayer/ckplayer.swf" flashvars="f=/image/5/6.flv&p=0" quality="high" width="300" height="200" align="middle" allowScriptAccess="always" allowFullscreen="true" type="application/x-shockwave-flash"></embed>
					</div>
				</div>
			</div>
	 </div>
	 
</body>
</html>