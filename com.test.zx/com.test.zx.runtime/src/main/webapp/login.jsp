<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>zx'test</title>
<!-- Custom Theme files -->
<!-- <link rel="icon" type="image/x-icon" href="favicon.ico"/> -->
<link href="resources/app/css/loginstyle.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" src="resources/jquery-easyui-1.4/jquery.min.js"></script>
<!-- Custom Theme files -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
</head>
<body>
<div class="login">
	<div class="login-top">
		<div class="title">
			<h1>zx'test</h1>
		</div>
		<iframe frameborder="0" src="" name="longinhiddenIframe" id="longinhiddenIframe" style="width:0;height:0;"></iframe>
		<form id = "mbcform"  action="userSessionAction!login.action" method="post" target=longinhiddenIframe>
<!-- 			<input type="text" name="username" id="username" value="用户帐号" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '用户帐号';}"> -->
<!-- 			<input type="password" name="password" id="password" value="password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'password';}"> -->
			<input type="text" name="username" id="username" placeholder="用户帐号" >
			<input type="password" name="password" id="password" placeholder="password">
		    <div class="forgot">
	 	    	<input type="checkbox" id="rememberMeCheckBox" name = "rememberMeCheckBox"/><a href="#" onclick="changeRemember()">记住密码</a>
	<!-- 	    	<a href="#">忘记密码</a> -->
		    	<input type="submit" id="okBtn" name="okBtn" value="登录" >
		    </div>
	    </form>
	</div>
	<div class="login-bottom">
		powered by zx	
	</div>
</div>	
<!-- <div class="copyright"> -->
<!-- 	<p>Copyright &copy; 2015.PK-TECH All rights reserved.</p> -->
<!-- </div> -->
<script type="text/javascript">
$(function() {
	$("#username").focus().val(getCookie("username"));
	$("#password").val(getCookie("password"));
	$('#rememberMeCheckBox').prop('checked',getCookie("rememberMeCheckBox"));
});

document.onkeydown=function(event){ 
 	e = event ? event :(window.event ? window.event : null); 
     if(e.keyCode==13){ 
     	$("#okBtn").click();
      	return false; 
     } 
} 

function changeRemember(){
	$('#rememberMeCheckBox').prop('checked',!$('#rememberMeCheckBox').prop('checked'));
}

function setCookie(name,value) 
{ 
	var Days = 30; 
	var exp = new Date(); 
	exp.setTime(exp.getTime() + Days*24*60*60*1000); 
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString(); 
} 

function getCookie(objName){//获取指定名称的cookie的值
	var arrStr = document.cookie.split("; ");
	for(var i = 0;i < arrStr.length;i ++){
		var temp = arrStr[i].split("=");
		if(temp[0] == objName) return unescape(temp[1]);
	}
	return null;
}
</script>

</body>
</html>