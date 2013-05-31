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
<style type="text/css">
	#addDiv {
		text-align:left;
	}
	#addDiv img {
		width:84px;
		height:33px;
	}
	#sectionContent {
		width:600px;
		margin:0px auto;
	}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("table tr").bind("mouseover",function(){
		if($("th",this).size()>0){
			return;
		}
		$(this).addClass("mouseover");
	}).bind("mouseout",function(){
		$(this).removeClass("mouseover");
	});
	
	$(".data").bind("click",function(){
		var id = $(this).attr("id");
		$("#annId").val(id);
		$("form").attr("action","<%=path%>/exam/system/getMessageById.d");
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
	});
});
</script>
</head>
<body style="overflow:auto">
	 <div id="main">
			<div id="title"><span>意见反馈</span></div>
			<div id="sectionContent">
			<div id="addDiv">
			<a href="<%=path%>/message/msg_save.jsp"><img alt="" src="<%=path%>/image/add.png"> </a>
			</div>
				<table>
					<tr>
						<th width="10%">序号</th>
						<th>标题</th>
						<th>时间</th>
						<th>操作</th>
					</tr>
					<s:if test="page.list!=null&&page.list.size>0">
					 <s:iterator id="msg" value="page.list" status="i">
					<tr class="data" id=<s:property value="#ann.id"/>>
						<td><s:property value="#i.index+1"/> </td>
						<td><s:property value="#msg.title"/> </td>
						<td><s:property value="#msg.createTime"/> </td>
						<td><a href="javascript:void(0);" id='<s:property value="#msg.id"/>'><img alt="" src="<%=path%>/image/dd.png"></a> </td>
					</tr>
					</s:iterator>
					</s:if>
					<s:else>
					<tr><td colspan="2">暂无数据</td></tr>
					</s:else>
				</table>
			</div>
			<div id="pageBar">
			<form action="<%=path %>/exam/system/listMessageStu.d">
			<s:hidden name="annId" id="annId"/>
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