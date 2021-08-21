<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form</title>
<style type="text/css">
.box2{position:relative; width:500px; height:50px; margin:0 auto; text-align:center;
		line-height:50px; }
.temp{position:relative; width:500px; height:50px; margin:0 auto;}
.attr1{position:relative; width:248px; height:48px; float:left; background:yellowgreen; 
		font-size:110%; color:white;text-align:center;	line-height:50px;
		font-weight:bold; border:1px solid green;}
.attr2{position:relative; width:248px; height:48px; float:left; border:1px solid green; 
		font-size:110%; text-align:left;	line-height:50px;}
#footer{position:relative;width:500px;height:50px;text-align:center;	line-height:50px;}
</style>
</head>
<body>
<form action="login" method="post">
	<div class="box2"><div id="title">로그인</div></div>
	<div class="box2"><div class="attr1">아이디</div>
		<div class="attr2">&nbsp;&nbsp;<input type="text" size="20" name="id"  
				style="width:200px;height:20px;"></div>
	</div>
	<div class="box2">
		<div class="attr1">비밀번호</div>
		<div class="attr2">&nbsp;&nbsp;<input type="password" size="20" name="pw"  
				style="width:200px;height:20px;"></div>
	</div>
	<div class="box2">
		<div id="footer"><input type="submit" value="로그인" />
			<input type="reset" value="다시작성" />
			<input type="button" value="회원가입" onClick="location.href='memberJoinForm'"/>
		</div>
	</div>
	<div class="box2"><div id="footer">${message}</div></div>
</form>
</body>
</html>