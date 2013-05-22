<%@ page language="java" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/section.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=path%>/js/core.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("table tr").bind("mouseover",function(){
		$(this).addClass("mouseover");
	}).bind("mouseout",function(){
		$(this).removeClass("mouseover");
	});
	//构造分页
	var maxPage = parseInt($("#maxPage").val());
	var currentPage = parseInt($("#currPage").val());
	var optionArray = [];
	for(var i=1;i<=maxPage;i++){
		if(currentPage==i)
		optionArray.push('<option value="'+i+'" selected="selected">'+i+'</option>');
		else 
		optionArray.push('<option value="'+i+'">'+i+'</option>');
	}
	$("#jump").append(optionArray.join(""));
	$("#jump").bind("change",function(){
		$("#currPage").val($(this).val());
		$("form").submit();
	});
	$("#prev").bind("click",function(){
		$("#currPage").val(parseInt(currentPage-1));
		$("form").submit();
	});
	$("#next").bind("click",function(){
		$("#currPage").val(parseInt(currentPage+1));
		$("form").submit();
	});
});
</script>
</head>
<body style="overflow:auto">
	 <div id="main">
			<div id="title"><span>学员考试成绩列表</span></div>
			<div id="sectionContent">
				<table>
					<tr>
						<th width="10%">序号</th>
						<th>学员名称</th>
						<th>最高分</th>
						<th>最低分</th>
						<th>考试次数</th>
						<th>平均成绩</th>
						<th>通过次数</th>
						<th>通过率</th>
					</tr>
					 <s:iterator id="score" value="page.list" status="i">
					<tr>
						<td><s:property value="#i.index+1"/> </td>
						<td><s:property value="#score.name"/> </td>
						<td><s:property value="#score.maxscore"/> </td>
						<td><s:property value="#score.minscore"/> </td>
						<td><s:property value="#score.scorecounts"/> </td>
						<td><s:property value="#score.avgscore"/> </td>
						<td><s:property value="#score.passcount"/> </td>
						<td><s:property value="#score.rate"/>%</td>
					</tr>
					</s:iterator>
				</table>
			</div>
			<div id="pageBar">
			<form action="<%=path %>/exam/system/listScore3.d">
			<s:hidden name="categoryId"/>
			<s:hidden name="questionId" id="questionId"/>
			<s:hidden id="maxPage" name="page.maxPage"/>
			<s:hidden id="currPage" name="page.currPage"/>
			<button id="prev">上一页</button>
			<select id="jump">
			</select>
			<button id="next">下一页</button>
			</form>
			</div>
	 </div>
	 
</body>
</html>