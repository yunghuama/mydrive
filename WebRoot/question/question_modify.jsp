<%@ page language="java" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/form.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=path%>/js/core.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<style type="text/css">
	.hasDel {
		text-decoration:line-through;
	}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("input[type='button']").eq(0).click(function(){
				$("form").submit();
	});
	$("#delFile").click(function(){
		$(".fileName").addClass("hasDel");
		$("#isDelImage").val(1);
	});
});

function loadCategory(type){
	$.ajax({
		   type: "POST",
		   url: '<%=path%>/system/ajax/getCategory.d',
		   data: {'type':type},
		   async: false,
		   success: function(data){
			   var array = [];
		   		for(var i=0;i<data.length;i++){
		   			var section = data[i];
		   			array.push("<option value="+section.id+">"+section.name+"</option>");
		   		}
		   		$("select[name='question.category']").empty().append(array.join(""));
		   }
		});
}
</script>
</head>
<body style="overflow:auto">
	 <div id="main">
			<div id="title"><span>修改问题</span></div>
			<div id="sectionContent">
				<form action="<%=path%>/exam/subject1/updateQuestion.d" method="post" enctype="multipart/form-data">
				<s:hidden name="type"/>
				<s:hidden name="question.id"/>
				<s:hidden name="isDelImage" id="isDelImage"/>
				章节编号:<s:textfield name="question.code"/> <br/>
				问题:<s:textfield name="question.question"/><br/>
				答案a:<s:textfield name="question.answer_a"/><br/>
				答案b:<s:textfield name="question.answer_b"/><br/>
				答案c:<s:textfield name="question.answer_c"/><br/>
				答案d:<s:textfield name="question.answer_d"/><br/>
				正确答案:<s:textfield name="question.answer"/><br/>
				所属题库:<s:select name="type" disabled="true" list="#{'0':'小汽车','1':'大客车','2':'大货车','3':'摩托车','4':'科目三' }"></s:select> 
				<br/>
				当前附件:<s:if test="question.image!=null">
							<span class="fileName"><s:property value="question.image"/></span>
							<s:hidden name="question.image"/>
						</s:if>
						<s:elseif test="question.video!=null">
							<span class="fileName"><s:property value="question.video"/></span>
							<s:hidden name="question.video"/>
						</s:elseif>
						<a href="javascript:void(0);" id="delFile">删除附件</a>
						<br/>
				附件类型:<input type="radio" name="fileType" value="0">图片&nbsp;&nbsp;<input type="radio" name="fileType" value="1">视频
				附件:<input type="file" name="upload"><br/>
				所属章节:<s:select list="sectionList" listKey="id" listValue="name" name="question.category"></s:select>
				
				<br/>
				提示:<s:textfield name="question.tips"/><br/>
				<input type="button" value="保存">
				</form>
			</div>
	 </div>
	 
</body>
</html>