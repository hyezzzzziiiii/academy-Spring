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
</head>
<body>
<h2>Id 찾기</h2>
<form method="post" name="frm" action="findIdCertificationNum">
<table align="center" bgcolor="black" cellspacing="1" width="400">
	<tr align="center" bgcolor="white" >
		<td width="430"><h3>성명 : ${member.name}</h3>
			<input type="hidden" name="name" value="${member.name}"></td></tr>
	<tr align="center" bgcolor="white" >	<td width="430">
			<h3>전화번호 : ${member.phone}</h3>
			<input type="hidden" name="phone" value="${member.name}">
			<input type="hidden" name="id" value="${member.id}">	</td></tr>
	<tr align="center" bgcolor="white" >
		<td width="430">
		<h3>인증번호<input type="text" name="inputNum"  ></h3>
		전송받은 번호를 입력하세요
		<input type="submit" value="인증번호 확인"></h3></td>
	</tr>
</table>
</form>
</body>
</html>