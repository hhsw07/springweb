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
		$("h2").text("로그인");
		var result = "${result}";
		if(result != ""){
			alert(result);
		}
	});
/*
ex) A04_ProductCtrl.java
	초기화면	구매 물건명 : [  ]
			가격 : [  ]
			개수 : [  ]
			[구매]
	다음화면
		@@@를 @@개 구매하여 총 비용이 @@@원 입니다.
*/
</script>
</head>

<body>
<h2 align="center"></h2>
<form action="${path}/loginPrc.do" method="post">
	<table align="center">
		<tr>
			<th>아이디</th>
			<td><input type="text" name="id" /></td>
		</tr>
		<tr>
			<th>패스워드</th>
			<td><input type="password" name="pass" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="로그인" />
			</td>
		</tr>
	</table>
</form>
</body>
</html>