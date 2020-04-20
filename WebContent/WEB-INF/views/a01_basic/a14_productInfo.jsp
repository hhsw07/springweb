<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.* "
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<fmt:requestEncoding value="utf-8"/>
<!DOCTYPE html>
<%--

--%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/a00_com/a00_com.css" >
<script src="${path}/a00_com/jquery-3.4.1.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(){
<%--

--%>
		$("h2").text("물건정보");
	});
</script>
</head>

<body>
<h2 align="center"></h2>
<form method="post">
	물건명: <input type="text" name="name"/><br>
	가격: <input type="text" name="price"/><br>
	개수: <input type="text" name="cnt"/><br>
	<input type="submit" value="구매"/><br>
</form>
<c:if test="${not empty p01.name}">
	<h3>${p01.name}(${p01.price})을(를) ${p01.cnt}개 구매하여, 총 비용이 ${p01.price*p01.cnt}원 입니다.</h3>
</c:if>
로그인 아이디 : ${mem.id}<br>
과일 선택:
<select>
	<c:forEach var="code" items="${sel01}">
		<option value="${code.key}">${code.val}</option>
	</c:forEach>
</select>
</body>
</html>