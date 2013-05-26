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
.addDiv {
	width:800px;
	margin:0px auto;
	text-align:left;
}

.addDiv img {
		width:84px;
		height:33px;
	}
	#sectionContent table {
		width:800px;
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
	//添加新题
	$(".addDiv img").click(function(){
		$("form").attr("action","<%=path%>/adver/ad_save.jsp");
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
			<div id="title"><span>广告管理</span></div>
			<div id="sectionContent">
			<div class="addDiv"><a href="javascript:void(0);"><img src="<%=path%>/image/add.png"/></a></div>
				<table>
					<tr>
						<th width="10%">序号</th>
						<th>名称</th>
						<th>链接地址</th>
						<th>图片名称</th>
						<th>页面</th>
						<th>位置</th>
						<th>轮换时间</th>
						<th>创建时间</th>
						<th  width="10%">操作</th>
					</tr>
					<s:if test="page.list!=null&&page.list.size>0">
					<s:iterator id="adver" value="page.list" status="i">
					<tr>
						<td><s:property value="#i.index+1"/> </td>
						<td><s:property value="#adver.name"/> </td>
						<td><s:property value="#adver.url"/> </td>
						<td><s:property value="#adver.image"/> </td>
						<td><s:property value='@com.platform.constants.StringConstant@getAdvPage(#adver.page)'/> </td>
						<td><s:property value='@com.platform.constants.StringConstant@getPosition(#adver.position)'/> </td>
						<td><s:property value="#adver.time"/> </td>
						<td><s:property value="#adver.createTime"/> </td>
						<td><a href='<%=path%>/system/adver/delete.d?adver.id=<s:property value="#adver.id"/>'>删除</a> </td>
					</tr>
					</s:iterator>
					</s:if>
					<s:else>
						<tr><td colspan="9">暂无数据</td></tr>
					</s:else>
				</table>
			
			</div>
			<div id="pageBar">
			<form action="<%=path %>/system/adver/list.d">
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