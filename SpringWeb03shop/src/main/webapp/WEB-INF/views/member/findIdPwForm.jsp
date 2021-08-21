<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{background:pink;}
</style>
<script type="text/javascript">
function move_pw(){
	document.frm.action="findPwForm";
	document.frm.submit();
	//location.href="findPwForm";
}

function move_Id(){
	document.frm.action="findIdForm";
	document.frm.submit();
	//location.href="findIdForm";
}
</script>
</head>
<body>
<h2>아이디 찾기</h2>
<form method="post" name="frm">
<table align="center" bgcolor="black" cellspacing="1" width="400">
	<tr align="center" bgcolor="white" height="200">
		<td width="230"><h3>아이디 찾기</h3><br>
			<input type="button" class="submit" value="이동" onClick="move_Id()">
		</td>
		<td width="230"><h3>비밀번호 찾기</h3><br>
			<input type="button"  class="submit" value="이동" onClick="move_pw()">
		</td>
	</tr>
</table>
</form>
</body>
</html>