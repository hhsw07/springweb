<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.* "
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<fmt:requestEncoding value="utf-8"/>
<!DOCTYPE html>
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
		$("h2").text("a03_exp");
	});
</script>
</head>

<body>
<%--
ex) A02_ExpCtrl.java		a03_exp.jsp
http://localhost:5080/springweb/exp02.do?num01=25&num02=30
화면 출력 : 25 + 30 = 50
--%>
<h2 align="center"></h2>
<h3 align="center">${param.num01} + ${param.num02} = ${sum}</h3>
</body>
</html>