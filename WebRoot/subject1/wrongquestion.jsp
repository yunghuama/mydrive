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
	$(".answer a").bind("click",function(){
		$(this).siblings("span").show();		
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
	$(".mark a").bind("click",function(){
		if(confirm("确定删除该题目?")){
		$("form").attr("action","<%=path %>/exam/subject1/delWrongQuestion.d");
		$("#questionId").val($(this).attr("id"));
		$("form").submit();
		}
	});
});
</script>
</head>
<body style="overflow:auto">
	 <div id="main">
			<div id="title"><span>科目一错题练习</span></div>
			<div id="sectionContent">
			 <s:iterator id="question" value="page.list" status="i">
			<div class="question">
			    <div class="quesntinContent">
					<div class="questionTitle"><span><s:property value="#question.id"/>. <s:property value="#question.question"/> </span></div>
					<div class="answera"><span><s:property value="#question.answer_a"/> </span></div>
					<div class="answerb"><span><s:property value="#question.answer_b"/> </span></div>
					<div class="answerc"><span><s:property value="#question.answer_c"/> </span></div>
					<div class="answerd"><span><s:property value="#question.answer_d"/> </span></div>
					<div class="answer"><a href="javascript:void(0);"><img alt="" src="<%=path%>/image/showquestion.png"/></a><span>正确答案：<s:property value="#question.answer"/> </span>
					<s:if test='#question.tips!=""&&#question.tips!=null'>
					<span>提示：<s:property value="#question.tips"/> </span>
					</s:if>
					</div>
					<div class="mark"><a href="javascript:void(0);" id="<s:property value='#question.id'/>"><img alt="" src="<%=path%>/image/del.png"/></a> </div>
				 </div>
				<div class="richmedia">
					<s:if test='#question.image!=""&&#question.image!=null'>
						<img src='/image/sub1/<s:property value="type"/>/<s:property value="#question.image"/>'/><br/><br/>
						<a href='/image/sub1/<s:property value="type"/>/<s:property value="#question.image"/>' target="_blank">查看大图</a>
					</s:if>
				</div>
			</div>
			</s:iterator>
			<div id="pageBar">
			<form action="<%=path %>/exam/subject1/listWrongQuestion.d">
			<s:hidden name="questionId"/>
			<s:hidden id="maxPage" name="page.maxPage"/>
			<s:hidden id="currPage" name="page.currPage"/>
			<button id="prev">上一页</button>
			<select id="jump">
			</select>
			<button id="next">下一页</button>
			</form>
			</div>
			</div>
	 </div>
	 
</body>
</html>