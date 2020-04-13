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
		$("h2").text("구매 처리");
	});
</script>
</head>

<body>
<!--  
A04_ProductCtrl.java
	초기 화면   구매 물건명 : [    ]
	               가격:[   ]
			  갯수 :[   ]
	               [구매]
	다음 화면
		@@@를 @@개 구매하여 총 비용이 @@@원 입니다.


 -->
<h2 align="center"></h2>
<form action="${path}/buyPrc.do" method="post">
	<table align="center">
		<tr>
			<th>구매물건명</th>
			<td><input type="text" name="pname"/></td>
		</tr>
		<tr>
			<th>가격</th>
			<td><input type="text" name="price"/></td>
		</tr>
		<tr>
			<th>개수</th>
			<td><input type="text" name="cnt"/></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="구매"/></td>
		</tr>
	
	</table>
</form>
<c:if test="${not empty result}">
<h3 align="center">${result}</h3>
</c:if>
</body>
</html>