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
/prod.do
물건명: [  ]
가격: [  ]
개수: [  ]
[구매]

/prodShow.do
@@@을 @@개 @@@원 구매하여 총비용이 @@원 입니다.
*/	
--%>
		$("h2").text("물건입력");
		var name = "${param.name}";
		if(name != ""){
			$("h3").text("${param.name}을(를) ${param.cnt}개 ${param.price}원 구매하여 총 비용이 ${tot}입니다.");
		}
	});
</script>
</head>

<body>
<h2 align="center"></h2>
<form action="prodShow.do" method="post">
	<table align="center">
		<tr><th>물건명:</th>
			<td><input type="text" name="name" /></td></tr>
		<tr><th>가격:</th>
			<td><input type="text" name="price" /></td></tr>
		<tr><th>개수:</th>
			<td><input type="text" name="cnt" /></td></tr>
		<tr><td colspan="2" style="text-align:center;"><input type="submit" value="구매" /></td></tr>
	</table>
</form>
<h3></h3>
</body>
</html>