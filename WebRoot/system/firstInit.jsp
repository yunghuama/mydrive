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
<title>首次登录，完善个人信息</title>
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

span {
	font-size:20px;
	color:#FFFFFF;
	height:20px;
	line-height:20px;
}

form span {
	color:#000000;
}
#content{
	width:300px;
	height:300px;
	margin:0px auto;
	margin-top:100px;
}
fieldset {
	padding:20px;
}
#barDiv{
	text-align:center;
}
</style>
</head>
<body>
		<div id="head">
				<span>首次登录，请完善以下个人信息</span>
		</div>
		
		
	<div id="content">
	<fieldset>
	<legend>个人资料</legend>
	<form name="infoForm" id="infoForm" action="<%=path%>/system/users/firstInit.d" class="form" method="post">
	<s:hidden name="users.id"></s:hidden>
      <table class="form-table" cellspacing="0" cellpadding="0">
        <tr>
          <td class="form-left"><span class="form-required">*</span>真实姓名</td>
          <td class="form-right"><input type="text" id="name" name="users.name" class="text"/></td>
        </tr>
        <tr>
          <td class="form-left"><span class="form-required">*</span>身份证</td>
          <td class="form-right"><input type="text" id="identity" name="users.identity" class="text"/></td>
        </tr>
        <tr>
          <td class="form-left">年龄</td>
          <td class="form-right"><input type="text" id="age" name="users.age" class="text"/></td>
        </tr>
        <tr>
          <td class="form-left"></span>性别</td>
          <td class="form-right">
          		<select  name="users.sex">
          			<option value="0">男</option>
					<option value="1">女</option>
          		</select>
          </td>
        </tr>
        <tr>
          <td class="form-left"><span class="form-required">*</span>车型</td>
          <td class="form-right"><select name="users.cartype">
								<option value="c1">C1</option>
								<option value="c2">C2</option>
								<option value="c3">C3</option>
								<option value="c4">C4</option>
								<option value="c5">C5</option>
								<option value="a1">A1</option>
								<option value="a2">A2</option>
								<option value="a3">A3</option>
								<option value="b1">B1</option>
								<option value="b2">B2</option>
								<option value="d">D</option>
								<option value="e">E</option>
								<option value="f">F</option>
							  </select> </td>
        </tr>
        <tr>
          <td class="form-left">手机号</td>
          <td class="form-right"><input type="text" id="number" name="users.phonenumber" class="text"/></td>
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