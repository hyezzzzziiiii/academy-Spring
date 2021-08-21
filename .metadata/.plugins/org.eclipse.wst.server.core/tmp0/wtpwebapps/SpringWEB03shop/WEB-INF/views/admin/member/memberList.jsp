<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/resources/admin/header.jsp"%>
<%@ include file="/resources/admin/sub_menu.jsp"%>

<script type="text/javascript">
function go_search_member() {
	var theForm = document.frm;
	if( theForm.key.value=="") return;
	theForm.action =  "memberList";
	theForm.submit();
}
function go_total_member() {
	var theForm = document.frm;
	theForm.key.value="";
	theForm.action =  "memberList";
	theForm.submit();
}
</script>
<article>
<h1>회원리스트</h1>  
<form name="frm" method="post">
<table style="float:right; ">
  <tr>
	  <td> 회원 이름
		  <input type="text" name="key" value="${key3}">
		  <input class="btn" type="button" value="검색" onclick="go_search_member()">
		  <input class="btn" type="button" name="btn_total" value="전체보기 " onClick="go_total_member()">
		  <input type="hidden" name="all_view" value="y">
	  </td>
  </tr>
</table>  
<br>
<table id="orderList">
  <tr>
    <th> 아이디(탈퇴여부) </th><th> 이름 </th><th> 이메일 </th><th> 우편번호 </th><th> 주소 </th><th> 전화 </th>  <th> 가입일 </th>
  </tr>
  <c:forEach items="${memberList}" var="memberVO">  
  <tr>
    <td>${memberVO.id} 
    <c:choose>
      <c:when test='${memberVO.useyn=="y"}'>
        <input type="checkbox" name="useyn" disabled="disabled">
      </c:when>
      <c:otherwise>
        <input type="checkbox" name="useyn" 
checked="checked" disabled="disabled">
      </c:otherwise>
    </c:choose>
    </td>
    <td> ${memberVO.name} </td>
    <td> ${memberVO.email} </td> 
    <td> ${memberVO.zip_num} </td>
    <td> ${memberVO.address} </td>
    <td> ${memberVO.phone} </td> 
    <td> <fmt:formatDate value="${memberVO.indate}"/></td>
  </tr>
  </c:forEach>
</table>



</form>
<jsp:include page="/resources/paging/paging.jsp">
    <jsp:param value="${paging.page}" name="page"/>
    <jsp:param value="${paging.beginPage}" name="beginPage"/>
    <jsp:param value="${paging.endPage}" name="endPage"/>
    <jsp:param value="${paging.prev}" name="prev"/>
    <jsp:param value="${paging.next}" name="next"/>
    <jsp:param value="memberList" name="command"/>
</jsp:include>
</article>
<%@ include file="/resources/admin/footer.jsp"%>    