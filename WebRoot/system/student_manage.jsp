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
#infoContent{
    width:550px;
    height:340px;
    margin:0px auto;
    margin-top:70px;
    position:relative;
}
fieldset {
    border : 1px solid #bfbfbf;
    background-color:#FFFFFF;
    padding:10px 0;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
    //载入
    $.ajax({
        type: "POST",
        url: "<%=path%>/system/ajax/getSchoolList.d",
        async: true,
        success: function(data){
           if(data!=null){
           var option = [];
           for(var i=0;i<data.length;i++){
               option.push("<option value="+data[i].id+">"+data[i].name+"</option>");
           }
           $("#school").append(option.join(""));
           $("#school1").append(option.join(""));
        }
        },
        error : function(){

        }
    });
	$("#queryNumber").click(function(){
          var number = $("#number").val();
          if(number==""||number.length!=7){
              alert("卡号为7位!");
              return;
          }
        //查询
        $.ajax({
            type: "POST",
            url: "<%=path%>/system/ajax/getStudentCardByNumber.d",
            data: {"studentVo.number":number},
            async: true,
            success: function(data){
                 $("#remindDays").val(data.remindDays);
                 $("#remindTimes").val(data.remindTimes);
                 $("#school").find("option[value="+data.schoolId+"]").attr("selected","selected");
            },
            error : function(){

            }
        });
    });


    $("#modify1").click(function(){
        var number = $("#number").val();
        var remindDays = $("#remindDays").val();
        var remindTimes = $("#remindTimes").val();
        var schooId =  $("#school").val();
        if(!confirm("确定要修改吗?")){
            return;
        }
        $.ajax({
            type: "POST",
            url: "<%=path%>/system/ajax/updateStudentCard.d",
            data: {"studentVo.number":number,"studentVo.remindDays":remindDays,"studentVo.remindTimes":remindTimes,"studentVo.schoolId":schooId},
            async: true,
            success: function(data){
                alert(data);
            },
            error : function(){

            }
        });
    });

    $("#modify2").click(function(){
        var fnumber = $("#startNumber").val();
        var tnumber = $("#endNumber").val();
        var schooId =  $("#school1").val();
        if(fnumber==""||fnumber.length!=7){
            alert("卡号为7位");
            return;
        }
        if(fnumber==""||tnumber.length!=7){
            alert("卡号为7位");
            return;
        }
        if(!confirm("确定要修改吗?")){
            return;
        }
        $.ajax({
            type: "POST",
            url: "<%=path%>/system/ajax/updateStudentCardByNumbers.d",
            data: {"fnumber":fnumber,"tnumber":tnumber,"schoolId":schooId},
            async: true,
            success: function(data){
                alert(data);
            },
            error : function(){

            }
        });
    });
});
</script>
</head>
<body style="overflow:auto">
	 <div id="main">
         <div id="infoContent">
             <fieldset>
                 <legend>单张卡片管理</legend>
                 <form name="infoForm" id="infoForm"  class="form" method="post">

                     <table class="form-table" cellspacing="0" cellpadding="0">
                         <tr>
                             <td class="form-left"><span class="form-required">*</span>卡号</td>
                             <td class="form-right"><input type="text" id="number"  class="text"/>
                                <input type="button" id="queryNumber" value="查询" style="margin-left: 10px;">
                             </td>
                         </tr>
                         <tr>
                             <td class="form-left"><span class="form-required">*</span>剩余天数</td>
                             <td class="form-right"><input type="text" id="remindDays"  class="text"/></td>
                         </tr>
                         <tr>
                             <td class="form-left"><span class="form-required">*</span>剩余次数</td>
                             <td class="form-right"><input type="text" id="remindTimes"  class="text"/></td>
                         </tr>
                         <tr>
                             <td class="form-left"><span class="form-required">*</span>所属驾校</td>
                             <td class="form-right">
                                 <select id="school" class="select"></select><input type="button" id="modify1" style="margin-left: 10px;" value="修改">
                             </td>
                         </tr>
                     </table>
                 </form>
             </fieldset>
             <fieldset style="margin-top:30px;">
                 <legend>卡片集中管理</legend>
                 <form name="infoForm" id="infoForm1" action="<%=path%>/system/users/firstInit.d" class="form" method="post">

                     <table class="form-table" cellspacing="0" cellpadding="0">
                         <tr>
                             <td class="form-left"><span class="form-required">*</span>起始卡号</td>
                             <td class="form-right"><input type="text" id="startNumber"  class="text"/></td>
                         </tr>
                         <tr>
                             <td class="form-left"><span class="form-required">*</span>截止卡号</td>
                             <td class="form-right"><input type="text" id="endNumber"  class="text"/></td>
                         </tr>
                         <tr>
                             <td class="form-left"><span class="form-required">*</span>所属驾校</td>
                             <td class="form-right"><select id="school1" class="select"></select><input type="button"  style="margin-left: 10px;" id="modify2" value="修改"></td>
                         </tr>
                     </table>
                 </form>
             </fieldset>
         </div>
	</div>
</body>
</html>