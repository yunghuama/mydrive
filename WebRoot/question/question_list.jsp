<%@ page language="java" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/question.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=path%>/js/core.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/ckplayer/ckplayer.js" charset="utf-8"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(".answer a").bind("click",function(){
		$(this).siblings("span").show();		
	});
    //切换地区
    $("#code").bind("change",function(){
        $("input[name='code']").val($(this).val());
        $("form").submit();
    });
	//过滤条件
	$(".type").click(function(){
		$(".type").removeClass("click");
		$(this).addClass("click");
		var type  = $(this).attr("id");
		$("input[name='type']").val(type);
		$("#currPage").val(1);
		$("form").submit();
	});
	$("#<s:property value='type'/>").addClass("click");
	//添加新题
	$(".addDiv img").click(function(){
		$("form").attr("action","<%=path%>/question/question_save.jsp");
		$("form").submit();
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
	})
	$(".mark a").bind("click",function(){
		var id = $(this).attr("id");
		$.ajax({
		type: "POST",
		url: "<%=path%>/system/ajax/saveMarkQuestion.d",
		data: "questionId="+id,
		success: function(msg){
		    	if(msg=='success'){
		    		alert("标记成功");
		    	}
		   }
		});
	});
});
</script>
</head>
<body style="overflow:auto">
	 <div id="main">
			<div id="type">
                <s:select list="questionTableList" listKey="name" listValue="title" name="code" id="code">

                </s:select>
				<div class="type" id="0">
					<span>小汽车</span>
				</div>
				<div class="type" id="1">
					<span>大客车</span>
				</div>
				<div class="type" id="2">
					<span>大货车</span>
				</div>
				<div class="type" id="3">
					<span>摩托车</span>
				</div>
				<div style="clear:both"></div>
				<div class="type" id="4">
					<span>科三(非摩托)</span>
				</div>
				<div class="type" id="6">
					<span>科三(摩托)</span>
				</div>
			</div>
			<div id="questionDiv">
			<div class="addDiv"><a href="javascript:void(0);"><img src="<%=path%>/image/add.png"/></a></div>
			<s:iterator id="question" value="page.list" status="i">
			<div class="question">
			    <div class="quesntinContent">
					<div class="questionTitle"><span><s:property value="#question.id"/>. <s:property value="#question.question"/> </span></div>
					<div class="answera"><span><s:property value="#question.answer_a"/> </span></div>
					<div class="answerb"><span><s:property value="#question.answer_b"/> </span></div>
					<div class="answerc"><span><s:property value="#question.answer_c"/> </span></div>
					<div class="answerd"><span><s:property value="#question.answer_d"/> </span></div>
					<div class="answer"><span>正确答案：<s:property value="#question.answer"/> </span></div>
					<div class="answer"><span>提示：<s:property value="#question.tips"/></span></div>
					<div class="answer"><span>所属章节：<s:property value="#question.category"/> </span></div>
				 </div>
				<div id="right">
				<div class="richmedia">
					<s:if test='#question.image!=""&&#question.image!=null'>
						<s:if test="type!=4">
						<img src='/image/<s:property value='code'/>/sub1/<s:property value="type"/>/<s:property value="#question.image"/>'/><br/><br/>
						<a href='/image/<s:property value='code'/>/sub1/<s:property value="type"/>/<s:property value="#question.image"/>' target="_blank">查看大图</a>
						</s:if>
						<s:elseif test="type==4">
						<img src='/image/<s:property value='code'/>/sub3/<s:property value="#question.image"/>'/><br/><br/>
						<a href='/image/<s:property value='code'/>/sub3/<s:property value="#question.image"/>' target="_blank">查看大图</a>
						</s:elseif>
					</s:if>
					<s:elseif test='#question.video!=""&&#question.video!=null'>
						<s:if test="type==4">
						<embed src="<%=path%>/js/ckplayer/ckplayer.swf" flashvars="f=/image/<s:property value='code'/>/sub3/<s:property value='#question.video'/>&p=0" quality="high" width="300" height="150" align="middle" allowScriptAccess="always" allowFullscreen="true" type="application/x-shockwave-flash"></embed>
						</s:if>
					</s:elseif>
				</div>
				<div class="operate">
				<a href="<%=path %>/exam/subject1/toUpdateQuestion.d?questionId=<s:property value='#question.id'/>&type=<s:property value='type'/>&code=<s:property value='code'/>"> <img alt="" src="<%=path%>/image/modifyquestion.png"></a><br/>
				<a href="<%=path %>/exam/subject1/deleteQuestion.d?questionId=<s:property value='#question.id'/>&type=<s:property value='type'/>&code=<s:property value='code'/>"><img alt="" src="<%=path%>/image/del.png"> </a><br/>
				</div>
				</div>
				<div class="clear"></div>
			</div>
			</s:iterator>
			<div id="pageBar">
			<form action="<%=path %>/exam/subject1/listQuestion.d">
			<s:hidden name="type"/>
            <s:hidden name="code"/>
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