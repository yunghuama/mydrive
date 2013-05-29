<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/css/core.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/form.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#next").click(function(){
			if($("#name").val()==""||$("#name").val().length>10){
				alert("请填写正确姓名");
				return;
			}
			if(!IdentityCodeValid($("#identity").val())){
				alert("请填写正确的身份证号");
				return;
			}
			if($("#age").val()!=""){
			if($("#age").val()<10||$("#age").val()>60){
				alert("请输入正确的年龄");
				return;
			}
			}
			$("#infoForm").submit();
		});
	});
	function IdentityCodeValid(code) { 
        var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
        var tip = "";
        var pass= true;
        
        if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
            tip = "身份证号格式错误";
            pass = false;
        }
        
       else if(!city[code.substr(0,2)]){
            tip = "地址编码错误";
            pass = false;
        }
        else{
            //18位身份证需要验证最后一位校验位
            if(code.length == 18){
                code = code.split('');
                //∑(ai×Wi)(mod 11)
                //加权因子
                var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                //校验位
                var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                var sum = 0;
                var ai = 0;
                var wi = 0;
                for (var i = 0; i < 17; i++)
                {
                    ai = code[i];
                    wi = factor[i];
                    sum += ai * wi;
                }
                var last = parity[sum % 11];
                if(parity[sum % 11] != code[17]){
                    tip = "校验位错误";
                    pass =false;
                }
            }
        }
        return pass;
    }
</script>
<title></title>
<style type="text/css">
#head {
	position:relative;
	width:100%;
	height:50px;
	background-color:#2d89ef;
	margin:0px auto;
	text-align:center;
	padding-top:15px;
}
#infoContent{
	width:500px;
	height:300px;
	margin:0px auto;
	margin-top:70px;
	position:relative;
}
fieldset {
	border : 1px solid #bfbfbf;
	background-color:#FFFFFF;
	padding:10px 0;
}
#infoForm {
	width:500px;
	height:250px;
}
#barDiv{
	text-align:center;
}

.text {
	width:250px;
}
.select {
	width :257	px;
}
td {
	height:40px;
}
</style>
</head>
<body>
	<div id="infoContent">
	<fieldset>
	<legend>个人资料</legend>
	<form name="infoForm" id="infoForm" action="<%=path%>/exam/system/updateUsers.d" class="form" method="post">
	<s:hidden name="users.id"></s:hidden>
      <table class="form-table" cellspacing="0" cellpadding="0">
        <tr>
          <td class="form-left"><span class="form-required">*</span>真实姓名</td>
          <td class="form-right"><s:textfield id="name" name="users.name" theme="simple" cssClass="text"/> </td>
        </tr>
        <tr>
          <td class="form-left"><span class="form-required">*</span>身份证</td>
          <td class="form-right"><s:textfield id="identity" name="users.identity" theme="simple" cssClass="text"/></td>
        </tr>
        <tr>
          <td class="form-left">年龄</td>
          <td class="form-right"><s:textfield id="age" name="users.age" theme="simple" cssClass="text"/></td>
        </tr>
        <tr>
          <td class="form-left">性别</td>
          <td class="form-right">
          		<s:select list="#{'0':'男','1':'女'}" name="users.sex" cssClass="select"></s:select>
          </td>
        </tr>
        <tr>
          <td class="form-left"><span class="form-required">*</span>车型</td>
          <td class="form-right">
          <s:select cssClass="select" name="users.cartype" list="#{'c1':'C1','c2':'C2','c3':'C3','c4':'C4','c5':'C5','a1':'A1','a2':'A2','a3':'A3','b1':'B1','b2':'B2','d':'D','e':'E','f':'F'}"></s:select>
           </td>
        </tr>
        <tr>
          <td class="form-left">手机号</td>
          <td class="form-right"><s:textfield name="users.phonenumber" theme="simple" cssClass="text"/></td>
        </tr>
      </table>
    </form>
    </fieldset>
	</div>
	<div id="barDiv">
			<a id="next" href="javascript:void(0);"><img alt="" src="<%=path%>/image/save.png"> </a>
	</div>
</body>
</html>