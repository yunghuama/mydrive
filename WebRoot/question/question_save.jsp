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
<script type="text/javascript">
$(document).ready(function(){
	loadCategory(1);
	$("select[name='type']").bind("change",function(){
			if($(this).val()==4){
				loadCategory(3);
			}else {
				loadCategory(1);
			}	
	});
	
	$("input[type='button']").eq(0).click(function(){
				$("form").submit();
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
			<div id="title"><span>添加新问题</span></div>
			<div id="sectionContent">
				<form action="<%=path%>/exam/subject1/saveQuestion.d" method="post" enctype="multipart/form-data">
				章节编号:<input type="text" name="question.code"><br/>
				问题:<input type="text" name="question.question"><br/>
				答案a:<input type="text" name="question.answer_a"><br/>
				答案b:<input type="text" name="question.answer_b"><br/>
				答案c:<input type="text" name="question.answer_c"><br/>
				答案d:<input type="text" name="question.answer_d"><br/>
				正确答案:<input type="text" name="question.answer"><br/>
				所属题库:<select name="type">
						<option value="0">小汽车</option>
						<option value="1">大客车</option>
						<option value="2">小货车</option>
						<option value="3">摩托车</option>
						<option value="4">科目三</option>
				</select> <br/>
				附件类型:<input type="radio" name="fileType" value="0">图片&nbsp;&nbsp;<input type="radio" name="fileType" value="1">视频
				附件:<input type="file" name="upload"><br/>
				所属章节:<select name="question.category">
								
				</select> <br/>
				提示:<input type="text" name="question.tips"><br/>
				<input type="button" value="保存">
				</form>
			</div>
	 </div>
	 
</body>
</html>