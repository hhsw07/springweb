<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<fmt:requestEncoding value="utf-8"/>     
<!DOCTYPE html>
<%--
<spring code="코드값" /> ==> 코드값에 mapping 된 내용이 언어에 따라서 출력이 된다.

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
<script type="text/javascript">
	$(document).ready(function(){
		<%-- 
		msg.properties 파일의 내용
		multilang=multilanguage
		welcome=welcome!
		id=id
		name=name
		pwd=password
		greet=hi!!
		regmem=register member!
		search=search
		
		
		--%>
		$("h2").text("<spring:message code='multilang'/>");
					
	});
</script>
</head>

<body>
<div class="jumbotron text-center">
	<h2></h2>
</div>
<div class="container">
	<h3 align="center"><spring:message code="regmem"/></h3>
	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<span class="input-group-text"><spring:message code="id"/></span>
		</div>
		<input name="id" class="form-control" 
			placeholder="<spring:message code="id"/><spring:message code="reg"/>" />	
	</div>     
</div>
</body>
</html>