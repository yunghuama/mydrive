<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/workspace.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//载入科目一统计
		$.ajax({
		   type: "POST",
		   url: "<%=path%>/system/ajax/statisticsSub1.d",
		   success: function(msg){
		     if(msg!=null&&msg.scorecounts>0){
		    	 var array = [];
		    	 array.push('<tr align="center">');
		    	 array.push("<td>"+msg.maxscore+"</td>");
		    	 array.push("<td>"+msg.minscore+"</td>");
		    	 array.push("<td>"+msg.scorecounts+"</td>");
		    	 array.push("<td>"+msg.avgscore+"</td>");
		    	 array.push("<td>"+msg.passcount+"</td>");
		    	 var passcount = parseInt(msg.passcount);
		    	 var scorecounts = parseInt(msg.scorecounts);
		    	 if(passcount==0)
		    	 array.push("<td>"+"0%"+"</td>");
		    	 else if(passcount>0)
		         array.push("<td>"+(parseInt((passcount/scorecounts)*100))+"%"+"</td>");
		    	 array.push("</tr>")
		    	 $(array.join("")).appendTo($("#sub1 .statistic .contents table"));
		     }else {
		    	 $("<tr><td colspan=6 align=center>暂无相关数据</td></tr>").appendTo($("#sub1 .statistic .contents table"));
		     }
		   }
		});
		//载入科目一成绩
		$.ajax({
		   type: "POST",
		   url: "<%=path%>/system/ajax/scoreListSub1.d",
		   success: function(msg){
		    if(msg!=null&&msg.length>0){
		    	 var array = [];
		    	for(var i=0;i<msg.length;i++){
		    		array.push('<tr align="center">');
			    	 array.push("<td>"+(i+1)+"</td>");
			    	 array.push("<td>"+msg[i].score+"</td>");
			    	 array.push("<td>"+msg[i].time+"</td>");
			    	 array.push("<td>"+msg[i].createtime+"</td>");
			    	 array.push("</tr>");
		    	}
		    	 $(array.join("")).appendTo($("#sub1 .score .contents table"));
		    }else {
		    	$("<tr><td colspan=4 align=center>暂无相关数据</td></tr>").appendTo($("#sub1 .score .contents table"));
		    }
		   }
		});
		//载入科目二统计
		$.ajax({
		   type: "POST",
		   url: "<%=path%>/system/ajax/statisticsSub3.d",
		   success: function(msg){
			   if(msg!=null&&msg.scorecounts>0){
			    	 var array = [];
			    	 array.push('<tr align="center">');
			    	 array.push("<td>"+msg.maxscore+"</td>");
			    	 array.push("<td>"+msg.minscore+"</td>");
			    	 array.push("<td>"+msg.scorecounts+"</td>");
			    	 array.push("<td>"+msg.avgscore+"</td>");
			    	 array.push("<td>"+msg.passcount+"</td>");
			    	 var passcount = parseInt(msg.passcount);
			    	 var scorecounts = parseInt(msg.scorecounts);
			    	 if(passcount==0)
			    	 array.push("<td>"+"0%"+"</td>");
			    	 else if(passcount>0)
			         array.push("<td>"+(parseInt((passcount/scorecounts)*100))+"%"+"</td>");
			    	 array.push("</tr>")
			    	 $(array.join("")).appendTo($("#sub2 .statistic .contents table"));
			     }else {
			    	 $("<tr><td colspan=6 align=center>暂无相关数据</td></tr>").appendTo($("#sub2 .statistic .contents table"));
			     }
		   }
		});
		//载入科目二成绩
		$.ajax({
		   type: "POST",
		   url: "<%=path%>/system/ajax/scoreListSub3.d",
		   success: function(msg){
			   if(msg!=null&&msg.length>0){
			    	 var array = [];
			    	for(var i=0;i<msg.length;i++){
			    		array.push('<tr align="center">');
				    	 array.push("<td>"+(i+1)+"</td>");
				    	 array.push("<td>"+msg[i].score+"</td>");
				    	 array.push("<td>"+msg[i].time+"</td>");
				    	 array.push("<td>"+msg[i].createtime+"</td>");
				    	 array.push("</tr>");
			    	}
			    	 $(array.join("")).appendTo($("#sub2 .score .contents table"));
			    }else {
			    	$("<tr><td colspan=4 align=center>暂无相关数据</td></tr>").appendTo($("#sub2 .score .contents table"));
			    }
		   }
		});
	});

</script>
<title></title>
</head>
<body style="overflow-y:auto">
		<div id="main">
			<div id="sub1">
			<div class="statistic">
			<div class="title"><img alt="" src="<%=path%>/image/blue.png"> <span>科目一考试统计</span></div>
			<div class="contents">
				<table align="center">
						<tr>
							<th>最高分</th>
							<th>最低分</th>
							<th>考试次数</th>
							<th>平均成绩</th>
							<th>通过次数</th>
							<th>通过率</th>
						</tr>
				</table>
			</div>
			</div>
			<div class="score">
			<div class="title"><img alt="" src="<%=path%>/image/blue.png"><span>科目一考试成绩</span></div>
			<div class="contents">
				<table>
						<tr>
							<th>序号</th>
							<th>分数</th>
							<th>考试耗时</th>
							<th>考试时间</th>
						</tr>
				</table>
			</div>
			</div>
			</div>
			<div id="sub2">
			<div class="statistic">
			<div class="title"><img alt="" src="<%=path%>/image/blue.png"><span>科目三考试统计</span></div>
			<div class="contents">
			<table>
						<tr>
							<th>最高分</th>
							<th>最低分</th>
							<th>考试次数</th>
							<th>平均成绩</th>
							<th>通过次数</th>
							<th>通过率</th>
						</tr>
				</table>
			</div>
			</div>
			<div class="score">
			<div class="title"><img alt="" src="<%=path%>/image/blue.png"><span>科目三考试成绩</span></div>
			<div class="contents">
			<table>
						<tr>
							<th>序号</th>
							<th>分数</th>
							<th>考试耗时</th>
							<th>考试时间</th>
						</tr>
				</table>
			</div>
			</div>
			</div>
		</div>
</body>
</html>