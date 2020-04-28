<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<fmt:requestEncoding value="utf-8"/>     
<!DOCTYPE html>
<%--


 --%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/a00_com/bootstrap.min.css" >
<link rel="stylesheet" href="${path}/a00_com/jquery-ui.css" >
<script src="${path}/a00_com/jquery.min.js"></script>
<script src="${path}/a00_com/popper.min.js"></script>
<script src="${path}/a00_com/bootstrap.min.js"></script>
<script src="${path}/a00_com/jquery-ui.js"></script>
<style type="text/css">
	.ordR{text-align:right;}
	.ordL{text-align:left;}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		<%-- 
		
		--%>
		$("h2").text("예제) 부서정보");
				
	});
</script>
</head>

<body>
<div class="container">
    <h2 align='center'></h2>
	<table class="table table-hover">
		<thead>
			<tr class="table-success">
				<th>부서번호</th>
				<th>부서명</th>
				<th>위치</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="d" items="${deptlist}">
			<tr>
				<td>${d.deptno}</td>
				<td>${d.dname}</td>
				<td>${d.loc}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>