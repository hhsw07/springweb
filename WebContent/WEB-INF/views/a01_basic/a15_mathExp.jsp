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
/*	
ex) 수학 문제
숫자1:[ ]
숫자2:[ ]
연산식:[select +,-,*,/ ]
선택했을 때, submit 처리 되어,
결과 @@ + @@ = @@
*/
--%>
		$("h2").text("수학 문제");
		
	});
</script>
</head>

<body>
<h2 align="center"></h2>
<form>
	숫자1: <input type="number" name="num01" /><br>
	숫자2: <input type="number" name="num02" /><br>
	<select name="sign01">
	<c:forEach var="sign" items="${sign}">
		<option value="${sign.val}">${sign.val}</option>
	</c:forEach>
	</select>
	<input type="submit" value="계산"/>
</form>
<c:choose>
	<c:when test="${param.sign01 == '+'}"><h3>${param.num01} ${param.sign01} ${param.num02} = ${param.num01+param.num02}</h3></c:when>
	<c:when test="${param.sign01 == '-'}"><h3>${param.num01} ${param.sign01} ${param.num02} = ${param.num01-param.num02}</h3></c:when>
	<c:when test="${param.sign01 == '*'}"><h3>${param.num01} ${param.sign01} ${param.num02} = ${param.num01*param.num02}</h3></c:when>
	<c:when test="${param.sign01 == '/'}"><h3>${param.num01} ${param.sign01} ${param.num02} = ${param.num01/param.num02}</h3></c:when>
</c:choose>
	
</body>
</html>