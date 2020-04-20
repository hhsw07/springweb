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
		$("h2").text("login get/post");
		var valid = "${valid}";
		if(valid != ""){
			if(valid == "true"){
				alert("로그인 성공");
			}else{
				alert("로그인 실패");
			}
		}
	});
</script>
</head>

<body>
<h2 align="center"></h2>
<form method="post">
<table align="center">
	<tr><th>ID</th>
		<td><input type="text" name="id" /></td></tr>
	<tr><th>PASS</th>
		<td><input type="password" name="pass" /></td></tr>
	<tr><td colspan="2" style="text-align:center;"><input type="submit" value="로그인" /></td></tr>
</table>
</form>

</body>
</html>