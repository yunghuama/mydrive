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
</style>
<script type="text/javascript">
$(document).ready(function(){
    var code = $("#code").val();
	loadCategory(1,code);
	$("select[name='type']").bind("change",function(){
			if($(this).val()==4){
				loadCategory(3,code);
			}else {
				loadCategory(1,code);
			}	
	});
	$("#next").click(function(){
		if($("input[name='question.code']").val()==""){
			alert("章节名称不能为空");
			return;
		}
		if($("input[name='question.question']").val()==""){
			alert("问题不能为空");
			return;
		}
		if($("input[name='question.answer_a']").val()==""){
			alert("答案a不能为空");
			return;
		}
		
		if($("input[name='question.answer_b']").val()==""){
			alert("答案b不能为空");
			return;
		}
		if($("input[name='question.answer']").val()==""){
			alert("正确答案不能为空");
			return;
		}
		if(confirm("确认添加该题目吗?")){
			$("#infoForm").submit();
		}
	});
});

function loadCategory(type,code){
	$.ajax({
		   type: "POST",
		   url: '<%=path%>/system/ajax/getCategory.d',
		   data: {'type':type,'code':code},
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
			<legend>添加题库 </legend>
			<div id="sectionContent">
				<form  name="infoForm" id="infoForm" action="<%=path%>/exam/subject1/saveQuestion.d" method="post" enctype="multipart/form-data">
                <s:hidden id="code" name="code" value="%{#parameters.code}"/>

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
		         		 <select name="type" class="select">
								<option value="0">小汽车</option>
								<option value="1">大客车</option>
								<option value="2">小货车</option>
								<option value="3">摩托车</option>
								<option value="4">科目三</option>
						</select>
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
		         		<select name="question.category" class="select">
										
						</select>
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