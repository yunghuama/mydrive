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
<script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
.text {
  padding: 2px 3px;
  background-color: #FFFFFF;
  border: 1px solid #A6B2CA;
  height: 17px;
  line-height: 17px;
  width: 142px;
}
#search {
	text-align:left;
	margin:0px auto;
	width:600px;
	margin-bottom:10px;
}
#exportAll {
	margin-left:100px;
	color:red;
	font-size:18px;
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
	//过滤
	$("#searchButton").click(function(){
		$("#sDate").val($("#sDate1").val());
		$("#eDate").val($("#eDate1").val());
		$("form").submit();
	});
	//导出
	$("#exportButton").click(function(){
		$("form").attr("action","<%=path %>/exam/system/exportActive.d");
		$("form").submit();
		$("form").attr("action","<%=path %>/exam/system/listActiveCard.d");
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
			<div id="title"><span>用户激活次数统计</span></div>
			<div id="sectionContent">
				<div id="search"><span>起始日期:</span><s:textfield  id="sDate1" name="stDate" theme="simple" cssClass="text" onClick="WdatePicker()"></s:textfield>&nbsp;&nbsp;&nbsp;<span>结束日期:</span><s:textfield  id="eDate1" theme="simple" name="enDate" cssClass="text" onClick="WdatePicker()"></s:textfield><button id="searchButton">查询</button><button id="exportButton">导出</button></div>
				<table>
					<tr>
						<th width="10%">序号</th>
						<th>激活日期</th>
						<th>激活次数</th>
					</tr>
					<s:if test="page.list!=null&&page.list.size>0">
					<s:iterator id="ll" value="page.list" status="i">
					<tr>
						<td><s:property value="#i.index+1"/> </td>
						<td><s:property value="#ll.date"/> </td>
						<td><s:property value="#ll.counts"/> </td>
					</s:iterator>
					</s:if>
					<s:else>
					<tr><td colspan="9">暂无数据</td></tr>
					</s:else>
				</table>
				
			</div>
			<div id="pageBar">
			<form action="<%=path %>/exam/system/listActiveCard.d" method="post">
			<s:hidden name="stDate" id="sDate"/>
			<s:hidden name="enDate" id="eDate"/>
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