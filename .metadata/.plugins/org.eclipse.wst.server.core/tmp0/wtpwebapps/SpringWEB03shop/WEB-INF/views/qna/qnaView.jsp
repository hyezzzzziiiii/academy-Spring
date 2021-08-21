<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/headerfooter01/header.jsp"%>
<%@ include file="/resources/sub04/sub_img.html"%> 
<%@ include file="/resources/sub04/sub_menu.jsp"%>
<style>
	td{color: white;}
	a{color: yellow;}
	h3{color: white;}
	th{color: white;}
</style>
<article>
<h2> 1:1 고객 게시판 </h2>
<h3> 고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다.</h3>
<form name="formm" method="post">
<table>
	<tr><th>제목</th>
		<td width="600" style="text-align:left;color:white">${qnaVO.subject}</td></tr>
	<tr><th>등록일</th>
		<td align="left" style="text-align:left;color:white">
		<fmt:formatDate value="${qnaVO.indate}" type="date"/></td></tr>
	<tr><th>질문내용</th>
	<td align="left" style="text-align:left;color:white;font-size:115%;">
		<pre>${qnaVO.content}</pre></td></tr>
	<tr><th>답변 내용</th>
		<td align="left" style="text-align:left;color:white;">${qnaVO.reply}</tr>
</table>
<div class="clear"></div>
<div id="buttons" style="float:right">
<input type="button" value="목록보기" class="submit" 
onclick="location.href='qnaWriteForm'"> <!--  -->
<input type="button" value="쇼핑 계속하기" class="cancel" onclick="location.href='/shop/'">
</div>
</form>
</article>

<%@ include file="/resources/headerfooter01/footer.jsp"%> 