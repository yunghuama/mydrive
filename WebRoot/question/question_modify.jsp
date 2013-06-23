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
#infoContent{
	width:600px;
	height:450px;
	margin:0px auto;
	margin-top:20px;
}
fieldset {
	padding:20px;
	border : 1px solid #7d7d7d;
}
#barDiv{
	text-align:center;
}
.text {
	width:300px;
}
.select {
	width:300px;
}
	.hasDel {
		text-decoration:line-through;
	}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#next").click(function(){
				$("form").submit();
	});
	$("#delFile").click(function(){
		$(".fileName").addClass("hasDel");
		$("#img").val("");
		$("#video").val("");
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
	 		<div id="infoContent">
			<fieldset>
			<legend>修改题库</legend>
			<div id="sectionContent">
				<form action="<%=path%>/exam/subject1/updateQuestion.d" method="post" enctype="multipart/form-data">
				<s:hidden name="type"/>
				<s:hidden name="question.id"/>
				<s:hidden name="isDelImage" id="isDelImage"/>
				<s:hidden name="question.image" id="img"/>
				<s:hidden name="question.video" id="video"/>
                <s:hidden name="code"/>
				<table class="form-table" cellspacing="0" cellpadding="0">
		        <tr>
		          <td class="form-left"><span class="form-required">*</span>章节编号</td>
		          <td class="form-right"><s:textfield name="question.code" theme="simple" cssClass="text"/> </td>
		        </tr>
		        <tr>
		          <td class="form-left"><span class="form-required">*</span>问题</td>
		          <td class="form-right"><s:textfield name="question.question" theme="simple" cssClass="text"/></td>
		        </tr>
		        <tr>
		          <td class="form-left"><span class="form-required">*</span>答案a</td>
		          <td class="form-right"><s:textfield name="question.answer_a" theme="simple" cssClass="text"/></td>
		        </tr>
		        <tr>
		          <td class="form-left"><span class="form-required">*</span>答案b</td>
		          <td class="form-right"><s:textfield name="question.answer_b" theme="simple" cssClass="text"/></td>
		        </tr>
		        <tr>
		          <td class="form-left">答案c</td>
		          <td class="form-right"><s:textfield name="question.answer_c" theme="simple" cssClass="text"/></td>
		        </tr>
		        <tr>
		          <td class="form-left">答案d</td>
		          <td class="form-right"><s:textfield name="question.answer_d" theme="simple" cssClass="text"/></td>
		        </tr>
		        <tr>
		          <td class="form-left"><span class="form-required">*</span>正确答案</td>
		          <td class="form-right"><s:textfield name="question.answer" theme="simple" cssClass="text"/></td>
		        </tr>
		        <tr>
		          <td class="form-left"></span>题库类型</td>
		          <td class="form-right">
		         		 <s:select name="type" disabled="true" list="#{'0':'小汽车','1':'大客车','2':'大货车','3':'摩托车','4':'科目三' }"></s:select> 
		          </td>
		        </tr>
		        <tr>
		          <td class="form-left"><span class="form-required">*</span>当前附件</td>
		          <td class="form-right">
		         		<s:if test="question.image!=null&&question.image!=''">
							<span class="fileName"><s:property value="question.image"/></span>
							&nbsp;&nbsp;<a href="javascript:void(0);" id="delFile">删除附件</a>
						</s:if>
						<s:elseif test="question.video!=null&&question.video!=''">
							<span class="fileName"><s:property value="question.video"/></span>
							&nbsp;&nbsp;<a href="javascript:void(0);" id="delFile">删除附件</a>
						</s:elseif>
						
		          </td>
		        </tr>
		         <tr>
		          <td class="form-left">附件类型</td>
		          <td class="form-right"><input type="radio" class="radio" name="fileType" value="0">图片&nbsp;&nbsp;<input type="radio" class="radio" name="fileType" value="1">视频</td>
		        </tr>
		        <tr>
		          <td class="form-left">附件</td>
		          <td class="form-right"><input type="file" name="upload" class="file"></td>
		        </tr>
		        <tr>
		          <td class="form-left"><span class="form-required">*</span>所属章节</td>
		          <td class="form-right">
		         		<s:select list="sectionList" listKey="id" listValue="name" name="question.category"></s:select>
		           </td>
		        </tr>
		        <tr>
		          <td class="form-left">答题提示</td>
		          <td class="form-right"><s:textfield name="question.tips" theme="simple" cssClass="text"/></td>
		        </tr>
     			 </table>
				</form>
			</div>
			</fieldset>
	 </div>
	 <div id="barDiv">
			<a id="next" href="javascript:void(0);"><img alt="" src="<%=path%>/image/save.png"> </a>
	</div>
</body>
</html>