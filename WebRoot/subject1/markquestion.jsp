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
<script type="text/javascript" src="<%=path%>/js/picview.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//弹出图片查看框
	$(".showImg").click(function(){
		var src = $(this).attr("src");
		new ImageView({"renderTo":$("body"),"src":src});
	});
	
	$(".showImgA").click(function(){
		var src = $(this).attr("name");
		new ImageView({"renderTo":$("body"),"src":src});
	});
	
	//显示答案
	$(".showQ").click(function(){
		var attr1 = "<%=path%>/image/showquestion.png";
		var attr2 = "<%=path%>/image/hidequestion.png";
		var attr = $(this).attr("src");
		if(attr==attr1){
			$(this).attr("src",attr2);
		}else if(attr==attr2){
			$(this).attr("src",attr1);
		}
		$(this).parent("a").next("span").toggle('fast');
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
		if(confirm("确定要删除该题？")){
		$("form").attr("action","<%=path %>/exam/subject1/delMarkQuestion.d");
		$("#questionId").val($(this).attr("id"));
		$("form").submit();
		}
	});
});
</script>
</head>
<body style="overflow:auto">
	 <div id="main">
			<div id="title"><span>科目一已标记题</span></div>
			<div id="questionDiv">
			 <s:iterator id="question" value="page.list" status="i">
			<div class="question">
			    <div class="quesntinContent">
					<div class="questionTitle"><span><s:property value="#question.id"/>. <s:property value="#question.question"/> </span></div>
					<div class="answera"><span><s:property value="#question.answer_a"/> </span></div>
					<div class="answerb"><span><s:property value="#question.answer_b"/> </span></div>
					<div class="answerc"><span><s:property value="#question.answer_c"/> </span></div>
					<div class="answerd"><span><s:property value="#question.answer_d"/> </span></div>
					<div class="answer"><a href="javascript:void(0);"><img alt="" src="<%=path%>/image/showquestion.png" class="showQ"/></a><span>正确答案：<s:property value="#question.answer"/> </span>
					<s:if test='#question.tips!=""&&#question.tips!=null'>
					<span>提示：<s:property value="#question.tips"/> </span>
					</s:if>
					</div>
					<div class="mark"><a href="javascript:void(0);" id="<s:property value='#question.id'/>"><img alt="" src="<%=path%>/image/del.png"/></a> </div>
				 </div>
				<div class="richmedia">
					<s:if test='#question.image!=""&&#question.image!=null'>
						<img src='/image/<s:property value="#session['LoginBean'].user.questionType"/>/sub1/<s:property value="type"/>/<s:property value="#question.image"/>' class="showImg"/><br/><br/>
						<a name='/image/<s:property value="#session['LoginBean'].user.questionType"/>/sub1/<s:property value="type"/>/<s:property value="#question.image"/>' href="javascript:void(0);" class="showImgA">查看大图</a>
					</s:if>
				</div>
				<div class="clear"></div>
			</div>
			</s:iterator>
			<div id="pageBar">
			<form action="<%=path %>/exam/subject1/markQuestion1.d">
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
	 </div>
	 
</body>
</html>